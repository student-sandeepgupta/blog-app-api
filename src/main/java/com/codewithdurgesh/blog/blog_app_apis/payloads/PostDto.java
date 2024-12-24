package com.codewithdurgesh.blog.blog_app_apis.payloads;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

//import com.codewithdurgesh.blog.blog_app_apis.entities.Comment;

//import com.codewithdurgesh.blog.blog_app_apis.entities.Category;
//import com.codewithdurgesh.blog.blog_app_apis.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// import jakarta.persistence.Column;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {


private Integer postId;
   
    private String title;
  
    private String content;

     private String imageName;

     private Date addDate;

    
    private CategoryDto category;

      private UserDto user;
private Set<CommentDto> comments = new HashSet<>();      

}
