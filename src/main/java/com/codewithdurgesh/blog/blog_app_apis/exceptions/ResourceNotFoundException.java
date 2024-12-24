package com.codewithdurgesh.blog.blog_app_apis.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {


    String resourceName;
    String fieldName;
   long fieldValue;
public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
    super("%s not found with %s : %s".formatted(resourceName, fieldName, fieldValue));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
}
}
