package JavaDataFrame;

import org.apache.hadoop.security.authorize.DefaultImpersonationProvider;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;
import java.util.List;

public class DataFrameFromRDD {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().config("spark.ui.showConsoleProgress", "false").appName("DataFrameFromRDD")
                .master("local[*]").enableHiveSupport().getOrCreate();
        spark.sparkContext().setLogLevel("ERROR");
        SparkConf conf = spark.sparkContext().getConf();
        spark.stop();
        JavaSparkContext jsc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(jsc);
        JavaRDD<Integer> javaRDD = jsc.parallelize(Arrays.asList(1, 2, 3, 4, 5));
        JavaRDD<Row> rddrow = javaRDD.map(new Function<Integer, Row>() {
            @Override
            public Row call(Integer v1) throws Exception {
                return RowFactory.create(v1);
            }
        });
        List<StructField> structfield = Arrays.asList(
                DataTypes.createStructField("colname", DataTypes.IntegerType, true)
        );
        StructType schema = DataTypes.createStructType(structfield);
        Dataset<Row> dataFrame = sqlContext.createDataFrame(rddrow, schema);
        dataFrame.printSchema();
        dataFrame.show();
        dataFrame.javaRDD().foreach(x -> System.out.println(x));

    }
}
