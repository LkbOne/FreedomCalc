package com.lkb.shoppingcart.dao;

import com.lkb.shoppingcart.bean.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExpenseDao {
    @Autowired
    ExpenseRepository expenseRepository;
    public Expense consumerBookByName(String name){
        return expenseRepository.findByName(name).get(0);
    }
    public Expense consumerBookById(Long id){
        return expenseRepository.findById(id).get();
    }
    public boolean saveExpense(Expense expense){
        expenseRepository.save(expense);
        return true;
    }
}
