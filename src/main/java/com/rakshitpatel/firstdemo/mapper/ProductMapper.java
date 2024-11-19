package com.rakshitpatel.firstdemo.mapper;

import com.rakshitpatel.firstdemo.dto.ProductRequest;
import com.rakshitpatel.firstdemo.dto.ProductResponse;
import com.rakshitpatel.firstdemo.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest req) {
        return Product.builder()
                .id(req.Id())
                .name(req.name())
                .price(req.price())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

}
