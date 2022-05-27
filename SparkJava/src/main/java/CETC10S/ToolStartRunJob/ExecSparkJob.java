package CETC10S.ToolStartRunJob;

import CETC10S.ToolConnectDB.MysqlJdbcCon;

public class ExecSparkJob extends MysqlJdbcCon {
    public static void main(String[] args) throws Exception{
        MysqlJdbcCon jdbc = new MysqlJdbcCon();
        String driver = jdbc.init().getProperty("driver");
        String url = jdbc.init().getProperty("url");
        String username = jdbc.init().getProperty("username");
        String password = jdbc.init().getProperty("pwd");

    }
}
