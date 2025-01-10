package com.example.flutter.controller.pageController;

import com.example.flutter.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor

public class MainController {

    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "/index";
    }

}
