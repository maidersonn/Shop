package com.maider.shop.domain.services;

import com.maider.shop.domain.entities.Article;
import com.maider.shop.domain.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }
}
