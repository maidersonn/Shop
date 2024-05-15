package com.maider.shop.rest;

import com.maider.shop.articleFactory.ArticleFactory;
import com.maider.shop.controllers.dto.ArticleCreationDTO;
import com.maider.shop.controllers.dto.ArticleDTO;
import com.maider.shop.domain.entities.Article;
import com.maider.shop.domain.entities.ArticleBuilder;
import com.maider.shop.domain.repositories.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.w3c.dom.Entity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiTest {
    @LocalServerPort
    private int port;


    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private ArticleRepository articleRepository;

    @Test
    void shouldReturnArticleDTOForCreateEndpoint() {
        ArticleCreationDTO  creationDto= new ArticleCreationDTO("trousers", "leather", "Lewis", 40, 80.0);
        Article article = ArticleFactory.createOne();
        Article articleReturned = ArticleFactory.createOne();
        articleReturned.setId(1L);

        Mockito.when(articleRepository.save(article)).thenReturn(articleReturned);

        ArticleDTO response = this.restTemplate.postForEntity("http://localhost:" + port + "/article", creationDto, ArticleDTO.class).getBody();

        assertNotNull(response);
        assertEquals("leathertrousers", response.getName());
        assertEquals( "Lewis", response.getBrand());
        assertEquals( 80.0, response.getPrice());
        assertEquals( 40, response.getSize());
        assertNotNull(response.getId());
    }
    @Test
    void shouldReturnListOfArticleDTOsForGetAllEndpoint() {
        List<Article> articles = ArticleFactory.create(2);
        Mockito.when(articleRepository.findAll()).thenReturn(articles);

        List<ArticleDTO> response = this.restTemplate.getForObject("http://localhost:" + port + "/articles", List.class);

        assertNotNull(response);
        assertEquals(2, response.size());
       // assertEquals("Lewis", response.get(0).getBrand());
       // assertInstanceOf(ArticleDTO, response.get(0));
    }
    @Test
    void shouldReturnArticleDTOForGetByIdEndpoint() {
        Article articleReturned = ArticleFactory.createOne();
        articleReturned.setId(1L);
        Mockito.when(articleRepository.getReferenceById(1L)).thenReturn(articleReturned);

        ArticleDTO response = this.restTemplate.getForEntity("http://localhost:" + port + "/article/1", ArticleDTO.class).getBody();

        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals(articleReturned.getBrand(), response.getBrand());
        assertEquals(articleReturned.getMaterial().concat(articleReturned.getType()), response.getName());
        assertEquals( articleReturned.getPrice(), response.getPrice());
        assertEquals( articleReturned.getSize_(), response.getSize());
    }

}
