package com.util;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.util.Properties;

public class PropertiesUtil {

    public static String get(String key){
        String resultValue = "";
        if (key == null || key.length() == 0){
            return resultValue;
        }

        Properties pps = new Properties();
        File file = new File("../mvp.properties");

        try {
            if (!file.exists()) {
                 file.createNewFile();
            }
        } catch (IOException e) {
                e.printStackTrace();
            }

        try {
            pps.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultValue = (String) pps.get(key);

        return resultValue;
    }


    public static void put(String key,String value){
        if (key == null || value == null) return;

        Properties pps = new Properties();
        File file = new File("../mvp.properties");

        try{
            if (!file.exists()){
                file.createNewFile();
            }
            InputStream in = new FileInputStream(file);
            pps.load(in);
            OutputStream out = new FileOutputStream(file);
            pps.setProperty(key,value);
            pps.store(out,"uppdate " + key + ":" + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final class KEYS{
        public static final String BASE_ACTIVITY_PACKAGE = "base_activity_package";
    }
}
