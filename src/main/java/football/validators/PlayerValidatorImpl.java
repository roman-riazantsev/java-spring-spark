package football.validators;

import football.udfs.PlayerValidatorUDF;
import org.apache.spark.sql.DataFrame;
import org.springframework.stereotype.Component;

import static football.configs.Const.FROM;
import static football.configs.Const.TO;
import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;

@Component
public class PlayerValidatorImpl implements Validator {
    @Override
    public DataFrame validate(DataFrame dataFrame) {
        return dataFrame.filter(callUDF(PlayerValidatorUDF.class.getName(), (col(FROM)))).
                filter(callUDF(PlayerValidatorUDF.class.getName(), (col(TO))));
    }
}
