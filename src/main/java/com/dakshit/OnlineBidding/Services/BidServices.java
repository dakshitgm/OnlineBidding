package com.dakshit.OnlineBidding.Services;

import com.dakshit.OnlineBidding.Entity.Bid;
import com.dakshit.OnlineBidding.Entity.Product;
import com.dakshit.OnlineBidding.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BidServices {
    public Bid addBid(Bid bid) throws Exception;
    public List<Bid> getBidListByUser(long bidderId);
    public List<Bid> getBidListByProduct(long productId);
    public Bid getBid(long bidId) throws Exception;
    public Bid updateBid(Long bidId, Bid bid) throws Exception;
    public void deleteBid(long bidId) throws Exception;
}
