package com.inventory.Inventory.controller;

import com.inventory.Inventory.dto.InventoryResponse;
import com.inventory.Inventory.model.Inventory;
import com.inventory.Inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public List<InventoryResponse> getVerify(@RequestParam List<String> skuCode) {
        return inventoryService.existSkuCode(skuCode);
    }
}
