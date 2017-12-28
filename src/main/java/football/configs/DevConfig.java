package football.configs;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static football.configs.Const.DEV;

@Profile(DEV)
@Configuration
public class DevConfig {
    @Bean
    public SparkConf sparkConf(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("songs");
        sparkConf.setMaster("local[*]");
        return sparkConf;
    }
}
