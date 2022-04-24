package com.example.coffeemachine.services.impl;

import com.example.coffeemachine.models.Coffee;
import com.example.coffeemachine.repositories.CoffeeTypesRepository;
import com.example.coffeemachine.services.CoffeeTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CoffeeTypesServiceImpl implements CoffeeTypesService {
    private CoffeeTypesRepository coffeeTypesRepository;
    @Autowired
    public CoffeeTypesServiceImpl(CoffeeTypesRepository coffeeTypesRepository) {
        this.coffeeTypesRepository = coffeeTypesRepository;
    }

    @Override
    public List<Coffee> findAll() {
        return coffeeTypesRepository.findAll();
    }

    @Override
    public Coffee findById(int id) {
        Optional<Coffee> coffee = coffeeTypesRepository.findById(id);
       return coffee.orElse(null);
    }

}
