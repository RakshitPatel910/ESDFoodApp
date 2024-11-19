package com.rakshitpatel.firstdemo.controller;

import com.rakshitpatel.firstdemo.dto.ProductRequest;
import com.rakshitpatel.firstdemo.dto.ProductResponse;
import com.rakshitpatel.firstdemo.mapper.ProductMapper;
import com.rakshitpatel.firstdemo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductResponse> getProducts(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productMapper.toProductResponse(productService.getProduct(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("{minPrice}-{maxPrice}")
    public ResponseEntity<List<ProductResponse>> get2ProductsByPriceRange(@PathVariable("minPrice") @Valid Double minPrice, @PathVariable("maxPrice") @Valid Double maxPrice) {
        return ResponseEntity.ok(productService.get2ProductsByPriceRange(minPrice, maxPrice));
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.updateProduct(productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") @Valid Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

}
