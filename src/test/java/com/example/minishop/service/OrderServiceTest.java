package com.example.minishop.service;

import com.example.minishop.model.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class OrderServiceTest {
    OrderService orderService = new OrderService();

    @Test
    public void insert() {
        List<Order> orders = Arrays.asList(Order.builder()
                        .userid("oognuyh")
                        .addr1("1")
                        .addr2("1")
                        .gAmount(1)
                        .gCode("O1")
                        .gColor("white")
                        .orderName("강현구")
                        .gSize("S")
                        .payMethod("debit")
                        .gPrice(12345)
                        .post("15821")
                        .gImage("dress1")
                        .phone("12")
                        .gName("ASD")
                        .build(),
                Order.builder()
                        .userid("oognuyh")
                        .addr1("1")
                        .addr2("1")
                        .gAmount(1)
                        .gCode("O1")
                        .gColor("white")
                        .orderName("강현구")
                        .gSize("S")
                        .payMethod("debit")
                        .gPrice(12345)
                        .post("15821")
                        .gImage("dress1")
                        .phone("12")
                        .gName("ASD")
                        .build());

        // orders.forEach(orderService::insertOrder);

        orderService.findAllOrders().forEach(System.out::println);
        orderService.findOrdersByUserId("oognuyh").forEach(System.out::println);
    }
}