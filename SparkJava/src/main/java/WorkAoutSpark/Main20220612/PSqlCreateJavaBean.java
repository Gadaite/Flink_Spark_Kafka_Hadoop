package WorkAoutSpark.Main20220612;

import GadaiteToolConnectDB.AutoCreatePSqlBean;

/**
 * 测试AutoCreatePSqlBean创建JavaBean
 * 通过
 */
public class PSqlCreateJavaBean {
    public static void main(String[] args) throws Exception {
        AutoCreatePSqlBean autoCreatePSqlBean = new AutoCreatePSqlBean();
        AutoCreatePSqlBean.packageOutPath = "WorkAoutSpark.Main20220612";
        autoCreatePSqlBean.generateTables = new String[]{"objecttrajactory"};
        autoCreatePSqlBean.generate();
    }
}
