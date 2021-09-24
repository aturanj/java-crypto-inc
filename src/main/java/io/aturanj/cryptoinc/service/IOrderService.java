package io.aturanj.cryptoinc.service;

import io.aturanj.cryptoinc.dto.LiveOrderBoardDto;
import io.aturanj.cryptoinc.dto.OrderDto;
import io.aturanj.cryptoinc.model.Order;
import java.util.List;

public interface IOrderService {

    Order getOrderById(Long id);

    Order createOrder(Order order);

    void deleteOrderLogical(Order order);

    List<OrderDto> getAllOrders();

    List<LiveOrderBoardDto> getLiveOrderBoard();
}
