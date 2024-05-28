package com.maider.shop.infrastructure;

import com.maider.shop.domain.entities.Article;
import com.maider.shop.domain.entities.ArticleFilter;
import com.maider.shop.domain.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
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
    @Override
    public Article save(Article article) { return jpaRepo.save(article);}
    @Override
    public List<Article> findAll() { return jpaRepo.findAll();}
    @Override
    public void deleteById(Long id) {jpaRepo.deleteById(id);}
    @Override
    public Boolean existsById(Long id) { return jpaRepo.existsById(id);}

    @Override
    public Article getReferenceById(Long id) { return jpaRepo.getReferenceById(id);}
}
