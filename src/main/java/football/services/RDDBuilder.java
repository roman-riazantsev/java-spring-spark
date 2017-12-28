package football.services;

import org.apache.spark.api.java.JavaRDD;

import java.util.HashMap;

public interface RDDBuilder {
    JavaRDD<HashMap<String, String>> buildRDD(String dataFile);
}
