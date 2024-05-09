package com.maider.shop.controllers.mapper;

import com.maider.shop.controllers.dto.FilterDTO;
import com.maider.shop.domain.entities.ArticleFilter;
import org.springframework.stereotype.Component;

@Component
public class ArticleFilterMapper {
    public ArticleFilter toArticleFilter(FilterDTO filterdto) {
        return new ArticleFilter(filterdto.getType(),
                                filterdto.getSizeLessThan(),
                                filterdto.getSizeGreaterThan(),
                                filterdto.getMaterial(),
                                filterdto.getBrand(),
                                filterdto.getPriceLessThan(),
                                filterdto.getPriceGreaterThan());
    }
}
