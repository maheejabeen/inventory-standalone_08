package com.example.inventory.controller;

import com.example.inventory.model.Item;
import com.example.inventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final ItemRepository repository;

    @Value("${server.port}")
    private String port;

    public InventoryController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/info")
    public Map<String, String> info() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("service", "inventory-service");
        map.put("servedByPort", port);
        return map;
    }

    @GetMapping
    public List<Item> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Item create(@RequestBody Item item) {
        item.setId(null);
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody Item item) {
        return repository.findById(id).map(existing -> {
            existing.setName(item.getName());
            existing.setQuantity(item.getQuantity());
            existing.setPrice(item.getPrice());
            return ResponseEntity.ok(repository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
