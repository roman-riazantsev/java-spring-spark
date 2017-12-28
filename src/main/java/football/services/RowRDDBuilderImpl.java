package football.services;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;

@Service
public class RowRDDBuilderImpl implements RowRDDBuilder, Serializable {
    @Autowired
    private EventBuilder eventBuilder;

    @Override
    public JavaRDD<Row> buildRowRDD(JavaRDD<HashMap<String, String>> rdd) {
        return rdd.
                filter(line -> !line.equals("")).
                map(entries -> eventBuilder.buildEvent(entries)).
                map(event -> RowFactory.create(event.getCode(), event.getFrom(), event.getTo(), event.getEventTime(), event.getStadion(), event.getStartTime()));
    }
}
