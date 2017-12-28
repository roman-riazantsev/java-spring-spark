package football.aop;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.apache.spark.sql.DataFrame;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j
public class DataFrameLogger {
    @SneakyThrows
    @AfterReturning(value = "@annotation(LogDataFrame)", returning = "output")
    public void log(JoinPoint jointPoint, Object output) {
        DataFrame dataFrame = (DataFrame) output;
        log.warn("JointPoint: " + jointPoint);
        log.warn("returned DataFrame:");
        dataFrame.show();
    }
}
