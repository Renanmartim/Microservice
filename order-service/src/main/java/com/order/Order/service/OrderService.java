package com.order.Order.service;

import com.order.Order.config.WebClientConfig;
import com.order.Order.dto.InventoryResponse;
import com.order.Order.dto.OrderLineItemsDto;
import com.order.Order.dto.OrderRequest;
import com.order.Order.model.Order;
import com.order.Order.model.OrderLineItems;
import com.order.Order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

    public ResponseEntity<Order> saveOrder(OrderRequest orderRequest) throws Exception {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsListDto()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItemsList);

        List<String> getSkuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        System.out.println("SKU Codes: " + getSkuCodes);


        InventoryResponse[] inventoryResponses = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", getSkuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        List<InventoryResponse> analyzedResponses = Arrays.asList(inventoryResponses);

        if (analyzedResponses.isEmpty()) {
            throw new IllegalArgumentException("Product not exist in inventory");
        } else {
            orderRepository.save(order);
        }



        return ResponseEntity.ok().body(order);
    }


    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());

        return orderLineItems;
    }


    public ResponseEntity<List<Order>> getAll() {
        List<Order> order = orderRepository.findAll();

        return ResponseEntity.ok().body(order);
    }
}
