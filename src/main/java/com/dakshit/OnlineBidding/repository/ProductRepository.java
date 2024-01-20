package com.dakshit.OnlineBidding.repository;

import com.dakshit.OnlineBidding.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
