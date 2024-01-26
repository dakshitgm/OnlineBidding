package com.dakshit.OnlineBidding.repository;

import com.dakshit.OnlineBidding.Entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    public ProductImage findByProductId(long productId);
}
