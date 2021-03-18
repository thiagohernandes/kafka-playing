package com.br.kafka.common;

import org.apache.kafka.common.KafkaException;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CommonExceptionHandler {

    public void handleExceptionKafka(Logger logger, String className, Exception e){
        if (e instanceof KafkaException) {
            logger.error("[{}] Erro com o Kafka: " + e.getMessage(), className);
        } else {
            logger.error("[{}] Erro original: " + e.getMessage(), className, e);
        }
    }

}
