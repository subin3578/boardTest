package com.example.flutter.controller.apiController;

import com.example.flutter.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardApiController {

    private final BoardService boardService;

    @ResponseStatus
    @DeleteMapping("/article/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable("id") Long id) {

        boolean isDeleted = boardService.deleteArticle(id);

        if (isDeleted) {
            return ResponseEntity.ok("Board deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete the board.");
        }
    }
}
