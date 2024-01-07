package com.dakshit.OnlineBidding.Services;

import com.dakshit.OnlineBidding.Entity.User;
import com.dakshit.OnlineBidding.Exception.DuplicateUserException;
import org.springframework.stereotype.Service;

@Service
public interface UserService{
    public User addUser(User user) throws DuplicateUserException;
    public String authenticate(String email, String password);

}
