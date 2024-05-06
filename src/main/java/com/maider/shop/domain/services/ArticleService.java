package com.maider.shop.domain.services;

import com.maider.shop.controllers.dto.ArticleDTO;
import com.maider.shop.controllers.dto.FilterDTO;
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
    public Result<List<Article>, String> getFiltered(FilterDTO filters) {
        try {
            ArticleSpecifications spec = new ArticleSpecifications(filters.getType(),
                                                                    filters.getSizeLessThan(),
                                                                    filters.getSizeGreaterThan(),
                                                                    filters.getMaterial(),
                                                                    filters.getBrand(),
                                                                    filters.getPriceLessThan(),
                                                                    filters.getPriceGreaterThan());
            List<Article> articles = articleRepository.findAll(spec);
            return new Success<>(articles);
        } catch (Exception e) {
            return new Failure<>("Error retrieving filtered articles");
        }

    }

    public Result<Article, String> getById(Long id) {
        try {
            Article article = articleRepository.getReferenceById(id);
            return new Success<>(article);
        } catch (Exception e) {
            return new Failure<>("Error getting article");
        }
    }
}
