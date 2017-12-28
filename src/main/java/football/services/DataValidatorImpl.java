package football.services;

import football.aop.LogDataFrame;
import football.validators.Validator;
import org.apache.spark.sql.DataFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataValidatorImpl implements DataValidator {
    @Autowired
    private List<Validator> validators;

    @LogDataFrame
    public DataFrame validateData(DataFrame dataFrame) {
        for (Validator validator : validators) {
            dataFrame = validator.validate(dataFrame);
        }
        return dataFrame;
    }
}
