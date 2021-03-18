package com.br.kafka.buy.producer.sender;

import java.util.*;
import java.util.concurrent.*;

import com.br.kafka.buy.producer.payload.BuyProductPayload;
import com.br.kafka.buy.producer.serializer.BuyProductSerializer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.*;

public final class BuyDirectProductSender implements BuyEventProductSender {
    private final Producer<String, BuyProductPayload> producer;
    private final String topic;

    public BuyDirectProductSender(Map<String, Object> producerConfig, String topic) {
        this.topic = topic;
        final var mergedConfig = new HashMap<String, Object>();
        mergedConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        mergedConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, BuyProductSerializer.class.getName());
        mergedConfig.putAll(producerConfig);
        producer = new KafkaProducer<>(mergedConfig);
    }

    @Override
    public Future<RecordMetadata> send(BuyProductPayload payload) {
        final var record = new ProducerRecord<>(topic, payload.getId().toString(), payload);
        return producer.send(record);
    }

    @Override
    public void close() {
        producer.close();
    }
}
