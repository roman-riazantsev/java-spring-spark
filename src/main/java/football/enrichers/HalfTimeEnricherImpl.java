package football.enrichers;

import football.udfs.HalfTimeDistributorUDF;
import org.apache.spark.sql.DataFrame;
import org.springframework.stereotype.Component;

import static football.configs.Const.EVENT_TIME;
import static football.configs.Const.HALF_TIME;
import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;

@Component
public class HalfTimeEnricherImpl implements Enricher{
    @Override
    public DataFrame enrich(DataFrame dataFrame) {
        return dataFrame.withColumn(HALF_TIME, callUDF(HalfTimeDistributorUDF.class.getName(), (col(EVENT_TIME))));
    }
}
