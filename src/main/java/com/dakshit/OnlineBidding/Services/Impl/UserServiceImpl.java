package com.dakshit.OnlineBidding.Services.Impl;

import com.dakshit.OnlineBidding.Entity.User;
import com.dakshit.OnlineBidding.Exception.DuplicateUserException;
import com.dakshit.OnlineBidding.Services.UserService;
import com.dakshit.OnlineBidding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {



    @Autowired
    private UserRepository userRepository;



    @Override
    public User addUser(User user) throws DuplicateUserException{
        if(userRepository.findByEmailId(user.getEmailId()) != null
                || userRepository.findByUserName(user.getUserName()) != null){
            throw new DuplicateUserException("username or email already exist");
        }
        return userRepository.save(user);
    }

    @Override
    public String authenticate(String email, String password) {
        User user = userRepository.findByEmailId(email);

        if(user == null || !password.equals(user.getPassword()))
            return "Wrong credentials";

        return "logged in successfully";
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmailId(email);

        if(user == null) {
            throw new UsernameNotFoundException("User not found with email" + email);
        }

        return UserDetailsImpl.build(user);
    }
}
