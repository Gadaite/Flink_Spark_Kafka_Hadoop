package WorkAoutSpark.Main20220604;

import GadaiteToolBaseSparkApp.GetDDL;
import GadaiteToolBaseSparkApp.RowToJavaBean;
import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.*;

public class SortJavaBeanObject {
    public static void main(String[] args) throws Exception {
        /**
         * 获取数据
         */
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("SortClassObject", "ERROR");
        Dataset<Row> dataset = con.GetDataSetByProperties(spark, "select * from audi limit 10");
        JavaRDD<Row> javaRDD = dataset.toJavaRDD();
        /**
         * 获取JavaBean对象列表
         */
        JavaRDD<Audi> audiJavaRDD = javaRDD.map(new RowToJavaBean<Audi>(new GetDDL().GetGadaiteDDL(dataset), Audi.class));
        List<Audi> collect = audiJavaRDD.collect();
        /**
         * Collections.sort(collect);
         * 下面出现如下报错信息
         * Exception in thread "main" java.lang.UnsupportedOperationException
         * 	at java.util.AbstractList.set(AbstractList.java:132)
         * 	at java.util.AbstractList$ListItr.set(AbstractList.java:426)
         * 	at java.util.List.sort(List.java:482)
         * 	at java.util.Collections.sort(Collections.java:143)
         * 	at WorkAoutSpark.Main20220604.SortJavaBeanObject.main(SortJavaBeanObject.java:28)
         */
//        Collections.sort(collect);
        List<Audi> list = new ArrayList<>();
        for (Audi obj : collect){
            list.add(obj);
        }
        Collections.sort(list);
        list.forEach(x-> System.out.println(x));
        System.out.println("_____________________________________________");
        /**
         * Java8建议入下写法，更加方便
         */
        list.sort(Comparator.comparing(Audi::getYear).reversed());
        list.forEach(x -> System.out.println(x));
    }
}
