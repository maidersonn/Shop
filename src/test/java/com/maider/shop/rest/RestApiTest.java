package com.maider.shop.rest;

import com.maider.shop.articleFactory.ArticleFactory;
import com.maider.shop.controllers.dto.ArticleCreationDTO;
import com.maider.shop.controllers.dto.ArticleDTO;
import com.maider.shop.controllers.dto.FilterDTO;
import com.maider.shop.domain.entities.Article;
import com.maider.shop.domain.entities.ArticleBuilder;
import com.maider.shop.domain.entities.ArticleFilter;
import com.maider.shop.domain.repositories.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.aspectj.util.LangUtil.isEmpty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiTest {
    @LocalServerPort
    private int port;
    @Autowired
    JpaRepository jpaRepository;
    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private ArticleRepository articleRepository;
    @AfterEach
    void clearDataBase() {
        jpaRepository.deleteAll();
    }
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

        List<ArticleDTO> response = this.restTemplate
                .exchange("http://localhost:" + port + "/articles",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ArticleDTO>>() {})
                .getBody();

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals("Lewis", response.get(0).getBrand());
        response.forEach((element) -> assertInstanceOf(ArticleDTO.class, element));
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
    @Test
    void shouldReturnArticleNotFoundWhenObjectDoesNotExist() {
       Mockito.when(articleRepository.getReferenceById(1L)).thenThrow(new EntityNotFoundException());

       String response = this.restTemplate.getForEntity("http://localhost:" + port + "/article/1", String.class).getBody();

       assertEquals("Article not found", response);
    }
    @Test
    void shouldReturnResponseEntityForDeleteEndpoint() {
        Article article = ArticleFactory.createOne();
        article.setId(1L);

        doAnswer(invocation -> null).when(articleRepository).deleteById(1L);

        ResponseEntity<?> response = this.restTemplate.exchange("http://localhost:" + port + "/article/1", HttpMethod.DELETE, null, ResponseEntity.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    @Test
    void shouldReturnArticleDTOForUpdateEndpoint() {
        ArticleCreationDTO articleCreation = new ArticleCreationDTO("trousers", "leather", "Lewis", 40, 52.0);
        Article articleToUpdate = ArticleFactory.createOne();
        articleToUpdate.setId(1L);
        articleToUpdate.setPrice(52);
        Mockito.when(articleRepository.existsById(1L)).thenReturn(true);
        Mockito.when(articleRepository.save(articleToUpdate)).thenReturn(articleToUpdate);

        ResponseEntity<ArticleDTO> response = this.restTemplate.exchange("http://localhost:" + port + "/article/1", HttpMethod.PUT, new HttpEntity<>(articleCreation), ArticleDTO.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(52, response.getBody().getPrice());
    }
}
