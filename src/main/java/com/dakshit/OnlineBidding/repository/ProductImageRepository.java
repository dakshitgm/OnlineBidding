package com.dakshit.OnlineBidding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImageRepository, Long> {
    public Optional<byte[]> findByProductId(long productId);
}
