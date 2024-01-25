package com.dakshit.OnlineBidding.Exception;

public class UnauthorisedAccessException extends Exception{
    public UnauthorisedAccessException() {
        super();
    }

    public UnauthorisedAccessException(String message) {
        super(message);
    }
}
