package football.utils;

import lombok.SneakyThrows;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.api.java.UDF3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Collection;

public class UdfRegisterApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private SQLContext sqlContext;

    @Override
    @SneakyThrows
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Collection<Object> udfObjects = context.getBeansWithAnnotation(UDF.class).values();

        for (Object udfObject : udfObjects) {

            /*
            *  Both .getMethods("call") and .getDeclaredMethod("call") hits the NoSuchMethodException,
            *  for now i decided to choose .getMethods()[0].
            *  Even though it fails with log4j, since call method returns Object in such case ...
            *  But I'm in the process of finding a better solution.
            */

            String javaType = udfObject.getClass().getMethods()[0].getReturnType().getName();
            Class<?>[] parametersTypes = udfObject.getClass().getMethods()[0].getParameterTypes();
            int amountOfParameters = parametersTypes.length;

            if (amountOfParameters == 1) {
                sqlContext.udf().register(udfObject.getClass().getName(),
                        (UDF1<?, ?>) udfObject,
                        TypeUtil.getSparkDataType(javaType));
            } else if (amountOfParameters == 3) {
                sqlContext.udf().register(udfObject.getClass().getName(),
                        (UDF3<?, ?, ?, ?>) udfObject,
                        TypeUtil.getSparkDataType(javaType));
            }
        }
    }
}
