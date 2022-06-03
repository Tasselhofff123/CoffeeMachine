package com.example.coffeemachine.services;

import com.example.coffeemachine.models.Coffee;
import com.example.coffeemachine.services.interfaces.CoffeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CoffeeService {
    private final CoffeeServiceInterface csi;

    @Autowired
    public CoffeeService(CoffeeServiceInterface csi) {
        this.csi = csi;
    }

    public List<Coffee> findAll() {
        return csi.findAll();
    }

    public Coffee find(int id) {
        return csi.find(id);
    }

}
