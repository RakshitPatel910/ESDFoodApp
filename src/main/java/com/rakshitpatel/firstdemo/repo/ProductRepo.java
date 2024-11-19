package com.rakshitpatel.firstdemo.repo;

import com.rakshitpatel.firstdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
//    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);

    @Query("select p from Product p where p.price between :minPrice and :maxPrice order by p.price desc limit 2")
    List<Product> findTop2ByPriceBetweenOrderByPriceAsc(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}
