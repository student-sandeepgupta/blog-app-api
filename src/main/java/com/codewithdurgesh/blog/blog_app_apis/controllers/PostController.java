
package com.codewithdurgesh.blog.blog_app_apis.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithdurgesh.blog.blog_app_apis.config.AppConstant;
import com.codewithdurgesh.blog.blog_app_apis.payloads.ApiResponse;
//import com.codewithdurgesh.blog.blog_app_apis.entities.Post;
import com.codewithdurgesh.blog.blog_app_apis.payloads.PostDto;
import com.codewithdurgesh.blog.blog_app_apis.payloads.PostResponse;
import com.codewithdurgesh.blog.blog_app_apis.services.FileService;
import com.codewithdurgesh.blog.blog_app_apis.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;
@Autowired
    private FileService fileService;
    @Value("{${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){

        PostDto createUserDto = this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<PostDto>(createUserDto,HttpStatus.CREATED);
    }
@GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getByUser(@PathVariable Integer userId ){
        
        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
        
        
        
    }
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getByCategory(@PathVariable Integer categoryId ){
        
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);

        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
        
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
        @RequestParam(defaultValue = AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
    @RequestParam(defaultValue = AppConstant.PAGE_SIZE,required = false)Integer pageSize,
    @RequestParam(defaultValue = AppConstant.SORT_BY,required=false)String sortBy,
    @RequestParam(defaultValue = AppConstant.SORT_DIR,required = false)String sortDir)
    {
       PostResponse postResponse =  this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
       return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }


    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){

       PostDto postDto = this.postService.getPostById(postId);

       return new ResponseEntity<>(postDto ,HttpStatus.OK);

    }

    @DeleteMapping("/posts/{postId}")

    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfully",true),HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")

    public ResponseEntity<PostDto> updatedPost(@RequestBody PostDto postDto,@PathVariable Integer postId){
     PostDto updateDto =   this.postService.updatePost(postDto, postId);

        return new ResponseEntity<PostDto>(updateDto,HttpStatus.OK);
    }
@GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> serachPostBytitle(@PathVariable String keyword){
        List<PostDto> searchPostDtos = this.postService.searchPosts(keyword);
return new ResponseEntity<List<PostDto>>(searchPostDtos,HttpStatus.OK);
    }

// post image uplode
@PostMapping("/post/image/upload/{postId}")
public ResponseEntity<PostDto> uploadPostImage(@RequestParam MultipartFile image,
@PathVariable Integer postId) throws IOException{
    PostDto postDto = this.postService.getPostById(postId);
  String fileName =  this.fileService.uploadImage(path, image);
 
  postDto.setImageName(fileName);
 PostDto updatedPost = this.postService.updatePost(postDto, postId);
 return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
}


@GetMapping(value = "post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
 public void downloadImage(@PathVariable String imageName,HttpServletResponse responce) throws IOException{
    
    InputStream resource = this.fileService.getResource(path, imageName);
    responce.setContentType(MediaType.IMAGE_JPEG_VALUE);
    StreamUtils.copy(resource, responce.getOutputStream());
 }

}
