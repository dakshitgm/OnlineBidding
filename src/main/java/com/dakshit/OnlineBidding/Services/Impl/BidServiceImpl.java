package com.dakshit.OnlineBidding.Services.Impl;

import com.dakshit.OnlineBidding.Entity.Bid;
import com.dakshit.OnlineBidding.Exception.BidNotFoundException;
import com.dakshit.OnlineBidding.Exception.UnauthorisedAccessException;
import com.dakshit.OnlineBidding.Services.BidServices;
import com.dakshit.OnlineBidding.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidServiceImpl implements BidServices {

    @Autowired
    private BidRepository bidRepository;


    @Override
    public Bid addBid(Bid bid, long userId) throws UnauthorisedAccessException {
        if(bid.getProduct().getSellerId() == userId)
            throw new UnauthorisedAccessException("cannot bid own product");
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
    public Bid getBid(long bidId, long userId) throws BidNotFoundException, UnauthorisedAccessException{
        Optional bidOptional = bidRepository.findById(bidId);
        if(!bidOptional.isPresent())
            throw new BidNotFoundException("Bid Not found with this bidId");


        Bid bid = (Bid) bidOptional.get();


        if(bid.getBidderId() != userId
                && bid.getProduct().getSeller().getId() != userId){
            throw new UnauthorisedAccessException("you are unauthorised to see this bid");
        }
        return bidRepository.getById(bidId);
    }

    @Override
    public Bid updateBid(Long bidId, Bid body) throws BidNotFoundException, UnauthorisedAccessException {
        Optional bidOptional = bidRepository.findById(bidId);

        if(!bidOptional.isPresent())
            throw new BidNotFoundException("Bid not found with id " + bidId);


        Bid bid = (Bid) bidOptional.get();
        if(bid.getBidderId() != body.getBidderId()
                || bid.getProductId() != body.getProductId())
            throw new UnauthorisedAccessException("wrong bidId or productId provided");


        bid.setPrice(body.getPrice());
        return bidRepository.save(bid);
    }

    @Override
    public void deleteBid(long bidId, long userId) throws BidNotFoundException, UnauthorisedAccessException {
        Optional bidOptional = bidRepository.findById(bidId);

        if(!bidOptional.isPresent())
            throw new BidNotFoundException("Bid not found with bidId "  + bidId);


        Bid bid = (Bid) bidOptional.get();
        if(bid.getBidderId() != userId)
            throw new UnauthorisedAccessException("you are unauthorised to change this bid");
        bidRepository.deleteById(bidId);
    }
}
