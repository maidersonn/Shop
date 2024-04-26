package com.maider.shop.controllers;

import com.maider.shop.domain.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    @GetMapping("/hola")
    @ResponseBody
    public String getAll() {
        return "HOla";
    }
}
