package com.codewithdurgesh.blog.blog_app_apis.services.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

// import org.hibernate.query.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.*;
//import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.blog_app_apis.entities.Category;
import com.codewithdurgesh.blog.blog_app_apis.entities.Post;
import com.codewithdurgesh.blog.blog_app_apis.entities.User;
import com.codewithdurgesh.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.blog_app_apis.payloads.PostDto;
import com.codewithdurgesh.blog.blog_app_apis.payloads.PostResponse;
import com.codewithdurgesh.blog.blog_app_apis.repositories.CategoryRepo;
import com.codewithdurgesh.blog.blog_app_apis.repositories.PostRepo;
import com.codewithdurgesh.blog.blog_app_apis.repositories.UserRepo;
import com.codewithdurgesh.blog.blog_app_apis.services.PostService;
@Service
public class PostServiceImpl implements PostService  {
@Autowired
private PostRepo postRepo;

@Autowired
private ModelMapper modelMapper;

@Autowired
private UserRepo userRepo;

@Autowired
private CategoryRepo categoryRepo;


    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
  User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User id",userId));

  Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddDate(new Date(0));
        post.setUser(user);
        post.setCategory(category);
        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id" ,postId ));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
       Post updatedpost = this.postRepo.save(post);
       return this.modelMapper.map(updatedpost, PostDto.class);     

    }

    @Override
    public void deletePost(Integer postId) {
Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id" ,postId ));

this.postRepo.delete(post);

      }

    @Override
    public PostDto getPostById(Integer postId) {

        Post post  = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));

        PostDto postDto = this.modelMapper.map(post, PostDto.class);
        return postDto;

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
        Sort sort =null;
        if(sortDir.equalsIgnoreCase(("asc"))){
            sort = Sort.by(sortBy).ascending();
        }
        else{
          sort =  Sort.by(sortBy).descending();
        }
  Pageable p = PageRequest.of(pageNumber, pageSize,sort);
      Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos = allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
   
PostResponse postResponse = new PostResponse();

postResponse.setContent(postDtos);
postResponse.setPostNumber(pagePost.getNumber());
postResponse.setPostSize(pagePost.getSize());
postResponse.setTotalElement(pagePost.getTotalElements());
postResponse.setTotalPages(pagePost.getTotalPages());
postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
List<Post> posts= this.postRepo.findByCategory(cat);
List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
return postDtos;
   }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
  
  User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
List<Post> posts = this.postRepo.findByUser(user);

List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
return postDtos;

    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
   List<Post> posts= this.postRepo.findByTitleContaining(keyword);
   List<PostDto> postDtos =posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
   return postDtos;
    }

}
