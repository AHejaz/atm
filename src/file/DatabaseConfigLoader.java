package file;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class DatabaseConfigLoader {

    private static Properties prop = new Properties();

    static {
        InputStream in;
        try {
            in = new FileInputStream("E:\\Projects\\java spring camp\\" +
                    "week_5_practice_java_ATM_OOP_Log_Exception_Collection_TAhalKard\\" +
                    "src\\file\\database_config.properties");
            prop.load(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMessage(String key){
        String message = prop.getProperty(key);
        return message;
    }
}