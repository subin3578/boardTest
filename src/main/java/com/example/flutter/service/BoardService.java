package com.example.flutter.service;

import com.example.flutter.dto.request.ArticleModifyDTO;
import com.example.flutter.dto.request.ArticleRequestDTO;
import com.example.flutter.dto.response.ArticleResponseDTO;
import com.example.flutter.entity.Article;
import com.example.flutter.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.NoSuchElementException;


@Service
@Log4j2
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long saveArticle(ArticleRequestDTO articleRequestDTO) {

        // TODO : DTO - toEntity() 만들기
        Article article = Article.builder()
                .author("익명")
                .title(articleRequestDTO.getTitle())
                .content(articleRequestDTO.getContent()).build();

        Article savedArticle = boardRepository.save(article);

        return savedArticle.getId();
    }

    public Long modifyArticle(ArticleModifyDTO articleRequestDTO) {
        Article article = boardRepository.findById(articleRequestDTO.getId())
                .orElseThrow(()->new NoSuchElementException("해당하는 ID의 게시글이 존재하지 않습니다"));

        article.setTitle(articleRequestDTO.getTitle());
        article.setContent(articleRequestDTO.getContent());
        Article savedArticle = boardRepository.save(article);

        return savedArticle.getId();
    }

    public boolean deleteArticle(Long articleId) {

        try {
            boardRepository.deleteById(articleId);
            return true;
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Page<ArticleResponseDTO> selectAllArticles(int page, int size) {

        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<Article> articles = boardRepository.findAll(pageable);

        return articles.map(article -> article.toDTO(article));
    }

    public ArticleResponseDTO selectArticleById(Long id) {

        Article article = boardRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("해당하는 ID의 게시글이 존재하지 않습니다"));

        return article.toDTO(article);

    }



}
