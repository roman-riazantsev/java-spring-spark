package football.services;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static football.configs.Const.*;

@Service
public class DataFrameBuilderImpl implements DataFrameBuilder {
    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private SQLContext sqlContext;

    @Autowired
    private RDDBuilder rddBuilder;

    @Autowired
    private RowRDDBuilder rowRDDBuilder;

    private static StructType createSchema() {
        return DataTypes.createStructType(new StructField[]{
                DataTypes.createStructField(CODE, DataTypes.IntegerType, false),
                DataTypes.createStructField(FROM, DataTypes.StringType, false),
                DataTypes.createStructField(TO, DataTypes.StringType, false),
                DataTypes.createStructField(EVENT_TIME, DataTypes.StringType, false),
                DataTypes.createStructField(STADION, DataTypes.StringType, false),
                DataTypes.createStructField(START_TIME, DataTypes.StringType, false)
        });
    }

    @Override
    public DataFrame buildDataFrame(String dataFile) {
        JavaRDD<HashMap<String, String>> rdd = rddBuilder.buildRDD(dataFile);
        JavaRDD<Row> rowRDD = rowRDDBuilder.buildRowRDD(rdd);

        return sqlContext.createDataFrame(rowRDD, createSchema());
    }
}








