package com.br.kafka.buy.consumer.deserializer;

import com.br.kafka.buy.producer.payload.BuyProductPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;


public final class BuyProductPayloadDeserializer implements
                                                  Deserializer<BuyProductPayloadDeserializerHandler> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public BuyProductPayloadDeserializerHandler deserialize(String topic, byte[] data) {
        final var value = new String(data);
        try {
            final var payload = objectMapper.readValue(value, BuyProductPayload.class);
            return new BuyProductPayloadDeserializerHandler(payload, null, value);
        } catch (Throwable e) {
            return new BuyProductPayloadDeserializerHandler(null, e, value);
        }
    }
}
