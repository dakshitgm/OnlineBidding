package com.dakshit.OnlineBidding.Controller;

import com.dakshit.OnlineBidding.Entity.AuthRequest;
import com.dakshit.OnlineBidding.Entity.User;
import com.dakshit.OnlineBidding.Exception.DuplicateUserException;
import com.dakshit.OnlineBidding.Payload.Response.LoginSuccessResponse;
import com.dakshit.OnlineBidding.Security.jwt.JwtUtils;
import com.dakshit.OnlineBidding.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="*", maxAge=3600)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@Valid @RequestBody  User user) throws DuplicateUserException {
        user.setPassword(encoder.encode(user.getPassword()));
        userService.addUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginSuccessResponse> login( @RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        return ResponseEntity.ok(new LoginSuccessResponse(jwt));
    }

    @PostMapping("/access")
    public String access(){
        return "ok";
    }

}
