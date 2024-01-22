package com.dakshit.OnlineBidding.Entity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "bidder_id"}))
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getProductId() {
        return productId;
    }

    public long getBidderId() {
        return bidderId;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "bidder_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User bidder;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "bidder_id")
    private long bidderId;

    private double price;

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setBidderId(long bidderId) {
        this.bidderId = bidderId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getBidder() {
        return bidder;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
