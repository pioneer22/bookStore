package main.java.com.example.bookstore.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadTxtFile {

    public static String readTxt(String filePath) {
        StringBuilder result = new StringBuilder();
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
                BufferedReader br = new BufferedReader(isr);
                String lineTxt = null;
                while ((lineTxt = br.readLine()) != null) {
                    result.append(System.lineSeparator()+lineTxt);
                }
                br.close();
            } else {
                System.out.println("文件不存在!");
            }
        } catch (Exception e) {
            System.out.println("文件读取错误!");
        }
        return result.toString();
    }

}
