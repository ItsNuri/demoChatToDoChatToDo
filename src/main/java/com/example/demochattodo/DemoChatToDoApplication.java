package com.example.demochattodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class DemoChatToDoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoChatToDoApplication.class, args);

    }

//    @Bean
//    public ChatMessageService chatMessageService() {
//        return new ChatMessageService();
//    }


}
