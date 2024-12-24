package com.codewithdurgesh.blog.blog_app_apis.payloads;

import jakarta.validation.constraints.NotBlank;
//import jakarta.persistence.Column;
//import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Integer categoryId;
    @NotBlank
    @Size(min=4,message = "min size of title is 4: ")
    private String categoryTitle;
    @NotBlank
    @Size(min =10,message = "min size of description is 10: ")
    private String categoryDescription;
}
