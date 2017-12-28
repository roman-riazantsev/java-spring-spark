package football.utils;

import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;

import java.util.HashMap;
import java.util.Map;

class TypeUtil {
    private static Map<String, DataType> sparkTypes = new HashMap<String, DataType>() {{
        put("java.lang.String", DataTypes.StringType);
        put("java.lang.Boolean", DataTypes.IntegerType);
        put("java.lang.Boolean", DataTypes.BooleanType);
    }};

    static DataType getSparkDataType(String type) {
        return sparkTypes.get(type);
    }
}
