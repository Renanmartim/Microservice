package com.inventory.Inventory;

import com.inventory.Inventory.model.Inventory;
import com.inventory.Inventory.repository.InventoryRepoistory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@SpringBootApplication
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

    @Bean
    public CommandLineRunner update(InventoryRepoistory inventoryRepoistory) {
        return args -> {
            var inventory1 = new Inventory();
            inventory1.setName("Iphone_14");
            inventory1.setSkucode("Iphone_14");
            inventory1.setQuantity(100L);

            var inventory2 = new Inventory();
            inventory2.setName("Iphone_15");
            inventory2.setSkucode("Iphone_15");
            inventory2.setQuantity(10L);

            inventoryRepoistory.save(inventory1);
            inventoryRepoistory.save(inventory2);
        };
    }
}
