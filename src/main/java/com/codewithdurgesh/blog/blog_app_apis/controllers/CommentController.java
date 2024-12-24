package com.codewithdurgesh.blog.blog_app_apis.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.blog_app_apis.payloads.ApiResponse;
import com.codewithdurgesh.blog.blog_app_apis.payloads.CommentDto;
import com.codewithdurgesh.blog.blog_app_apis.services.CommentService;

import java.util.List;

//import org.hibernate.dialect.function.HypotheticalSetWindowEmulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/comments/")
public class CommentController {

    @Autowired 
    private CommentService commentService ;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
        CommentDto commentDto2 = this.commentService.createComment(commentDto, postId);
        return new ResponseEntity<CommentDto>(commentDto2,HttpStatus.OK);
    }
   
    @DeleteMapping("/comments/{commentId}")
public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
    this.commentService.deleteCommentById(commentId);
    return new ResponseEntity<ApiResponse>(new ApiResponse("comment delete sussesfully",true),HttpStatus.OK);
}

@GetMapping("/")
public ResponseEntity<List<CommentDto>> getAllComments(){
    List<CommentDto> commentDtos = this.commentService.getALLComment();
    return new ResponseEntity<List<CommentDto>>(commentDtos,HttpStatus.OK);
}

@GetMapping("{commentId}")
public ResponseEntity<CommentDto> getById(@PathVariable Integer commentId){
   CommentDto commentDto =  this.commentService.getById(commentId);

    return new ResponseEntity<CommentDto>(commentDto,HttpStatus.OK);
}


    }
    

