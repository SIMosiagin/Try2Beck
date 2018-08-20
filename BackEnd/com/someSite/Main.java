package com.someSite;

import com.someSite.entity.firstApplication.FileExcel;
import com.someSite.entity.secondApplication.SDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class })
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public FileExcel getFileExcel(){
        return new FileExcel();
    }

    @Bean
    public SDB getDao(){
        return new SDB();
    }


}
