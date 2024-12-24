package com.codewithdurgesh.blog.blog_app_apis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.codewithdurgesh.blog.blog_app_apis.security.JwtAuthenticationEntryPoint;
import com.codewithdurgesh.blog.blog_app_apis.security.JwtAuthenticationfilter;

//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
public class SecurityConfig   {
@Autowired
private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
@Autowired
private JwtAuthenticationfilter jwtAuthenticationfilter;

@SuppressWarnings({ "deprecation", "removal" })
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        

        http.csrf(csrf->csrf.disable())
       .cors(cors->cors.disable())
       .authorizeRequests(auth->auth.requestMatchers("/test").authenticated().requestMatchers("/auth/login").permitAll()
       .anyRequest()
       .authenticated())
       .exceptionHandling(ex->ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
       .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       http.addFilterBefore(jwtAuthenticationfilter, UsernamePasswordAuthenticationFilter.class);
       return http.build();

    }
// @Bean
//   public AuthenticationManager authenticationManagerBean() throws Exception{
//     super.authenticationManagerBean();
//     return http.build()

//   }
}
