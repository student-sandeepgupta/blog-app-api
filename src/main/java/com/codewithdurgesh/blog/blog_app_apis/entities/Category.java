package com.codewithdurgesh.blog.blog_app_apis.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
//import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer categoryId;

   @Column(name = "title")
   private String categoryTitle;

@Column (name = "discription")
private String categoryDescription;

@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
private List<Post> posts = new ArrayList<>();

}
