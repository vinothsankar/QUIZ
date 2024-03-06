package com.question.quizserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizServerApplication.class, args);
	}

}
