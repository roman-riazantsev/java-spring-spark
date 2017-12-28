package football.enrichers;

import football.udfs.DescriptionDistributorUDF;
import org.apache.spark.sql.DataFrame;
import org.springframework.stereotype.Component;

import static football.configs.Const.CODE;
import static football.configs.Const.CODE_DESC;
import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;

@Component
public class DescriptionEnricherImpl implements Enricher {

    @Override
    public DataFrame enrich(DataFrame dataFrame) {
        return dataFrame.withColumn(CODE_DESC, callUDF(DescriptionDistributorUDF.class.getName(), (col(CODE))));
    }
}
