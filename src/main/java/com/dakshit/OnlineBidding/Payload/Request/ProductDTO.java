package com.dakshit.OnlineBidding.Payload.Request;

import com.dakshit.OnlineBidding.Entity.Product;

public class ProductDTO {

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

    private String name;
    private String description;

    public ProductDTO(){

    }

}
