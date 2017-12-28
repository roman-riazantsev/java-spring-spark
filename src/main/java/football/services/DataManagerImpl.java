package football.services;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataManagerImpl implements DataManager {

    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private DataFrameBuilder dataFrameBuilder;

    @Autowired
    private DataEnricher dataEnricher;

    @Autowired
    private DataValidator dataValidator;

    @Override
    public void manageData() {
        DataFrame dataFrame = dataFrameBuilder.buildDataFrame("data/rawData.txt");
        dataFrame = dataEnricher.enrichData(dataFrame);
        dataFrame = dataValidator.validateData(dataFrame);
    }
}







