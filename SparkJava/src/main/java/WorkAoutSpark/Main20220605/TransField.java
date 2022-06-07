package WorkAoutSpark.Main20220605;

import org.apache.spark.sql.api.java.UDF1;


public class TransField implements UDF1<String, String> {
    @Override
    public String call(String s) throws Exception {
        if (s != null){
            if (s.contains("T")){
                s = s.replace("T"," ");
            }
            if (s.contains("Z")){
                s = s.replace("Z","");
            }
            return s;
        }else {
            return null;
        }

    }
}
