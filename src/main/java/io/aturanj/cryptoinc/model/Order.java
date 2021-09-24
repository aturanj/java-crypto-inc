package io.aturanj.cryptoinc.model;

import io.aturanj.cryptoinc.dto.LiveOrderBoardDto;
import io.aturanj.cryptoinc.dto.OrderDto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "orders")
@SqlResultSetMapping(
        name = "liveOrderBoardConditionalResult",
        classes = {
            @ConstructorResult(
                    targetClass = io.aturanj.cryptoinc.dto.LiveOrderBoardDto.class,
                    columns = {
                        @ColumnResult(name = "orderType"),
                        @ColumnResult(name = "coinType"),
                        @ColumnResult(name = "orderQuantity", type = BigDecimal.class),
                        @ColumnResult(name = "price", type = BigDecimal.class)
                    })})
@NamedNativeQuery(
        name = "Order.getLiveOrderBoardConditional",
        query = "SELECT O.ORDER_TYPE as orderType, O.COIN_TYPE as coinType, O.PRICE as price, SUM(O.ORDER_QUANTITY) as orderQuantity FROM ORDERS O "
        + "WHERE O.DELETED=FALSE "
        + "GROUP BY O.ORDER_TYPE, O.COIN_TYPE, O.PRICE "
        + "ORDER BY CASE WHEN O.ORDER_TYPE='BUY' THEN O.PRICE END DESC, CASE WHEN O.ORDER_TYPE='SELL' THEN O.PRICE END ASC, O.order_date_time DESC;",
        resultSetMapping = "liveOrderBoardConditionalResult")
public class Order implements Serializable {

    private static final long serialVersionUID = 3252591505029724246L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    @Min(value = 0, message = "User Id should not be less than 0")
    private Integer userId;
    @Column(name = "order_type")
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    @Column(name = "coin_type")
    @Enumerated(EnumType.STRING)
    private CoinType coinType;
    @Column(name = "order_quantity")
    private BigDecimal orderQuantity;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "order_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime orderDateTime = LocalDateTime.now();
    @Column(name = "deleted")
    private Boolean deleted = false;

    public Order() {
    }

    public Order(int userId, OrderType orderType, CoinType coinType, BigDecimal orderQuantity, BigDecimal price) {
        this.userId = userId;
        this.orderType = orderType;
        this.coinType = coinType;
        this.orderQuantity = orderQuantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public CoinType getCoinType() {
        return coinType;
    }

    public void setCoinType(CoinType coinType) {
        this.coinType = coinType;
    }

    public Double getOrderQuantityDouble() {
        return orderQuantity.doubleValue();
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

    @Override
    public String toString() {
        return "Order Type: " + getOrderType() + " " + getOrderQuantity() + " " + getCoinType() + " for " + getPrice();
    }

    public LiveOrderBoardDto toLiveOrderBoardDto() {
        var liveOrderBoardDto = new LiveOrderBoardDto();
        liveOrderBoardDto.setCoinType(this.getCoinType().label);
        liveOrderBoardDto.setOrderQuantity(this.getOrderQuantity());
        liveOrderBoardDto.setOrderType(this.getOrderType().label);
        liveOrderBoardDto.setPrice(this.getPrice());
        return liveOrderBoardDto;
    }

    public OrderDto toOrderDto() {
        var orderDto = new OrderDto();
        orderDto.setCoinType(this.getCoinType().label);
        orderDto.setDeleted(this.isDeleted());
        orderDto.setOrderQuantity(this.getOrderQuantity());
        orderDto.setOrderType(this.getOrderType().label);
        orderDto.setPrice(this.getPrice());
        orderDto.setUserId(this.getUserId());
        orderDto.setOrderDateTime(this.getOrderDateTime());
        return orderDto;
    }
}
