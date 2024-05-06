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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


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
            return new ResponseEntity<>(articleDTOList,  HttpStatus.OK);
        } else return new ResponseEntity<>(articles.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/article")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid ArticleCreationDTO articleDTO) {
        Article newArticle = mapper.toArticle(articleDTO);
        Result<Article, String> article = articleService.save(newArticle);
        if (article instanceof Success<Article, String>) {
            ArticleDTO newArticleDTO = mapper.toDto(article.getValue());
            return new ResponseEntity<>(newArticleDTO, HttpStatus.CREATED);
        } else return new ResponseEntity<>(article.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("/article/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Result<Boolean, String> deleteResponse = articleService.deleteById(id);
        if (deleteResponse instanceof Success<Boolean, String>) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(deleteResponse.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping("/article/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid ArticleCreationDTO  articleDTO,
                                    @PathVariable Long id) {
        Article articleToUpdate = mapper.toArticle(articleDTO);
        Result<Article, String> updatedArticle = articleService.updateById(id, articleToUpdate);
        if (updatedArticle instanceof Success<Article, String>) {
            ArticleDTO updatedArticleDTO = mapper.toDto(updatedArticle.getValue());
            return new ResponseEntity<>(updatedArticleDTO, HttpStatus.OK);
        } else return new ResponseEntity<>(updatedArticle.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/articles/filtered")
    public List<Article> getFiltered(@RequestParam(required = false) String type,
                                         @RequestParam(required = false) Integer sizeLessThan,
                                         @RequestParam(required = false) Integer sizeGreaterThan,
                                         @RequestParam(required = false) String material,
                                         @RequestParam(required = false) String brand,
                                         @RequestParam(required = false) Double priceLessThan,
                                         @RequestParam(required = false) Double priceGreaterThan) {
        List<Article> articles = articleService.getFiltered(type,sizeLessThan,sizeGreaterThan, material, brand,priceLessThan,priceGreaterThan);
        return articles;
    }
}
