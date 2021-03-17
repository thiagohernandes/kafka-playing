package com.br.kafka.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class ProducerBasic {

    final Logger LOGGER = LoggerFactory.getLogger(ProducerBasic.class);

    @Scheduled(fixedDelay = 8000L)
    public void init() {

    }
}
