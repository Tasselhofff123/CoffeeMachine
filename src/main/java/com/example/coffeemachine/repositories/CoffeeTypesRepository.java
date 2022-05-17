package com.example.coffeemachine.repositories;

import com.example.coffeemachine.models.Coffee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeTypesRepository extends CrudRepository<Coffee, Integer> {
}
