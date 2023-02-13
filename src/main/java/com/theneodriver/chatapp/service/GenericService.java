package com.theneodriver.chatapp.service;

public interface GenericService<T, ID> {

    T findById(ID id);
    
    T save(T object);
    
    T update(ID id, T object);
    
    void deleteById(ID id);
    
}