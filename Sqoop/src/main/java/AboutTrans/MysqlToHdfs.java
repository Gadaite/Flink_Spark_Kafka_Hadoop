package AboutTrans;

import org.apache.hadoop.conf.Configuration;

public class MysqlToHdfs {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://192.168.1.10:9000/");
        String[] arg = new String[] {"--connect","jdbc:mysql://192.168.1.10:3306/SQOOP_DATA",
                "--username","root",
                "--password","LYP809834049",
                "--table","seeds_dataset",
                "--m","1",
                "--export-dir","hdfs://192.168.1.10:8020/user/hive/warehouse/dw_api_server.db/persons",
                "--input-fields-terminated-by"," "
        };
    }
}
