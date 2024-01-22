package com.dakshit.OnlineBidding.repository;

import com.dakshit.OnlineBidding.Entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    public List<Bid> findByBidderId(long userId);
    public List<Bid> findByProductId(long productId);
}
