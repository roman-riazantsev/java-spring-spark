package football.services;

import org.apache.spark.sql.DataFrame;

public interface DataEnricher {
    DataFrame enrichData(DataFrame dataFrame);
}
