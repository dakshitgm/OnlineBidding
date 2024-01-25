package com.dakshit.OnlineBidding.repository;

import com.dakshit.OnlineBidding.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findBySellerId(long sellerId);
}
