package com.lkb.shoppingcart.dao;

import com.lkb.shoppingcart.bean.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IncomeDao {
    @Autowired
    IncomeRepository incomeRepository;
    public Income incomeByName(String name){
        return incomeRepository.findByName(name).get(0);
    }
    public Income incomeById(Long id){
        return incomeRepository.findById(id).get();
    }
    public boolean saveIncome(Income income){
        incomeRepository.save(income);
        return true;
    }
}
