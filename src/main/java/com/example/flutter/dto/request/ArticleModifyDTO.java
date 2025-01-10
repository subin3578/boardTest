package com.example.flutter.dto.request;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ArticleModifyDTO {

    private Long id;
    private String title;
    private String content;
    private String author = "anomynous";


}
