package com.example.coffeemachine.services.interfaces;


import java.util.List;
import com.example.coffeemachine.models.Coffee;
public interface CoffeeTypesServiceInterface {
    Coffee find(int id);

    List<Coffee> findAll();
}
