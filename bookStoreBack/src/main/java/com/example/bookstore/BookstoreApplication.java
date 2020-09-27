package main.java.com.example.bookstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.bookstore.dao")
public class BookstoreApplication {
	public static void main(String[] args)
	{
		SpringApplication.run(BookstoreApplication.class, args);
	}

}
