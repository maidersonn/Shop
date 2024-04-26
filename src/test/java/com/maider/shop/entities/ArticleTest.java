package com.maider.shop.entities;

import com.maider.shop.domain.entities.Article;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class ArticleTest {
    @Test
    public void itCreatesAnEmptyArticle () {
        Article articulo = new Article();
        assertNotNull(articulo);
    }
    @Test
    public void itCreatesArticleWithProperties () {
        Article articulo = new Article(1L,"pantalon", 38, "algodon", "Batela", 10.5);
        assertEquals(1L, articulo.getId());
        assertEquals("pantalon", articulo.getType());
        assertEquals(38, articulo.getSize());
        assertEquals("algodon", articulo.getMaterial());
        assertEquals("Batela", articulo.getBrand());
        assertEquals(10.5, articulo.getPrice());
    }
}