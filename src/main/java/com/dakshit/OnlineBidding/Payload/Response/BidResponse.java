package com.dakshit.OnlineBidding.Payload.Response;

import com.dakshit.OnlineBidding.Entity.Bid;

public class BidResponse {
    private long bidId;
    private long productId;
    private long bidderId;
    private double price;


    public BidResponse(Bid bid){
        this.bidId = bid.getId();
        this.productId = bid.getProductId();
        this.bidderId = bid.getBidderId();
        this.price = bid.getPrice();
    }

    public long getBidderId() {
        return bidderId;
    }

    public void setBidderId(long bidderId) {
        this.bidderId = bidderId;
    }


    public long getBidId() {
        return bidId;
    }

    public void setBidId(long bidId) {
        this.bidId = bidId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
