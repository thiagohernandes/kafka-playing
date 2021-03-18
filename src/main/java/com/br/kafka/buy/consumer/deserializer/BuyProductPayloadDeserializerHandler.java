package com.br.kafka.buy.consumer.deserializer;

import com.br.kafka.buy.producer.payload.BuyProductPayload;

public class BuyProductPayloadDeserializerError {

    private final BuyProductPayload productPayload;
    private final Throwable error;
    private String encodedValue;

    public BuyProductPayloadDeserializerError(BuyProductPayload productPayload,
                                              Throwable error,
                                              String encodedValue){
        this.productPayload = productPayload;
        this.error = error;
        this.encodedValue = encodedValue;
    }

    public BuyProductPayload getProductPayload() {
        return productPayload;
    }

    public Throwable getError() {
        return error;
    }

    public String getEncodedValue() {
        return encodedValue;
    }

    @Override
    public String toString() {
        return "BuyProductPayloadDeserializerError{" +
                "productPayload=" + productPayload +
                ", error=" + error +
                ", encodedValue='" + encodedValue + '\'' +
                '}';
    }
}
