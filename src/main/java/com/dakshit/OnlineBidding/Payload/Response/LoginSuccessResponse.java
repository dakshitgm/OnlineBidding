package com.dakshit.OnlineBidding.Payload.Response;


public class LoginSuccessResponse {
    private String token;

    public LoginSuccessResponse(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
