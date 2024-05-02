package com.maider.shop.controllers;

import com.maider.shop.domain.entities.Article;
import com.maider.shop.domain.security.Result;
import com.maider.shop.domain.security.Success;
import com.maider.shop.domain.services.ArticleService;
import com.maider.shop.controllers.dto.ArticleDTO;
import com.maider.shop.controllers.dto.ArticleCreationDTO;
import com.maider.shop.controllers.mapper.ArticleMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getAll() {
        Result<List<Article>, String> articles = articleService.getAll();
        if(articles instanceof Success<?,?>) {
            List<Article> articlesList = articles.getValue();
            List<ArticleDTO> articleDTOList = articlesList.stream().map(article -> mapper.toDto(article)).toList();
            return new ResponseEntity<>(articleDTOList,  HttpStatusCode.valueOf(200));
        } else return new ResponseEntity<>(articles.getError(), HttpStatusCode.valueOf(500));
    }
    @PostMapping("/article")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid ArticleCreationDTO articleDTO) {
        Article newArticle = mapper.toArticle(articleDTO);
        Result<Article, String> article = articleService.save(newArticle);
        if (article instanceof Success<Article, String>) {
            ArticleDTO newArticleDTO = mapper.toDto(article.getValue());
            return new ResponseEntity<>(newArticleDTO, HttpStatusCode.valueOf(200));
        } else return new ResponseEntity<>(article.getError(), HttpStatusCode.valueOf(500));
    }
    @DeleteMapping("/article/{id}")
    public void delete(@PathVariable Long id) {

    }
}
