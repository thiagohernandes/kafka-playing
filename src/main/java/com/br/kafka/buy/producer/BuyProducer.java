package com.br.kafka.buy.producer;

import com.br.kafka.buy.constants.BuyConstants;
import com.br.kafka.buy.database.BuyProductDatabase;
import com.br.kafka.buy.dto.BuyProductDto;
import com.br.kafka.buy.producer.payload.BuyProductPayload;
import com.br.kafka.buy.producer.sender.BuyDirectProductSender;
import com.br.kafka.common.CommonExceptionHandler;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;

@Configuration
public class BuyProducer {

    private final String TOPICO_COMPRA = BuyConstants.TOPIC_COMPRA;
    private final Logger LOGGER = LoggerFactory.getLogger(BuyProducer.class);
    private CommonExceptionHandler commonExceptionHandler;
    private final Map<String, Object> CONFIG =
            Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                    ProducerConfig.CLIENT_ID_CONFIG, BuyConstants.CLIENT_ID_COMPRA_PRODUTO);

    @Autowired
    public BuyProducer(CommonExceptionHandler commonExceptionHandler) {
        this.commonExceptionHandler = commonExceptionHandler;
    }

    @Scheduled(fixedDelay = 5000L)
    public void init() {
        LOGGER.info("[BuyProducer] Iniciando processo de compra de produto...");
        try (var sender = new BuyDirectProductSender(CONFIG, TOPICO_COMPRA)) {
            BuyProductDto product = BuyProductDatabase.findProductById(1);
            BuyProductDatabase.sellProduct(product, 3);
            sender.send(new BuyProductPayload(product.getId(), product.getDescription(),
                    product.getQuantity()));
            LOGGER.info("[BuyProducer] Produto {} enviado com sucesso!", product.toString());
        } catch(Exception e) {
            commonExceptionHandler
                    .handleExceptionKafka(LOGGER, BuyProducer.class.getSimpleName(), e);
        }
    }
}
