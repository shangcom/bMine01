package org.zerock.bmine01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // AuditingEntityListener 활성화.
public class BMine01Application {

    public static void main(String[] args) {
        SpringApplication.run(BMine01Application.class, args);
    }

}
