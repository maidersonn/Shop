package com.maider.shop.domain.entities;

public interface Bag<T> {

    void add(T t);
    T remove();

}
