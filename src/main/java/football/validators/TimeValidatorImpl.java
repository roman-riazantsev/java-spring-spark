package football.validators;

import football.udfs.TimeValidatorUDF;
import org.apache.spark.sql.DataFrame;
import org.springframework.stereotype.Component;

import static football.configs.Const.EVENT_TIME;
import static football.configs.Const.START_TIME;
import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;

@Component
public class TimeValidatorImpl implements Validator {
    @Override
    public DataFrame validate(DataFrame dataFrame) {
        return dataFrame.filter(callUDF(TimeValidatorUDF.class.getName(), (col(START_TIME)))).
                filter(callUDF(TimeValidatorUDF.class.getName(), (col(EVENT_TIME))));
    }
}
