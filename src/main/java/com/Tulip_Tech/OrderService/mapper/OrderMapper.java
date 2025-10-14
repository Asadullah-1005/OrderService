package com.Tulip_Tech.OrderService.mapper;

import com.Tulip_Tech.OrderService.entity.OrderEntity;
import com.Tulip_Tech.OrderService.model.Dto.CreateOrderRequest;
import com.Tulip_Tech.OrderService.model.domain.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {



    public Order EntityToOrder(OrderEntity orderEntity) {
        Order order = new Order();
        BeanUtils.copyProperties(orderEntity, order);
        return order;
    }

    public OrderEntity createOrderEntity(CreateOrderRequest createOrderRequest) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(createOrderRequest, orderEntity);
        orderEntity.setOrderStatus("CREATED");
        orderEntity.setOrderDate(java.time.Instant.now());
        return orderEntity;
    }

}
