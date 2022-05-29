package WorkAoutSpark.Main20220529;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;

/**
 * made by Gadaite
 * RDD映射为JavaBean类实例对象
 */
public class MMapFunction implements Function<Row, Audi> {
    @Override
    public Audi call(Row v1) throws Exception {
        Audi audi = new Audi();
        //  原表model字段前面有空格，在这里进行删除
        audi.setModel(v1.getString(v1.fieldIndex("model")).split(" ")[1]);
        audi.setYear(v1.getInt(v1.fieldIndex("year")));
        audi.setPrice(v1.getInt(v1.fieldIndex("price")));
        audi.setTransmission(v1.getString(v1.fieldIndex("transmission")));
        audi.setMileage(v1.getInt(v1.fieldIndex("mileage")));
        audi.setFuelType(v1.getString(v1.fieldIndex("fuelType")));
        audi.setTax(v1.getInt(v1.fieldIndex("tax")));
        audi.setMpg(v1.getDouble(v1.fieldIndex("mpg")));
        audi.setEngineSize(v1.getDouble(v1.fieldIndex("engineSize")));
        return audi;
    }
}
