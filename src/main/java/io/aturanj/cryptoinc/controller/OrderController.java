package io.aturanj.cryptoinc.controller;

import io.aturanj.cryptoinc.dto.LiveOrderBoardDto;
import io.aturanj.cryptoinc.dto.OrderDto;
import io.aturanj.cryptoinc.dto.StringResponseDto;
import io.aturanj.cryptoinc.exception.BadRequestException;
import io.aturanj.cryptoinc.model.Order;
import io.aturanj.cryptoinc.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Orders", tags = {"Orders"})
public class OrderController {

    private final IOrderService iOrderService;

    @Autowired
    public OrderController(IOrderService iOrderService) {
        this.iOrderService = iOrderService;
    }

    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<StringResponseDto> takePlaceOrder(@RequestBody OrderDto orderDto) {
        if (orderDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            try {
                var order = iOrderService.createOrder(orderDto.toOrder());
                return new ResponseEntity<>(new StringResponseDto(order.getId().toString()), HttpStatus.OK);
            } catch (BadRequestException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StringResponseDto(ex.getMessage()));
            }
        }
    }

    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        var accountList = iOrderService.getAllOrders();
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }

    @GetMapping(value = "/liveorderboard", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LiveOrderBoardDto>> getLiveOrderBoard() {
        var liveOrderBoard = iOrderService.getLiveOrderBoard();
        return new ResponseEntity<>(liveOrderBoard, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiParam(name = "id", example = "1", required = true)
    public ResponseEntity deleteOrderLogical(@PathVariable("id") Long id) {

        Order order = iOrderService.getOrderById(id);

        if (order == null || order.isDeleted()) {
            return new ResponseEntity<>(new StringResponseDto("id: " + id), HttpStatus.NOT_FOUND);
        } else {
            iOrderService.deleteOrderLogical(order);
            return new ResponseEntity<>(new StringResponseDto("id: " + id), HttpStatus.OK);
        }
    }
}
