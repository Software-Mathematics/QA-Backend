package KafkaAPI.KafkaServices;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilityClass {
    private static final Properties properties=new Properties();


    public static String getProperty(String key) throws IOException {
        FileInputStream inputStream=new FileInputStream("src/main/resources/application.properties");

        properties.load(inputStream);
        return  properties.getProperty(key);
    }


}
