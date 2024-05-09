package com.maider.shop.result;

public interface Result<TValue, TError> {

    Boolean isSuccess();
    TValue getValue();
    TError getError();

}
