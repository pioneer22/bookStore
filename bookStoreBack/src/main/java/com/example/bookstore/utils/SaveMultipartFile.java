package main.java.com.example.bookstore.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveMultipartFile {

    public static String addImg(MultipartFile file) {
        try {
//            String basePath = request.getServletContext().getRealPath("/static/headImg/");
            String basePath = "E:/inteillJProject/bookstore/src/main/resources/static/Img/";
            String fileName = file.getOriginalFilename();//文件原名
            // 判断文件类型
            String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;

            //时间自定义图片名称
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String trueFileName = sdf.format(date)+ "." + type;
            File directory = new File(basePath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            file.transferTo(new File(basePath + trueFileName));
            return trueFileName;
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
            return null;
        }

    }
}
