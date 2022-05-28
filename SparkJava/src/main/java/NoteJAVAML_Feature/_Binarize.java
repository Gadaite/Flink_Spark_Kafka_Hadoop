package NoteJAVAML_Feature;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.ml.feature.Binarizer;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;

import java.util.Arrays;

public class _Binarize {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("_Binarize");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(jsc);
        Dataset<Row> df = sqlContext.createDataFrame(jsc.parallelize(Arrays.asList(
                RowFactory.create(0.1),
                RowFactory.create(0.2),
                RowFactory.create(0.3),
                RowFactory.create(0.4),
                RowFactory.create(0.5)
        )), DataTypes.createStructType(Arrays.asList(
                DataTypes.createStructField("col", DataTypes.DoubleType, false)
        )));
        df.show();
        df.printSchema();
        Binarizer binarizer = new Binarizer();
        binarizer.setThreshold(0.3);
        binarizer.setInputCol("col");
        binarizer.setOutputCol("res");
        Dataset<Row> resds = binarizer.transform(df);
        resds.show();
        resds.printSchema();
    }
}
