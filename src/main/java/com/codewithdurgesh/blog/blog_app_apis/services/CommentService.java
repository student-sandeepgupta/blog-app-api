package com.codewithdurgesh.blog.blog_app_apis.services;

import java.util.List;

import com.codewithdurgesh.blog.blog_app_apis.payloads.CommentDto;

public interface CommentService {
// create
    CommentDto createComment(CommentDto commentDto,Integer postId);

//Update 
CommentDto updateComment(CommentDto commentDto,Integer postId);

// get all comment
List<CommentDto> getALLComment();

//get by id comment
CommentDto getById(Integer commentId);

// delete by id comment
void deleteCommentById(Integer commentId);

}
