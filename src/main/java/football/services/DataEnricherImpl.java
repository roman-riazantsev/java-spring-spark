package football.services;

import football.aop.LogDataFrame;
import football.enrichers.Enricher;
import org.apache.spark.sql.DataFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataEnricherImpl implements DataEnricher {
    @Autowired
    private List<Enricher> enrichers;

    @LogDataFrame
    public DataFrame enrichData(DataFrame dataFrame) {
        for (Enricher enricher : enrichers) {
            dataFrame = enricher.enrich(dataFrame);
        }
        return dataFrame;
    }
}
