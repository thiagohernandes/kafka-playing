package com.br.kafka.buy.database;

import com.br.kafka.buy.dto.BuyProductDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BuyProductDatabase {

    public static List<BuyProductDto> databaseProducts =
            Arrays.asList(new BuyProductDto(1, "PRODUCT 1", 10),
                          new BuyProductDto(2, "PRODUCT 2", 4),
                          new BuyProductDto(3, "PRODUCT 3", 2));

    public static BuyProductDto findProductById(Integer id) {
        return databaseProducts.stream()
                .filter(product -> product.getId().equals(id)).
                        findFirst().orElse(null);
    }

    public static void sellProduct(BuyProductDto buyProductDto, Integer quantity) {
        databaseProducts = databaseProducts.stream()
                .filter(product -> !product.getId().equals(buyProductDto.getId())).collect(Collectors.toList());
        databaseProducts.add(new BuyProductDto(buyProductDto.getId(), buyProductDto.getDescription(),
                buyProductDto.getQuantity() - quantity));
    }

}
