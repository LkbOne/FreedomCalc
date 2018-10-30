package com.lkb.shoppingcart.dao;

import com.lkb.shoppingcart.bean.Income;
import com.lkb.shoppingcart.bean.Outcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OutcomeDao {
    @Autowired
    OutcomeRepository outcomeRepository;
    public Outcome outcomeByName(String name){
        return outcomeRepository.findByName(name).get(0);
    }
    public Outcome outcomeById(Long id){
        return outcomeRepository.findById(id).get();
    }
    public boolean saveOutcome(Outcome outcome){
        outcomeRepository.save(outcome);
        return true;
    }
}

