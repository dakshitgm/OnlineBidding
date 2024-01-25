package com.dakshit.OnlineBidding.Services;

import com.dakshit.OnlineBidding.Entity.Bid;
import com.dakshit.OnlineBidding.Entity.Product;
import com.dakshit.OnlineBidding.Entity.User;
import com.dakshit.OnlineBidding.Exception.BidNotFoundException;
import com.dakshit.OnlineBidding.Exception.UnauthorisedAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BidServices {
    public Bid addBid(Bid bid, long userId) throws BidNotFoundException, UnauthorisedAccessException;
    public List<Bid> getBidListByUser(long bidderId);
    public List<Bid> getBidListByProduct(long productId);
    public Bid getBid(long bidId, long userId) throws BidNotFoundException, UnauthorisedAccessException;
    public Bid updateBid(Long bidId, Bid bid) throws BidNotFoundException, UnauthorisedAccessException;
    public void deleteBid(long bidId, long userId) throws BidNotFoundException, UnauthorisedAccessException;
}
