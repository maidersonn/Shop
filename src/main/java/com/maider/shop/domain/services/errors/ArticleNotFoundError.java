package com.maider.shop.domain.services.errors;

public class ArticleNotFoundError implements ArticleError {

    private String message;

    public ArticleNotFoundError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
