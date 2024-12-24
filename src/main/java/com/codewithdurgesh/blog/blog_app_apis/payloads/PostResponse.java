package com.codewithdurgesh.blog.blog_app_apis.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PostResponse {

    private List<PostDto> content;
    private Integer postNumber;
    private Integer postSize;
    private long totalElement;
    private Integer totalPages;
    private boolean lastPage;
}
