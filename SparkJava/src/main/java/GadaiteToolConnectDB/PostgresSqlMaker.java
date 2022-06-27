package GadaiteToolConnectDB;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Row;
import java.io.Serializable;
import java.util.Map;
import java.util.StringJoiner;

/**
 * made by Gadaite
 */
public class PostgresSqlMaker implements Serializable , VoidFunction<Row> {
    private PostgresqlConnect PSqlConnect = new PostgresqlConnect();

    private String TableName;

    /**
     * 配合foreach对RDD中的数据进行入库
     * @param PSqlConnect   PostgresqlConnect对象
     * @param TableName     表名
     */
    public PostgresSqlMaker(PostgresqlConnect PSqlConnect, String TableName) {
        this.PSqlConnect = PSqlConnect;
        this.TableName = TableName;
    }

    public PostgresSqlMaker(){}

    public PostgresqlConnect getPSqlConnect() {
        return PSqlConnect;
    }

    public void setPSqlConnect(PostgresqlConnect PSqlConnect) {
        this.PSqlConnect = PSqlConnect;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    /**
     * made by Gadaite
     * 对RDD而言是进行foreach逐行写入丢失并行写入速度
     * @param javaRDD<Row> 需要插入方式提交到PostgresSql数据库的JavaRDD<Row>
     * @param TableName 上传的表名
     * @throws Exception
     */
    public void ExecInsert(JavaRDD<Row> javaRDD, String TableName) throws Exception {
        Map<String, String> nameMapType = PSqlConnect.GetColumnNameMapType(TableName);
        javaRDD.foreach(x ->{
            StringBuffer buffer = new StringBuffer();
            StringJoiner joinerField = new StringJoiner(" , ");
            StringJoiner joinerValue = new StringJoiner(" , ");
            buffer.append("INSERT INTO public.").append(TableName).append(" (");
            for (Map.Entry<String, String> entry : nameMapType.entrySet()){
                joinerField.add(entry.getKey());
                joinerValue.add(entry.getValue() + "('" + (x.get(x.fieldIndex(entry.getKey())).toString()) + "')");
            }
            buffer.append(joinerField.toString());
            buffer.append(") VALUES(").append(joinerValue.toString()).append(")");
            String sqlOfInsert = buffer.toString()
                    .replace("timestamp","");
            PSqlConnect.ExecPSql(sqlOfInsert);
        });
    }
    /**
     * made by Gadaite
     * 使用Map算子实现并行写入到PostgresSql数据库
     * @param row JavaRDD<Row>
     * @throws Exception
     */
    @Override
    public void call(Row row) throws Exception {
        Map<String, String> nameMapType = PSqlConnect.GetColumnNameMapType(TableName);
        StringBuffer buffer = new StringBuffer();
        StringJoiner joinerField = new StringJoiner(" , ");
        StringJoiner joinerValue = new StringJoiner(" , ");
        buffer.append("INSERT INTO public.").append(TableName).append(" (");
        for (Map.Entry<String, String> entry : nameMapType.entrySet()){
            joinerField.add(entry.getKey());
            joinerValue.add(entry.getValue() + "('" + (row.get(row.fieldIndex(entry.getKey())).toString()) + "')");
        }
        buffer.append(joinerField.toString());
        buffer.append(") VALUES(").append(joinerValue.toString()).append(")");
        String sqlOfInsert = buffer.toString()
                .replace("timestamp","");
//            System.out.println(sqlOfInsert);
        PSqlConnect.ExecPSql(sqlOfInsert);
    }
}
