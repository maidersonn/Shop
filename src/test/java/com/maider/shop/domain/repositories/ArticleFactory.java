package com.maider.shop.domain.repositories;

import com.maider.shop.domain.entities.Article;
import com.maider.shop.domain.entities.ArticleBuilder;

import java.util.ArrayList;
import java.util.List;

public class ArticleFactory {
    public static Article createOne() {
        ArticleBuilder builder = new ArticleBuilder();
        return builder.withBrand("Lewis").withPrice(80).withSize(40).withMaterial("leather").withType("trousers").build();
    }
    public static List<Article> create(Integer amount) {
        ArticleBuilder builder = new ArticleBuilder();
        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Article newArticle = builder.withBrand("Lewis").withPrice(80).withSize(40).withMaterial("leather").withType("trousers").build();
            articles.add(newArticle);
        }
        return articles;
    }
}
