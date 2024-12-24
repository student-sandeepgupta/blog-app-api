package com.codewithdurgesh.blog.blog_app_apis.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;

//import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;

//import org.hibernate.validator.constraints.ru.INN;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer postId;

    @Column(name = "post_title",length = 100,nullable = false)
    private String title;
    @Column(name = "Post_content",length = 10000)
    private String content;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "date")
    private Date addDate;

    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch =FetchType.LAZY )
private Set<Comment> comments = new HashSet<>();

}
