package com.br.kafka.buy.producer.sender;

import java.io.*;
import java.util.concurrent.*;

import com.br.kafka.buy.producer.payload.BuyProductPayload;
import com.br.kafka.buy.producer.sender.exception.SendException;
import org.apache.kafka.clients.producer.*;

public interface BuyEventProductSender extends Closeable {

    Future<RecordMetadata> send(BuyProductPayload payload);

    default RecordMetadata blockingSend(BuyProductPayload payload)
            throws SendException, InterruptedException {
        try {
            return send(payload).get();
        } catch (ExecutionException e) {
            throw new SendException(e.getCause());
        }
    }

    @Override
    void close();

}

