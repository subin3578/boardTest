package com.example.flutter.controller.pageController;

import com.example.flutter.dto.request.ArticleModifyDTO;
import com.example.flutter.dto.request.ArticleRequestDTO;
import com.example.flutter.dto.response.ArticleResponseDTO;
import com.example.flutter.entity.Article;
import com.example.flutter.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam( name = "page", defaultValue = "0") int page,
                       @RequestParam(name="size", defaultValue = "5") int size) {

        Page<ArticleResponseDTO> articles
                = boardService.selectAllArticles(page, size);
        model.addAttribute("articles", articles);
        model.addAttribute("totalPages", articles.getTotalPages());
        model.addAttribute("currentPage", page);

        return "board/list";
    }

    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") long id) {

        model.addAttribute("article", boardService.selectArticleById(id));

        return "board/modify";
    }

    @GetMapping("/write")
    public String write() {

        return "board/write";
    }

    // 글 작성
    @PostMapping("/write")
    public String writeArticle(@ModelAttribute ArticleRequestDTO articleRequestDTO) {

        // 프론트에서 Validation 추가 했으나 백에서도 추가
        if(articleRequestDTO.getTitle().length() > 20){
            throw new IllegalArgumentException("제목 길이 제한이 넘어섰습니다.");
        }else if(articleRequestDTO.getContent().length() > 20){
            throw new IllegalArgumentException("내용 길이 제한이 넘어섰습니다.");
        }

        boardService.saveArticle(articleRequestDTO);

        return "redirect:/board/list";
    }

    @PostMapping("/modify")
    public String modifyArticle(ArticleModifyDTO articleDTO) {

        if(articleDTO.getTitle().length() > 20){
            throw new IllegalArgumentException("제목 길이 제한이 넘어섰습니다.");
        }else if(articleDTO.getContent().length() > 20){
            throw new IllegalArgumentException("내용 길이 제한이 넘어섰습니다.");
        }

        boardService.modifyArticle(articleDTO);

        return "redirect:/board/list";
    }



}
