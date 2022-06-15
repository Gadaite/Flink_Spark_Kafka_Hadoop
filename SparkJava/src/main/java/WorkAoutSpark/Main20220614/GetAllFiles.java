package WorkAoutSpark.Main20220614;

import GadaiteToolConnectDB.PostgresqlJdbcCon;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetAllFiles {
    public static void main(String[] args) throws Exception {
        int count =0;
        List<String> paths = new ArrayList<>();
        String rootpath = "D:\\googledownload\\Geolife Trajectories 1.3\\Data";
        File files = new File(rootpath);
        File[] docs = files.listFiles();
        for (File f1:docs){
            File[] f2 = f1.listFiles();
            for (File f3 : f2){
                File[] f4 = f3.listFiles();
                if (f4 != null){
                    for (File f5 : f4){
                        String path = f5.getAbsoluteFile().toPath().toString();
                        System.out.println(f5.getAbsoluteFile());
                        paths.add(path);
                        count += 1;
                    }
                }
            }
        }
        System.out.println("文件总数量：" + count);
        PostgresqlJdbcCon pcon = new PostgresqlJdbcCon();
        SparkSession spark = pcon.getSparkSesssion("20220615", "ERROR");
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        int c =0;
//        for (String path : paths){
        for (int i = 10983;i<paths.size();i++){
            String path = paths.get(i);
            JavaRDD<SampleData> javaRDD = jsc.textFile("file:\\" + path).filter(new Function<String, Boolean>() {
                @Override
                public Boolean call(String v1) throws Exception {
                    return v1.length() > "0,2,255,My Track,0,0,2,8421376".length() ;
                }
            }).map(new Function<String, SampleData>() {
                @Override
                public SampleData call(String v1) throws Exception {
                    String[] split = v1.split(",");
                    return new SampleData(split[0], split[1], split[4], split[5] + " " + split[6]);
                }
            });
            Dataset<Row> dataset = spark.createDataFrame(javaRDD, SampleData.class);
            pcon.PushToPSql(dataset,"TrajectLonlat","append");
            c += 1;
            if (c%1000 == 0){
                Thread.sleep(40*1000);
            }
            System.out.println("已完成文件数: " +c+" ,总数: " + count );
        }

    }
}
