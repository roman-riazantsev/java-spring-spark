package football.udfs;

import football.utils.UDF;
import org.apache.spark.sql.api.java.UDF1;

@UDF
public class TimeValidatorUDF implements UDF1<String, Boolean> {
    @Override
    public Boolean call(String time) throws Exception {
        return Integer.parseInt(time.split(":")[0]) < 110;
    }
}
