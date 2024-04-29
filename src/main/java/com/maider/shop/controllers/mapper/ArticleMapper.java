package com.maider.shop.controllers.mapper;

import com.maider.shop.domain.entities.Article;
import com.maider.shop.controllers.dto.ArticleCreationDTO;
import com.maider.shop.controllers.dto.ArticleDTO;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {
    public ArticleDTO toDto (Article article) {
        String type = article.getType();
        String material = article.getMaterial();
        String name = material.concat(type);
        return new ArticleDTO(article.getId(), name, article.getBrand(), article.getSize_(), article.getPrice());

    }
    public Article toArticle (ArticleCreationDTO articleDTO) {
        return new Article(articleDTO.getType(), articleDTO.getSize(), articleDTO.getMaterial(), articleDTO.getBrand(), articleDTO.getPrice());
    }
}
