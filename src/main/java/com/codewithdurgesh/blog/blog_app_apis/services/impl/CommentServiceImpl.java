package com.codewithdurgesh.blog.blog_app_apis.services.impl;

//import java.util.Collection;
import java.util.List;
//import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.blog_app_apis.entities.Comment;
import com.codewithdurgesh.blog.blog_app_apis.entities.Post;
import com.codewithdurgesh.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.blog_app_apis.payloads.CommentDto;
import com.codewithdurgesh.blog.blog_app_apis.repositories.CommentRepo;
import com.codewithdurgesh.blog.blog_app_apis.repositories.PostRepo;
import com.codewithdurgesh.blog.blog_app_apis.services.CommentService;

//import jakarta.persistence.PostRemove;
@Service
public class CommentServiceImpl implements CommentService {
@Autowired
private CommentRepo commentRepo;

@Autowired
private PostRepo postRepo;

@Autowired

private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto,Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","post Id", postId));
  Comment comment2 = this.modelMapper.map(commentDto, Comment.class);
comment2.setPost(post);
Comment savedComment = this.commentRepo.save(comment2);
return this.modelMapper.map(savedComment, CommentDto.class);

    }

    @Override
    public CommentDto updateComment(CommentDto commentDto,Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","comment Id",commentId));
 
        comment.setContent(commentDto.getContent());
        Comment comment2 = this.commentRepo.save(comment);
       CommentDto commentDto2 = this.modelMapper.map(comment2, CommentDto.class);

 return commentDto2;
    }

    @Override
    public List<CommentDto> getALLComment() {
   List<Comment> comments = this.commentRepo.findAll();
   List<CommentDto> commentDtos = comments.stream().map((comment)->modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
   return commentDtos;
   
    }

    @Override
    public CommentDto getById(Integer commentId) {
  Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comm","post Id", commentId) );
CommentDto commentDto = this.modelMapper.map(comment,CommentDto.class);
return commentDto;

}

    @Override
    public void deleteCommentById(Integer commentId) {
   Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","comment Id",commentId));
 this.commentRepo.delete(comment);
   
    }

}
