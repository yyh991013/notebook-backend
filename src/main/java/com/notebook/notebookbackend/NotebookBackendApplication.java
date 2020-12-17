package com.notebook.notebookbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 22454
 */
@SpringBootApplication
@MapperScan("com.notebook.notebookbackend.data.database.dao")
public class NotebookBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotebookBackendApplication.class, args);
    }

}
