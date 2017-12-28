package football.configs;

import football.utils.AutowiredBroadcastBPP;
import football.utils.UdfRegisterApplicationListener;
import lombok.SneakyThrows;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.HashMap;
import java.util.Properties;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"football"})

public class AppConfig {

    @Autowired
    private SparkConf sparkConf;

    @Bean
    public JavaSparkContext sc(){
        return new JavaSparkContext(sparkConf);
    }

    @Bean
    public SQLContext sqlContext() {
        return new SQLContext(sc());
    }

    @Bean
    public AutowiredBroadcastBPP autowiredBroadcastBPP() {
        return new AutowiredBroadcastBPP();
    }

    @Bean
    public UdfRegisterApplicationListener udfRegisterApplicationListener() {
        return new UdfRegisterApplicationListener();
    }

    @Bean
    @SneakyThrows
    public CodeConfig codeConfig(){
        return CodeConfig.builder().codes(PropertiesLoaderUtils.loadAllProperties("codes.properties")).build();
    }

    @Bean
    @SneakyThrows
    public TeamConfig teamConfig(){
        HashMap<String,String> teams  = new HashMap<>();
        Properties teamProperties = PropertiesLoaderUtils.loadAllProperties("teams.properties");
        for (final String Country: teamProperties.stringPropertyNames()){
            String[] players = teamProperties.getProperty(Country).split(",");
            for (String player: players) {
                teams.put(player, Country);
            }
        }
        return TeamConfig.builder().teams(teams).build();
    }
}
