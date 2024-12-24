package com.codewithdurgesh.blog.blog_app_apis.controllers;

import java.util.List;

//import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.blog_app_apis.payloads.ApiResponse;
import com.codewithdurgesh.blog.blog_app_apis.payloads.UserDto;
import com.codewithdurgesh.blog.blog_app_apis.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
@Autowired
    private UserService userService;

    //post - create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
    }

    // put - updating
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid){
        UserDto upadatedDto = this.userService.updateUser(userDto, uid);
return ResponseEntity.ok(upadatedDto);
    }

    // delete - 
@DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid ){

        this.userService.deleteUser(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted succesfully",true),HttpStatus.OK);
    }

    // getAll
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    //getbyId

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getById(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
