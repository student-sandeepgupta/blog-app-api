package com.codewithdurgesh.blog.blog_app_apis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.blog_app_apis.payloads.JwtAuthRequest;
import com.codewithdurgesh.blog.blog_app_apis.payloads.JwtAuthResponce;
import com.codewithdurgesh.blog.blog_app_apis.security.JwtTokenHelper;

@RestController
@RequestMapping("/auth/")
public class AuthController {
   
    
        @Autowired
        private UserDetailsService userDetailsService;
    
        @Autowired
        private AuthenticationManager manager;
    
    
        @Autowired
        private JwtTokenHelper helper;
    
        private Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    
        @PostMapping("/login")
        public ResponseEntity<JwtAuthResponce> login(@RequestBody JwtAuthRequest request) {
    
            this.doAuthenticate(request.getEmail(), request.getPassword());
    
    
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            String token = this.helper.generateToken(userDetails);
    
        JwtAuthResponce response = JwtAuthResponce.builder()
                    .token(token)
                    .username(userDetails.getUsername()).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    
        private void doAuthenticate(String email, String password) {
    
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
            try {
                manager.authenticate(authentication);
    
    
            } catch (BadCredentialsException e) {
                throw new BadCredentialsException(" Invalid Username or Password  !!");
            }
    
        }
    
        @ExceptionHandler(BadCredentialsException.class)
        public String exceptionHandler() {
            return "Credentials Invalid !!";
        }
    
    }










// @Autowired
// private JwtTokenHelper jwtTokenHelper;
// @Autowired
// private UserDetailsService userDetailsService;
// @Autowired
// private AuthenticationManager authenticationManager;
//     @PostMapping("/login")
//         public ResponseEntity<JwtAuthResponce> createToken(@RequestBody JwtAuthRequest request){

//             this.authenticate(request.getEmail(),request.getPassword());
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
//        String token = this.jwtTokenHelper.generateToken(userDetails);
      
//        JwtAuthResponce jwtAuthResponce = new JwtAuthResponce();
//      jwtAuthResponce.setToken(token);
//        return new ResponseEntity<JwtAuthResponce>(jwtAuthResponce,HttpStatus.OK);
       
//         }
//         private void authenticate(String email,String password){
//      UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(email, password);
//      this.authenticationManager.authenticate(authenticationToken);      
//         }
    


    // JwtAuthResponce jwtAuthResponce = new JwtAuthResponce();
    // jwtAuthResponce.setToken(token);