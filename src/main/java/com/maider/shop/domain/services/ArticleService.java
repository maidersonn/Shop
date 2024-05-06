package com.maider.shop.domain.services;

import com.maider.shop.controllers.spec.ArticleSpecifications;
import com.maider.shop.domain.entities.Article;
import com.maider.shop.domain.repositories.ArticleRepository;
import com.maider.shop.domain.security.Failure;
import com.maider.shop.domain.security.Result;
import com.maider.shop.domain.security.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

    public Result<Article, String> updateById(Long id, Article articleToUpdate) {
        try {
            Boolean article = articleRepository.existsById(id);
            if(!article) return new Failure<>("This article does not exist");
            articleToUpdate.setId(id);
            Article updatedArticle = articleRepository.save(articleToUpdate);
            return new Success<>(updatedArticle);
        } catch (Exception e) {
            return new Failure<>("Error updating article");
        }
    }
    public List<Article> getFiltered(String type, Integer sizeLessThan, Integer sizeGreaterThan, String material, String brand, Double priceLessThan, Double priceGreaterThan) {
        ArticleSpecifications spec = new ArticleSpecifications(type,sizeLessThan,sizeGreaterThan, material, brand,priceLessThan,priceGreaterThan);
        return articleRepository.findAll(spec);
    }
}
