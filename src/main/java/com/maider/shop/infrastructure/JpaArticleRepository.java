package com.maider.shop.infrastructure;

import com.maider.shop.domain.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaArticleRepository extends JpaRepository<Article,String>, JpaSpecificationExecutor<Article> {
}
