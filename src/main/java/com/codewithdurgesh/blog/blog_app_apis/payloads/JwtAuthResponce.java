package com.codewithdurgesh.blog.blog_app_apis.payloads;



import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtAuthResponce {

    private String token;
    private String username;
    
}
