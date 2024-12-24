package com.codewithdurgesh.blog.blog_app_apis.entities;


//import org.hibernate.annotations.ManyToAny;

//import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
public class Comment {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
private String content;

@ManyToOne
private Post post;


}
