package com.maider.shop.domain.repositories;

import com.maider.shop.domain.entities.Article;
import com.maider.shop.domain.entities.ArticleFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository {

    public List<Article> filter(ArticleFilter filters);

}
