package file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoadConfigurationLogService {
    private static Logger logger = Logger.getLogger(LoadConfigurationLogService.class.getName());

    public static void load() {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src\\file\\logger.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
