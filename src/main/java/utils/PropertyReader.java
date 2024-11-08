package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static String getProperty(String path, String key) throws IOException {
        String value;
        InputStream file = new FileInputStream(path);
        Properties property = new Properties();
        property.load(file);
        System.out.println("Property value is "+ property.getProperty(key));
        value = property.getProperty(key);
        return value;
    }
}
