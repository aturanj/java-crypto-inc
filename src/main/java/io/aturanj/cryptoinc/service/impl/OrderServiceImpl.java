package io.aturanj.cryptoinc.service.impl;

import io.aturanj.cryptoinc.dto.LiveOrderBoardDto;
import io.aturanj.cryptoinc.dto.OrderDto;
import io.aturanj.cryptoinc.model.Order;
import io.aturanj.cryptoinc.repository.OrderRepository;
import io.aturanj.cryptoinc.service.IOrderService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrderLogical(Order order) {
        order.setDeleted(true);// soft delete
        orderRepository.save(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        var orders = orderRepository.findAll();
        return orders
                .stream()
                .map(order -> order.toOrderDto())
                .collect(Collectors.toList());
    }

    @Override
    public List<LiveOrderBoardDto> getLiveOrderBoard() {
        var liveOrderBoardConditionalResult = orderRepository.getLiveOrderBoardConditional();

        return liveOrderBoardConditionalResult
                .stream()
                .limit(10)
                .collect(Collectors.toList());
    }
}
