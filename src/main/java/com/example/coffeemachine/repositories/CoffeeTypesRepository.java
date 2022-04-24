package com.example.coffeemachine.repositories;

import com.example.coffeemachine.models.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeTypesRepository extends JpaRepository<Coffee, Integer> {
}
