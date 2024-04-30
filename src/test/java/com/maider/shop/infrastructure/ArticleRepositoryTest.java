package com.maider.shop.infrastructure;

import com.maider.shop.domain.entities.Article;
import com.maider.shop.domain.entities.ArticleBuilder;
import com.maider.shop.domain.repositories.ArticleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    /**
     *  It saves an article
     *  It retrieves all articles
     *  It deletes an article
     *  It retrieves an article by id
     *  It updates an article
     *
     */
    @Test
    void whenCallToSaveItSavesAnArticle() {
        ArticleBuilder builder = new ArticleBuilder();
        Article newArticle = builder.withBrand("Lewis").withPrice(80).withSize(40).withMaterial("leather").withType("trousers").build();

        Article article = articleRepository.save(newArticle);

        Assertions.assertNotNull(article);
    }
   /* @Test whenCallToGetAllItRetrievesAllArticles() {

    }*/
}