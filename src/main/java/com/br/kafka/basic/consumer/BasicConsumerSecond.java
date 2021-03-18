//package com.br.kafka.basic.consumer;
//
//import com.br.kafka.basic.constants.BasicConstants;
//import com.br.kafka.common.BasicExceptionHandler;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.time.Duration;
//import java.util.Map;
//import java.util.Set;
//
//@Configuration
//public class BasicConsumerSecond {
//
//    private final Logger LOGGER = LoggerFactory.getLogger(BasicConsumerSecond.class);
//    private final String TOPPIC = BasicConstants.TOPIC_BASIC;
//    private BasicExceptionHandler exceptionHandler;
//    private final Map<String, Object> CONFIG =
//            Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BasicConstants.LOCALHOST_9092,
//                    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
//                    ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
//                    ConsumerConfig.GROUP_ID_CONFIG, BasicConstants.GROUP_ID_CONSUMER_2,
//                    ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, BasicConstants.OFFSET_RESET_CONFIG_VALUE,
//                    ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Boolean.FALSE);
//
//    @Autowired
//    public BasicConsumerSecond(final BasicExceptionHandler exceptionHandler) {
//        this.exceptionHandler = exceptionHandler;
//    }
//
//    @Scheduled(fixedDelay = 1000L)
//    public void init() {
//        try (var consumer = new KafkaConsumer<String, String>(CONFIG)) {
//            consumer.subscribe(Set.of(TOPPIC));
//            final var records = consumer.poll(Duration.ofMillis(8000L));
//            for (var record : records) {
//                LOGGER.info("[ConsumerBasicTwo] Valor recebido {}", record.value());
//            }
//            consumer.commitAsync();
//        } catch(Exception e) {
//            exceptionHandler
//                    .handleExceptionKafka(LOGGER, BasicConsumerSecond.class.getSimpleName() , e);
//        }
//    }
//}
