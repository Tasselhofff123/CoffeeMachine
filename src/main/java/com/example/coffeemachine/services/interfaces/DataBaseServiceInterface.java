package com.example.coffeemachine.services.interfaces;


import java.util.List;

public interface DataBaseServiceInterface<T> {
    T find(int id);

    List<T> findAll();
}
