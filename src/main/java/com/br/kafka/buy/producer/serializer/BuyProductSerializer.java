package com.br.kafka.buy.producer.serializer;

import com.br.kafka.buy.producer.payload.BuyProductPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public final class BuyProductSerializer implements Serializer<BuyProductPayload> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final class MarshallingException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        private MarshallingException(Throwable cause) { super(cause); }
    }

    @Override
    public byte[] serialize(String topic, BuyProductPayload data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new MarshallingException(e);
        }
    }
}
