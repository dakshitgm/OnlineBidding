package com.dakshit.OnlineBidding.Services.Impl;

import com.dakshit.OnlineBidding.Entity.Bid;
import com.dakshit.OnlineBidding.Services.BidServices;
import com.dakshit.OnlineBidding.Utils.UserUtils;
import com.dakshit.OnlineBidding.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidServiceImpl implements BidServices {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private UserUtils userUtils;

    @Override
    public Bid addBid(Bid bid) throws Exception {
        if(bid.getProduct().getSeller().getId() == userUtils.getLoggedInUser().getId())
            throw new Exception("cannot bid own product");
        return bidRepository.save(bid);
    }

    @Override
    public List<Bid> getBidListByUser(long bidderId) {
        return bidRepository.findByBidderId(bidderId);
    }

    @Override
    public List<Bid> getBidListByProduct(long productId) {
        return bidRepository.findByProductId(productId);
    }

    @Override
    public Bid getBid(long bidId) throws Exception{
        Optional bidOptional = bidRepository.findById(bidId);
        if(!bidOptional.isPresent()){
            throw new Exception("bid not Found");
        }

        Bid bid = (Bid) bidOptional.get();

        long currentUserId = userUtils.getLoggedInUser().getId();
        if(bid.getBidderId() != currentUserId
                && bid.getProduct().getSeller().getId() != currentUserId){
            throw new Exception("you are unauthorised to see this bid");
        }
        return bidRepository.getById(bidId);
    }

    @Override
    public Bid updateBid(Long bidId, Bid body) throws Exception{
        Optional bidOptional = bidRepository.findById(bidId);

        if(!bidOptional.isPresent()){
            // TODO: create custom exception here
            throw new Exception("bid not found exception");
        }

        Bid bid = (Bid) bidOptional.get();
        if(bid.getBidderId() != body.getBidderId()
                || bid.getProductId() != body.getProductId()){
            // TODO: create custom exception here
            throw new Exception("wrong bidId or product Id provided");
        };

        bid.setPrice(body.getPrice());
        return bidRepository.save(bid);
    }

    @Override
    public void deleteBid(long bidId) throws Exception {
        Optional bidOptional = bidRepository.findById(bidId);

        if(!bidOptional.isPresent()){
            // TODO: create custom exception here
            throw new Exception("bid not found exception");
        }

        Bid bid = (Bid) bidOptional.get();
        if(bid.getBidderId() != userUtils.getLoggedInUser().getId())
            throw new Exception("you are unauthorised to delete this bid");
        bidRepository.deleteById(bidId);
    }
}
