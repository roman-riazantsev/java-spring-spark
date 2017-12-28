package football.validators;

import org.apache.spark.sql.DataFrame;

public interface Validator {
    DataFrame validate(DataFrame dataFrame);
}
