package WorkAoutSpark.Main20220529;

import GadaiteToolConnectDB.MysqlConnect;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HandleTable extends MysqlConnect {
    public static void main(String[] args) throws Exception{
        MysqlConnect connect = new MysqlConnect();
        ResultSet resultSet = connect.MysqlQuery("select * from `CETC10S`.`flights` limit 1000000");
        List list = connect.GetResultSetColumnName(resultSet);
        /**
         * 选取指定列的数据：前25列
         */
        List ChooseColumns = new ArrayList<>();
        for (int i = 0;i < 25 ; i++){
            ChooseColumns.add(list.get(i));
        }
        List<Map> maps = connect.GetREsultSetMapData(resultSet);
        System.out.println(maps.size());
        FlightsDDL flightsDDL = new FlightsDDL();
        System.out.println(flightsDDL.GetDDL());
    }
}
