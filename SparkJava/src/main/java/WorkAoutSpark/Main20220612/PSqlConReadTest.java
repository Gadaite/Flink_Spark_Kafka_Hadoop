package WorkAoutSpark.Main20220612;

import GadaiteToolConnectDB.PostgresqlConnect;
import GadaiteToolGeo.BinaryToGeometry;
import org.locationtech.jts.geom.LineString;

import java.sql.ResultSet;

/**
 * 测试PostgresqlConnect方式读取PSql
 * 完成
 */
public class PSqlConReadTest {
    public static void main(String[] args) throws Exception {
        PostgresqlConnect connect = new PostgresqlConnect();
        ResultSet resultSet = connect.PSqlQuery("select * from objecttrajactory limit 10");
        while (resultSet.next()){
            String s = resultSet.getObject(2).toString();
            LineString lineString = new BinaryToGeometry().JTSLineString(s);
            System.out.println(lineString.toString());
        }
    }
}
