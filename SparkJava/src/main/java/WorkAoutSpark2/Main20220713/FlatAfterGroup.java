package WorkAoutSpark2.Main20220713;

import GadaiteToolConnectDB.MysqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.io.Serializable;
import java.util.*;

/**
 * 分组之后的FlatMap,在内部的还是相同分组的数据
 */
public class FlatAfterGroup {
    public static void main(String[] args) {
        MysqlJdbcCon con = new MysqlJdbcCon();
        SparkSession spark = con.getSparkSesssion("flatAfterGroup", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        JavaRDD<TestPojo> javaRDD = jsc.parallelize(Arrays.asList(
                new TestPojo("java", "Spark"),
                new TestPojo("java", "Flink"),
                new TestPojo("python", "Spark"),
                new TestPojo("python", "Pytorch"),
                new TestPojo("Scala", "Spark"),
                new TestPojo("Scala", "Akka")
        ));
        javaRDD.mapToPair(new PairFunction<TestPojo, String, TestPojo>() {
            @Override
            public Tuple2<String, TestPojo> call(TestPojo testPojo) throws Exception {
                return new Tuple2<>(testPojo.getKey(),testPojo);
            }
        }).groupByKey().flatMap(new FlatMapFunction<Tuple2<String, Iterable<TestPojo>>, TestPojo>() {
            @Override
            public Iterator<TestPojo> call(Tuple2<String, Iterable<TestPojo>> stringIterableTuple2) throws Exception {
                String key = stringIterableTuple2._1;
                List<String> list = new ArrayList<>();
                Set<String> set = new HashSet<>();
                stringIterableTuple2._2.forEach(x->{
                    String s = x.getKey();
                    list.add(s);
                    set.add(s);
                });
                if (set.size() >= 2){
                    System.out.println("able");
                }
                return stringIterableTuple2._2.iterator();
            }
        }).foreach(new VoidFunction<TestPojo>() {
            @Override
            public void call(TestPojo testPojo) throws Exception {
                System.out.println(testPojo);
            }
        });
    }
    public static class TestPojo implements Serializable {
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public TestPojo(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public TestPojo(){}

        @Override
        public String toString() {
            return "TestPojo{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
