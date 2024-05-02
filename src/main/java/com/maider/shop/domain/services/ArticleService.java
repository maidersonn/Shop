package com.maider.shop.domain.services;

import com.maider.shop.domain.entities.Article;
import com.maider.shop.domain.repositories.ArticleRepository;
import com.maider.shop.domain.security.Failure;
import com.maider.shop.domain.security.Result;
import com.maider.shop.domain.security.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Result<Article, String> save(Article article) {
       try {
           Article newArticle = articleRepository.save(article);
           return new Success<>(newArticle);
       } catch (Exception e) {
           return new Failure<>("Error relationed with database");
       }

    }
    public Result<List<Article>, String> getAll() {
        try {
            List<Article> articles = articleRepository.findAll();
            return new Success<>(articles);
        } catch (Exception e) {
            return new Failure<>("Error retrieving articles");
        }
    }

    public Result<Boolean, String> deleteById(Long id) {
        try {
            articleRepository.deleteById(id);
            return new Success<>(true);
        } catch (Exception e) {
            return new Failure<>("Error deleting article");
        }
    }
}
