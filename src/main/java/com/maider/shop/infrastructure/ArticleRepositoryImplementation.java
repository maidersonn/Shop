package com.maider.shop.infrastructure;

import com.maider.shop.domain.entities.Article;
import com.maider.shop.domain.entities.ArticleFilter;
import com.maider.shop.domain.repositories.ArticleRepository;
import com.maider.shop.domain.services.ArticleSpecifications;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArticleRepositoryImplementation implements ArticleRepository{

    @Autowired
    private JpaArticleRepository jpaRepo;

    @Override
    public List<Article> filter(ArticleFilter filters) {
        ArticleSpecifications specs = new ArticleSpecifications(filters.getType(),
                filters.getSizeLessThan(),
                filters.getSizeGreaterThan(),
                filters.getMaterial(),
                filters.getBrand(),
                filters.getPriceLessThan(),
                filters.getPriceGreaterThan());
        return jpaRepo.findAll(specs);
    }
}
