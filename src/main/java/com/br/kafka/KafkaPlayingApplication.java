package com.br.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaPlayingApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaPlayingApplication.class, args);
	}

}
