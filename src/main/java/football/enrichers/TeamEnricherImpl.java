package football.enrichers;

import football.udfs.TeamDistributorUDF;
import org.apache.spark.sql.DataFrame;
import org.springframework.stereotype.Service;

import static football.configs.Const.*;
import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;

@Service
public class TeamEnricherImpl implements Enricher {
    @Override
    public DataFrame enrich(DataFrame dataFrame) {
        return dataFrame.
                withColumn(FROM_TEAM, callUDF(TeamDistributorUDF.class.getName(), (col(FROM)))).
                withColumn(TO_TEAM, callUDF(TeamDistributorUDF.class.getName(), (col(TO))));
    }
}
