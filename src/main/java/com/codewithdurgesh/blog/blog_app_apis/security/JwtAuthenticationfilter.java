package com.codewithdurgesh.blog.blog_app_apis.security;

import java.io.IOException;

//import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// import lombok.extern.java.Log;

@Component
public class JwtAuthenticationfilter extends OncePerRequestFilter {
   
    
        // private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
        @Autowired
        private JwtTokenHelper jwtHelper;
    
    
        @Autowired
        private UserDetailsService userDetailsService;
    
        @Override
        protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") FilterChain filterChain) throws ServletException, IOException {
    
    //        try {
    //            Thread.sleep(500);
    //        } catch (InterruptedException e) {
    //            throw new RuntimeException(e);
    //        }
            //Authorization
    
            String requestHeader = request.getHeader("Authorization");
            //Bearer 2352345235sdfrsfgsdfsdf
            // logger.info(" Header :  {}", requestHeader);
            String username = null;
            String token = null;
            if (requestHeader != null && requestHeader.startsWith("Bearer")) {
                //looking good
                token = requestHeader.substring(7);
                try {
    
                    username = this.jwtHelper.getUsernameFromToken(token);
    
                } catch (IllegalArgumentException e) {
                    System.out.println("");
                } catch (ExpiredJwtException e) {
                  System.out.println(" ");
                } catch (MalformedJwtException e) {
                    System.out.println(" ");
                } catch (Exception e) {
                    e.printStackTrace();
    
                }
    
    
            } else {
                System.out.println(" ");
            }
    
    
            //
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    
    
                //fetch user detail from username
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
                if (validateToken) {
    
                    //set the authentication
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
    
    
                } else {
                   System.out.println(" ");
                }
    
    
            }
    
            filterChain.doFilter(request, response);
    
    
        }
    }

