package com.rakshitpatel.firstdemo.service;

import com.rakshitpatel.firstdemo.dto.ProductRequest;
import com.rakshitpatel.firstdemo.dto.ProductResponse;
import com.rakshitpatel.firstdemo.entity.Product;
import com.rakshitpatel.firstdemo.mapper.ProductMapper;
import com.rakshitpatel.firstdemo.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepo productRepo;

    public String createProduct( ProductRequest req ) {
        Product product = productMapper.toProduct(req);

        productRepo.save(product);

        return "Product created!";
    }

    public Product getProduct( Long id ) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        List<ProductResponse> productResponseList = new ArrayList<>();

        for (Product product : productList) {
            productResponseList.add( productMapper.toProductResponse(product) );
        }

        return productResponseList;
    }

    public String updateProduct( ProductRequest req ) {
        Product product = productRepo.findById(req.Id()).orElseThrow(() -> new RuntimeException("Product not found!"));

        product.setName(req.name());
        product.setPrice(req.price());

        productRepo.save(product);

        return "Product updated!";
    }

    public String deleteProduct( Long id ) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found!"));

        productRepo.delete(product);

        return "Product deleted!";
    }

    public List<ProductResponse> get2ProductsByPriceRange( Double minPrice, Double maxPrice ) {
        List<Product> productList = productRepo.findTop2ByPriceBetweenOrderByPriceAsc(minPrice, maxPrice);;
        List<ProductResponse> productResponseList = new ArrayList<>();

        for (Product product : productList) {
            productResponseList.add( productMapper.toProductResponse(product) );
        }

        Collections.reverse(productResponseList);

        return productResponseList;
    }

}
