package com.dakshit.OnlineBidding.Utils;

import com.dakshit.OnlineBidding.Entity.User;
import com.dakshit.OnlineBidding.Services.Impl.UserDetailsImpl;
import com.dakshit.OnlineBidding.Services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    public long getLoggedInUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        return userDetailsImpl.getId();
    }
}
