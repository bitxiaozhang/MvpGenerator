package com.util;

import com.MvpGeneratorAction;
import com.intellij.openapi.actionSystem.AnAction;

import java.io.*;

public class FileUtil {

    public static String readTemplateFile(String fileName){
        String content = "";
        InputStream in = null;
        in = MvpGeneratorAction.class.getResourceAsStream("./template/" + fileName);
        try {
            content = new String(readStream(in));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }


    public static void writeToFile(String content, String classPath, String className) {
        try {
            File floder = new File(classPath);
            if (!floder.exists()){
                floder.mkdirs();
            }

            File file = new File(classPath + File.separator + className);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static byte[] readStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        try {
            while ((len = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            outputStream.close();
            inputStream.close();
        }

        return outputStream.toByteArray();
    }
}
