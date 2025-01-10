package com.example.flutter.dto.request;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleRequestDTO {

    private String title;
    private String content;
    private String author = "anomynous";


}
