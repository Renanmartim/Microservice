package com.inventory.Inventory.repository;

import com.inventory.Inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepoistory extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findBySkucode(String skucode);
}
