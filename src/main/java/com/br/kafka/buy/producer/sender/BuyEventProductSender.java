package com.br.kafka.buy.producer.sender;

import java.io.*;
import java.util.concurrent.*;

import com.br.kafka.buy.producer.payload.BuyProductPayload;
import com.br.kafka.buy.producer.sender.exception.BuySendException;
import org.apache.kafka.clients.producer.*;

public interface BuyEventProductSender extends Closeable {

    Future<RecordMetadata> send(BuyProductPayload payload);

    default RecordMetadata blockingSend(BuyProductPayload payload)
            throws BuySendException, InterruptedException {
        try {
            return send(payload).get();
        } catch (ExecutionException e) {
            throw new BuySendException(e.getCause());
        }
    }

    @Override
    void close();

}

