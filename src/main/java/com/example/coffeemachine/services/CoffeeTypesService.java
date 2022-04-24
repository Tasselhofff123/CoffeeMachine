package com.example.coffeemachine.services;

import com.example.coffeemachine.models.Coffee;

import java.util.List;

public interface CoffeeTypesService {
    List<Coffee> findAll();
    Coffee findById(int id);
}
