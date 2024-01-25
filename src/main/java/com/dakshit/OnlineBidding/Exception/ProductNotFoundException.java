package com.dakshit.OnlineBidding.Exception;

import java.util.NoSuchElementException;

public class ProductNotFoundException extends NoSuchElementException {
    public ProductNotFoundException() {
        super("product not with this id");
    }

    public ProductNotFoundException(String s) {
        super(s);
    }
}
