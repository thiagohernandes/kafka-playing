//package com.br.kafka.basic.producer;
//
//import com.br.kafka.basic.constants.BasicConstants;
//import com.br.kafka.common.BasicExceptionHandler;
//import org.apache.kafka.clients.producer.Callback;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.util.Date;
//import java.util.Map;
//
//@Configuration
//public class BasicProducer {
//
//    private final Logger LOGGER = LoggerFactory.getLogger(BasicProducer.class);
//    private final String TOPIC = BasicConstants.TOPIC_BASIC;
//    private BasicExceptionHandler exceptionHandler;
//    private final Map<String, Object> CONFIG =
//            Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BasicConstants.LOCALHOST_9092,
//                    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
//                    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
//                    ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, Boolean.TRUE);
//
//    @Autowired
//    public BasicProducer(final BasicExceptionHandler exceptionHandler) {
//        this.exceptionHandler = exceptionHandler;
//    }
//
//    @Scheduled(fixedDelay = 2000L)
//    public void init() {
//        try (var producer = new KafkaProducer<String, String>(CONFIG)) {
//            final var key = BasicConstants.KEY_BASIC;
//            final var value =  new Date().toString();
//            LOGGER.info("[ProducerBasic] Publicando o registro [CHAVE] {} - [VALOR] {}", key, value);
//            final Callback callback = (metaData, exception) -> {
//                LOGGER.warn("[ProducerBasic] Callback: [METADATA] {}", metaData);
//            };
//            producer.send(new ProducerRecord<>(TOPIC, key, value), callback);
//        } catch(Exception e) {
//            exceptionHandler.handleExceptionKafka(LOGGER, this.getClass().getSimpleName() , e);
//        }
//    }
//}
