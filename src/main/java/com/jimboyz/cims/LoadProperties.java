package com.jimboyz.cims;

import com.jimboyz.cims.err.ErrorDialog;
import com.jimboyz.cims.err.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

@Deprecated(forRemoval = true, since = "current-version (2.0)")
// Change to StudentImageFile
public class LoadProperties {

    public static String getSaveFilename() {
        Properties properties = new Properties();
        try(FileInputStream fis = new FileInputStream(AppProperties.CIMS_DIR + File.separator + AppProperties.CAPTURED_IMG_FILE)) {
            properties.load(fis);
        } catch (IOException ignored) {}
        return properties.getProperty("app.image.filename");
    }

    public static void deleteConfigFile() {
        try {
            File file = new File(AppProperties.CIMS_DIR + File.separator + AppProperties.CAPTURED_IMG_FILE);
            if(file.exists()) {
//            file.delete();
                Files.delete(file.getAbsoluteFile().toPath());
            }
        } catch (IOException e) {
            Message.showErr(e.getMessage());
        }
    }
}
