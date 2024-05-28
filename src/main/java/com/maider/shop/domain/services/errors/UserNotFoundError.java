package com.maider.shop.domain.services.errors;

public class UserNotFoundError implements ShopError {
    private String message;

    public UserNotFoundError(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
