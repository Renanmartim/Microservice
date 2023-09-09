package com.inventory.Inventory.service;

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

    public boolean existSkuCode(String skucode) {
        Optional<Inventory> inventory = inventoryRepoistory.findBySkucode(skucode);

        return inventory.isPresent();
    }

}
