package WorkAoutSpark.Main20220614;

import GadaiteToolConnectDB.PostgresqlConnect;

/**
 * 用于更改表的字段对应的数据类型
 */
public class ChangeTableSchema {
    public static void main(String[] args) throws Exception {
        PostgresqlConnect pconnect = new PostgresqlConnect();
        String sql = "ALTER TABLE public.trajectlonlat ALTER COLUMN altitude TYPE float8 USING altitude::float8;\n" +
                "ALTER TABLE public.trajectlonlat RENAME COLUMN col4 TO datetime;\n" +
                "ALTER TABLE public.trajectlonlat ALTER COLUMN datetime TYPE timestamp USING datetime::timestamp;\n";
        String[] sqls = sql.split("\\n");
        for (String s:sqls){
            System.out.println(s);
            pconnect.ExecPSql(s);
        }
    }
}
