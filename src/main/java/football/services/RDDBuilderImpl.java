package football.services;

import football.utils.MapUtil;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RDDBuilderImpl implements RDDBuilder {
    @Autowired
    private JavaSparkContext sc;

    @Override
    public JavaRDD<HashMap<String, String>> buildRDD(String dataFile) {
        JavaRDD<String> rdd = sc.textFile(dataFile);
        return rdd.map(MapUtil::getMap);
    }
}
