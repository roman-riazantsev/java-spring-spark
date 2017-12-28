package football.services;

import org.apache.spark.sql.DataFrame;

public interface DataValidator {
    DataFrame validateData(DataFrame dataFrame);
}
