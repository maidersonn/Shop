package com.maider.shop.domain.repositories;

import com.maider.shop.domain.entities.Article;
import com.maider.shop.domain.entities.ArticleFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ArticleRepository {

    public List<Article> filter(ArticleFilter filters);
    public Article save(Article article);
    public List<Article> findAll();
    public void deleteById(Long id);
    Boolean existsById(Long id);
    Article getReferenceById(Long id);
}
