package io.aturanj.cryptoinc.dto;

import io.aturanj.cryptoinc.model.CoinType;
import io.aturanj.cryptoinc.model.Order;
import io.aturanj.cryptoinc.model.OrderType;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Hidden;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDto {

    private Integer userId;
    @ApiModelProperty(example = "SELL")
    private String orderType;
    @ApiModelProperty(example = "ETH")
    private String coinType;
    private BigDecimal orderQuantity;
    private BigDecimal price;
    private LocalDateTime orderDateTime;
    @ApiModelProperty(example = "false")
    private Boolean deleted = false;

    public OrderDto() {
    }

    public OrderDto(Integer userId, String orderType, String coinType, BigDecimal orderQuantity, BigDecimal price) {
        this.userId = userId;
        this.orderType = orderType;
        this.coinType = coinType;
        this.orderQuantity = orderQuantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order Type: " + getOrderType() + " " + getOrderQuantity() + " " + getCoinType() + " for " + getPrice();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    @Hidden
    public Order toOrder() {
        var order = new Order();
        order.setCoinType(CoinType.valueOf(this.getCoinType()));
        order.setDeleted(this.isDeleted());
        order.setOrderQuantity(this.getOrderQuantity());
        order.setOrderType(OrderType.valueOf(this.getOrderType()));
        order.setPrice(this.getPrice());
        order.setUserId(this.getUserId());
        return order;
    }
}
