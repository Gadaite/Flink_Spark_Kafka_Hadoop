package CETC10S.BaseSparkApp;


import java.io.FileReader;
import java.io.Serializable;
import java.util.Properties;

public class ReadMysqlProperyies implements Serializable {
    public static void main(String[] args) throws Exception{
        Properties properties = new Properties();
        FileReader fileReader = new FileReader("F:\\CodeG50\\BiGData\\SparkJava\\src\\main\\resources\\mysql.properties");
        properties.load(fileReader);
        fileReader.close();
    }
}
