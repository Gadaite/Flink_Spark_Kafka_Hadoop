package WorkAoutSpark.Main20220612;

import GadaiteToolConnectDB.MysqlJdbcCon;
import GadaiteToolConnectDB.PostgresqlConnect;
import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * 测试从Mysql加载数据处理转换数据类型
 * 转为JavaBean,使用Java反射机制由JavaRDD创建DataSet
 * 上传数据到PostgresSql
 */
public class PSqlUploadData {
    public static void main(String[] args) throws Exception {
        /**
         * Mysql数据源
         */
        MysqlJdbcCon mysqlJdbcCon = new MysqlJdbcCon();
        SparkSession spark = mysqlJdbcCon.getSparkSesssion("MysqlToPSql", "ERROR");
        Dataset<Row> dataset = mysqlJdbcCon.GetDataSetByProperties(spark, "select * from BrightkiteTrajectory2008");
        dataset.show(10);
        System.out.println(dataset.count());
        /**
         * 提交到PSql
         */
        PostgresqlJdbcCon postgresqlJdbcCon = new PostgresqlJdbcCon();
        postgresqlJdbcCon.PushToPSql(dataset,"BrightkiteTrajectory2008","overwrite");
        /**
         * 尝试直接更新表结构
         */
        PostgresqlConnect postgresqlConnect = new PostgresqlConnect();
        postgresqlConnect.ExecPSql("ALTER TABLE public.brightkitetrajectory2008 ALTER COLUMN trajectory TYPE geometry " +
                "USING trajectory::geometry;");
        /**
         * 在Dbeaver上查看数据已经更新
         * 下面使用代码查看一下
         */
        StringBuffer sql = new StringBuffer();
        sql.append("select A.attnum,");
        sql.append("( SELECT description FROM pg_catalog.pg_description WHERE objoid = A.attrelid AND objsubid = A.attnum ) AS comment,");
        sql.append("A.attname AS field,");
        sql.append("( select typname from pg_type where oid = A.atttypid) AS type,");
        sql.append("A.atttypmod AS data_type ");
        sql.append("FROM pg_catalog.pg_attribute A ");
        sql.append("WHERE 1 = 1 ");
        sql.append("AND A.attrelid = ( SELECT oid FROM pg_class WHERE relname = '");
        sql.append("brightkitetrajectory2008");//   表名
        sql.append("') ");
        sql.append("AND A.attnum > 0 ");
        sql.append("AND NOT A.attisdropped ");
        sql.append("ORDER by A.attnum ; ");
        ResultSet resultSet = postgresqlConnect.PSqlQuery(sql.toString());
        int columnCount = resultSet.getMetaData().getColumnCount();
        StringJoiner joiner = new StringJoiner("\t");
        StringJoiner joiner2 = new StringJoiner("\t");
        boolean isget = false;
        while (resultSet.next()){
            for (int i=1;i<=columnCount;i++){
                if (!isget){
                    joiner.add(String.valueOf(resultSet.getMetaData().getColumnName(i)));
                }
                joiner2.add(String.valueOf(resultSet.getObject(i)));
                if (i == columnCount && !isget){
                    isget = true;
                    System.out.println(joiner);
                }
            }
            System.out.println(joiner2);
            joiner2 = new StringJoiner("\t");
        }
        /**
         * 执行清空表试试对数据进行插入的方式写入,是否会改变表结构(不会)
         * 但是写入就会报错:(DataSet的trajectory是字符串类型,不能直接转换为geometry)
         * Caused by: org.postgresql.util.PSQLException: ERROR:
         * column "trajectory" is of type geometry but expression is of type character varying
         * 说明append方式需要数据类型匹配才能追加写入
         */
        postgresqlConnect.ExecPSql("truncate brightkitetrajectory2008 ;");
        postgresqlJdbcCon.PushToPSql(dataset,"brightkitetrajectory2008","append");
    }
}
