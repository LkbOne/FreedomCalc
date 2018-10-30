package com.lkb.shoppingcart.dao;

import com.lkb.shoppingcart.bean.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BudgetDao {
    @Autowired
    BudgetRepository budgetRepository;
//    public Budget budgetByName(String name){
//        return budgetRepository.findByName(name).get(0);
//    }
    public Budget budgetById(Long id){
        return budgetRepository.findById(id).get();
    }
    public boolean saveBudget(Budget budget){
        budgetRepository.save(budget);
        return true;
    }
}
