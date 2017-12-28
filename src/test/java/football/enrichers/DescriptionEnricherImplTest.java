package football.enrichers;

import football.configs.AppConfig;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static football.configs.Const.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles(DEV)
public class DescriptionEnricherImplTest {
    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private SQLContext sqlContext;

    @Autowired
    private DescriptionEnricherImpl descriptionEnricher;

    @Test
    public void enrichTest() {
        List<Integer> stringAsList = new ArrayList<>();
        stringAsList.add(2);
        stringAsList.add(3);

        JavaRDD<Row> rowRDD = sc.parallelize(stringAsList).map(RowFactory::create);
        StructType schema = DataTypes.createStructType(new StructField[]{DataTypes.createStructField(CODE, DataTypes.IntegerType, false)});
        DataFrame dataFrame = sqlContext.createDataFrame(rowRDD, schema).toDF();
        DataFrame enrichedDataFrame = descriptionEnricher.enrich(dataFrame);

        String[] codeDescriptions = new String[2];
        Row[] rows = enrichedDataFrame.select(CODE_DESC).collect();
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].length(); j++) {
                codeDescriptions[i] = rows[i].get(0).toString();
            }
        }

        Assert.assertArrayEquals(codeDescriptions,
                new String[]{"red card", "pass sent"});
    }
}