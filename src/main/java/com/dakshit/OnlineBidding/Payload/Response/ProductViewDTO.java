package com.dakshit.OnlineBidding.Payload.Response;

import com.dakshit.OnlineBidding.Entity.Product;

public class ProductViewDTO {
    private Long id;
    private String name;
    private String description;
    private String sellerUserName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSellerUserName() {
        return sellerUserName;
    }

    public void setSellerUserName(String sellerUserName) {
        this.sellerUserName = sellerUserName;
    }

    public ProductViewDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.sellerUserName = product.getSeller().getUserName();
    }
}
