package com.inventory.Inventory.service;

import com.inventory.Inventory.dto.InventoryResponse;
import com.inventory.Inventory.model.Inventory;
import com.inventory.Inventory.repository.InventoryRepoistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepoistory inventoryRepoistory;

    public List<InventoryResponse> existSkuCode(List<String> skuCode) {
        List<Inventory> inventory = inventoryRepoistory.findBySkucodeIn(skuCode);

        List<InventoryResponse> responseList = inventory.stream().map(this::mapToInventoryResponse).toList();

        return responseList;
    }

    private InventoryResponse mapToInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .skucode(inventory.getSkucode())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }

}
