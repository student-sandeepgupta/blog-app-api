package com.codewithdurgesh.blog.blog_app_apis.payloads;

// import jakarta.validation.constraints.Email;
import lombok.Data;



@Data
public class JwtAuthRequest {

    private String email;
    private String password;
}
