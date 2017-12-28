package football.services;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Row;

import java.util.HashMap;

public interface RowRDDBuilder {
    JavaRDD<Row> buildRowRDD(JavaRDD<HashMap<String, String>> rdd);
}
