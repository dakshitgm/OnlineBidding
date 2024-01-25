package com.dakshit.OnlineBidding.Exception;

import java.util.NoSuchElementException;

public class BidNotFoundException extends NoSuchElementException {
    public BidNotFoundException() {
        super();
    }

    public BidNotFoundException(String s) {
        super(s);
    }
}
