package ZTestOnlY;

import GadaiteToolConnectDB.PostgresqlConnect;

import java.sql.ResultSet;
import java.util.Map;
import java.util.Set;

public class Test06 {
    public static void main(String[] args) throws Exception {
        PostgresqlConnect connect = new PostgresqlConnect();
        ResultSet resultSet = connect.PSqlQuery("select * from brightkitetrajectory2008 limit 10");
        Map<String, String> mapType = connect.GetColumnNameMapType(resultSet);
        Set<Map.Entry<String, String>> entries = mapType.entrySet();
        for (Map.Entry<String, String> entry :entries){
            System.out.println(entry.getKey() + " --> " + entry.getValue());
        }
    }
}
