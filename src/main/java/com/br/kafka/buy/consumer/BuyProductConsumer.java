package com.br.kafka.buy.consumer;

import com.br.kafka.buy.constants.BuyConstants;
import com.br.kafka.buy.consumer.deserializer.BuyProductPayloadDeserializer;
import com.br.kafka.buy.dto.BuyProductDto;
import com.br.kafka.common.CommonExceptionHandler;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.util.Map;
import java.util.Set;

@Configuration
public class BuyProductConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(BuyProductConsumer.class);
    private final String TOPIC = BuyConstants.TOPIC_COMPRA;
    private final String GROUP_ID = BuyConstants.GROUP_ID_COMPRA;
    private CommonExceptionHandler commonExceptionHandler;
    private final Map<String, Object> CONFIG =
            Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
                    ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, BuyProductPayloadDeserializer.class.getName(),
                    ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID,
                    ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest",
                    ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false,
                    ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
    @Autowired
    public BuyProductConsumer(CommonExceptionHandler commonExceptionHandler) {
        this.commonExceptionHandler = commonExceptionHandler;
    }

    @Scheduled(fixedDelay = 5000L)
    public void init(){
        try (var consumer = new KafkaConsumer<String, BuyProductDto>(CONFIG)) {
            consumer.subscribe(Set.of(TOPIC));
            final var records = consumer.poll(Duration.ofMillis(5000l));
            for (var record : records) {
                LOGGER.info("[BuyProductConsumer] Recebendo o registro: [KEY] {} e [VALUE] {}",
                        record.key(), record.value());
                Gson gson = new Gson();
                BuyProductDto productDto = gson.fromJson(String.valueOf(record.value()), BuyProductDto.class);
                LOGGER.warn("[BuyProductConsumer] Produto id -> {} - descrição -> {} - quantidade -> {} ",
                        productDto.getId(), productDto.getDescription(), productDto.getQuantity());
            }
            consumer.commitAsync();
        } catch(Exception e) {
            commonExceptionHandler
                    .handleExceptionKafka(LOGGER, BuyProductConsumer.class.getSimpleName(), e);
        }
    }
}
