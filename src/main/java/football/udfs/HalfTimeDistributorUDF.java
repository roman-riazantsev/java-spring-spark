package football.udfs;

import football.utils.UDF;
import org.apache.spark.sql.api.java.UDF1;

@UDF
public class HalfTimeDistributorUDF implements UDF1<String, String> {
    @Override
    public String call(String eventTime) throws Exception {
        return Integer.parseInt(eventTime.split(":")[0]) < 45 ? "first" : "second";
    }
}
