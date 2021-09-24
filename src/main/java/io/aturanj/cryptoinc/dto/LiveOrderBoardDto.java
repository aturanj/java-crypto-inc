package io.aturanj.cryptoinc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class LiveOrderBoardDto implements Serializable {

    @ApiModelProperty(example = "SELL")
    private String orderType;
    @ApiModelProperty(example = "ETH")
    private String coinType;
    private BigDecimal orderQuantity;
    private BigDecimal price;

    public LiveOrderBoardDto() {
    }

    public LiveOrderBoardDto(String orderType, String coinType, BigDecimal orderQuantity, BigDecimal price) {
        this.orderType = orderType;
        this.coinType = coinType;
        this.orderQuantity = orderQuantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order Type: " + getOrderType() + " " + getOrderQuantity() + " " + getCoinType() + " for " + getPrice();
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
}
