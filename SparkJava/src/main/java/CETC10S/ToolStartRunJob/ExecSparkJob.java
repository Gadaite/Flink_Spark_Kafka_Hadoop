package CETC10S.ToolStartRunJob;

import CETC10S.ToolConnectDB.MysqlJDBC;

public class ExecSparkJob extends MysqlJDBC {
    public static void main(String[] args) throws Exception{
        MysqlJDBC jdbc = new MysqlJDBC();
        String driver = jdbc.init().getProperty("driver");
        String url = jdbc.init().getProperty("url");
        String username = jdbc.init().getProperty("username");
        String password = jdbc.init().getProperty("pwd");
    }
}
