package com.maider.shop.controllers;

import com.maider.shop.domain.entities.Article;
import com.maider.shop.domain.services.ArticleService;
import com.maider.shop.controllers.dto.ArticleDTO;
import com.maider.shop.controllers.dto.ArticleCreationDTO;
import com.maider.shop.controllers.mapper.ArticleMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleMapper mapper;

    @GetMapping("/articles")
    @ResponseBody
    public List<ArticleDTO> getAll() {
        List<Article> articles = articleService.getAll();
        return articles.stream().map(article -> mapper.toDto(article)).toList();
    }
    @PostMapping("/article")
    @ResponseBody
    public ArticleDTO create(@RequestBody @Valid ArticleCreationDTO articleDTO) {
        Article newArticle = mapper.toArticle(articleDTO);
        Article article = articleService.save(newArticle);
        return mapper.toDto(article);
    }
    @DeleteMapping("/article/{id}")
    public void delete(@PathVariable Long id) {

    }
}
