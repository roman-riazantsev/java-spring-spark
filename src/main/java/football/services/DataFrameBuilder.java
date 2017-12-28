package football.services;

import org.apache.spark.sql.DataFrame;

public interface DataFrameBuilder {
    DataFrame buildDataFrame(String dataFile);
}
