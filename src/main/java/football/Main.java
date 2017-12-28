package football;

import football.configs.AppConfig;
import football.services.DataManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static football.configs.Const.DEV;
import static football.configs.Const.PROD;

public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", DEV);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        DataManager dataManager = context.getBean(DataManager.class);
        dataManager.manageData();
    }
}
