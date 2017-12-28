package football.enrichers;

import org.apache.spark.sql.DataFrame;

public interface Enricher {
    DataFrame enrich (DataFrame dataFrame);
}
