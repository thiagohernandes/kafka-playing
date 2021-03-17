package com.br.kafka.basic;

import com.br.kafka.basic.constants.BasicConstants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.util.Map;
import java.util.Set;

@Configuration
public class ConsumerBasic {

    final Logger LOGGER = LoggerFactory.getLogger(ConsumerBasic.class);
    final String topic = BasicConstants.TOPIC_BASIC;

    final Map<String, Object> config =
            Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BasicConstants.LOCALHOST_9092,
                    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
                    ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
                    ConsumerConfig.GROUP_ID_CONFIG, BasicConstants.GROUP_ID_CONSUMER_1,
                    ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, BasicConstants.OFFSET_RESET_CONFIG_VALUE,
                    ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Boolean.FALSE);

    @Scheduled(fixedDelay = 5000L)
    public void init(){
        try (var consumer = new KafkaConsumer<String, String>(config)) {
            consumer.subscribe(Set.of(topic));
            final var records = consumer.poll(Duration.ofMillis(500));
            for (var record : records) {
                LOGGER.info("[ConsumerBasic] Valor recebido {}", record.value());
            }
            consumer.commitAsync();
        } catch(Exception e) {
            if (e instanceof KafkaException) {
                LOGGER.error("[ConsumerBaic] Erro com o Kafka - Erro originial: {}", e);
            } else {
                LOGGER.error("[ConsumerBaic] Erro originial: {}", e);
            }
        }
    }

}
