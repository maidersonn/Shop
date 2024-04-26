package com.maider.shop.entities;

import com.maider.shop.domain.entities.Article;
import com.maider.shop.domain.entities.ArticleBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class ArticleBuilderTest {
    @Test
    public void itCreatesArticleWithProperties () {
        ArticleBuilder builder = new ArticleBuilder();
        builder.withBrand("Lewis")
                .withType("pantalon")
                .withMaterial("vaquero")
                .withSize(38)
                .withPrice(80)
                .withId(1L);
        Article article = builder.build();
        assertEquals(1L, article.getId());
        assertEquals("pantalon", article.getType());
        assertEquals(38, article.getSize());
        assertEquals("vaquero", article.getMaterial());
        assertEquals("Lewis", article.getBrand());
        assertEquals(80, article.getPrice());
    }
}
