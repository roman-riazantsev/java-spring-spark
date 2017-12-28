package football.udfs;

import football.utils.UDF;
import org.apache.spark.sql.api.java.UDF3;

import java.util.ArrayList;
import java.util.Arrays;

@UDF
public class CodeValidatorUDF implements UDF3<Integer, String, String, Boolean> {
    @Override
    public Boolean call(Integer code, String from, String to) throws Exception {
        ArrayList<Integer> twoPlayerCode = new ArrayList<>();
        twoPlayerCode.addAll(Arrays.asList(3, 4, 7, 9));

        return twoPlayerCode.contains(code);
    }
}
