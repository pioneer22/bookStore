package main.java.com.example.bookstore.utils;

public class SearchKeyUtil {
    public static String getStrForMYSQL(String name){
        StringBuilder sb = new StringBuilder();
        sb.append("%");
        for(int i=0; i<name.length(); i++){
            sb.append(name.charAt(i));
            sb.append("%");
        }
        return  sb.toString();
    }
}
