package com.example.flutter.entity;

import com.example.flutter.dto.response.ArticleResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String author = "anomynous";
    private int hit;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    public ArticleResponseDTO toDTO(Article article) {
        return ArticleResponseDTO.builder()
                .id(this.id)
                .title(this.title)
                .author(this.author)
                .content(this.content)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt).build();
    }
}
