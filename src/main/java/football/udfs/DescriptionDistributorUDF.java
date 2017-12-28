package football.udfs;

import football.configs.CodeConfig;
import football.utils.AutowiredBroadcast;
import football.utils.UDF;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.api.java.UDF1;

@UDF
public class DescriptionDistributorUDF implements UDF1<Integer, String> {
    @AutowiredBroadcast
    private Broadcast<CodeConfig> codes;

    @Override
    public String call(Integer code) throws Exception {
        return codes.getValue().getCodes().getProperty(code.toString());
    }
}
