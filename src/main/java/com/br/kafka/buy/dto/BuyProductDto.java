package com.br.kafka.buy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BuyProductDto {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("quantity")
    private Integer quantity;

    public BuyProductDto(Integer id, String description, Integer quantity) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BuyProductDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
