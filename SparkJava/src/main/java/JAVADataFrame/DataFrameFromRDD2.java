package JAVADataFrame;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;

public class DataFrameFromRDD2 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("DataFrameFromRDD2");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        JavaRDD<Row> javarddrow = jsc.parallelize(Arrays.asList(
                RowFactory.create(1, 2, 3),
                RowFactory.create(1, 2, 3),
                RowFactory.create(1, 2, 3),
                RowFactory.create(1, 2, 3)
        ));
        StructType schema = DataTypes.createStructType(
                Arrays.asList(
                        DataTypes.createStructField("col1", DataTypes.IntegerType, true),
                        DataTypes.createStructField("col2", DataTypes.IntegerType, true),
                        DataTypes.createStructField("col3", DataTypes.IntegerType, true)
                        )
        );
        SQLContext sqlContext = new SQLContext(jsc);
        Dataset<Row> dataFrame = sqlContext.createDataFrame(javarddrow, schema);
        dataFrame.printSchema();
        dataFrame.show();
        dataFrame.createOrReplaceTempView("tabletmp");
        sqlContext.sql("select * from tabletmp limit 1").show();
    }
}
