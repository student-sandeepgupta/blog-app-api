package com.codewithdurgesh.blog.blog_app_apis.payloads;

//import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {

private int id;

@NotEmpty
@Size(min = 4,message = "username must be greater then 4 character")
    private String name;

@Email(message = "email address is not valid")
    private String email;

@NotEmpty
@Size(min = 3,message = "password must be greater then 3 character")
    private String password;

    private String about;
}
