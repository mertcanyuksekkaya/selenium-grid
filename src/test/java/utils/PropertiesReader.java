package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertiesReader {
    private static Properties properties;
    private static final String file;

    static {
//        if you are using java build, give -Dpackage=docker on the command line
        if (System.getProperty("package") == null){
            file = System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"properties"+File.separator+"configuration.properties";
        }else{
            file = System.getProperty("user.dir")+File.separator+"properties"+File.separator+"configuration.properties";
        }
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);
            properties.load(new InputStreamReader(fileInputStream, Charset.forName("UTF-8")));
            fileInputStream.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static String getParameter(String parameter){
        if (System.getProperty(parameter) != null) {
            return System.getProperty(parameter);
        } else {
            return properties.getProperty(parameter);
        }
    }

    public static String setParameter(String key,String value){
        if (System.getProperty(value) != null) {
            System.setProperty(key,value);
            return System.getProperty(key);
        } else {
            properties.setProperty(key,value);
            return  properties.getProperty(key);
        }
    }


}
