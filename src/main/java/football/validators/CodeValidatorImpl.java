package football.validators;


import football.udfs.CodeValidatorUDF;
import org.apache.spark.sql.DataFrame;
import org.springframework.stereotype.Component;

import static football.configs.Const.*;
import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;

@Component
public class CodeValidatorImpl implements Validator {
    @Override
    public DataFrame validate(DataFrame dataFrame) {
        return dataFrame.filter(callUDF(CodeValidatorUDF.class.getName(), (col(CODE)), (col(FROM)), (col(TO))));
    }
}
