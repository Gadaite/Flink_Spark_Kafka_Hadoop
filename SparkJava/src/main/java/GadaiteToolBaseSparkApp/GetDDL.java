package GadaiteToolBaseSparkApp;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class GetDDL {
    private Dataset<Row> dataset;

    public Dataset<Row> getDataset() {
        return dataset;
    }

    public void setDataset(Dataset<Row> dataset) {
        this.dataset = dataset;
    }

    public GetDDL(){}
    public GetDDL(Dataset<Row> dataset) {
        this.dataset = dataset;
    }
    public String GetDefaultDDL(Dataset dataset){
        String ddl = dataset.schema().toDDL();
        return ddl;
    }
    public String GetGadaiteDDL(Dataset dataset){
        String ddl = dataset.schema().simpleString();
        String subddl = ddl.substring(ddl.indexOf("<") + 1, ddl.indexOf(">"));
        return subddl;
    }
}
