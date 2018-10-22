package com.lkb.shoppingcart.dao;

import com.lkb.shoppingcart.bean.ConsumerBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConsumerBookDao {
    @Autowired
    ConsumerBookRepository consumerBookRepository;
    public ConsumerBook consumerBookByName(String name){
        return consumerBookRepository.findByName(name).get(0);
    }
    public ConsumerBook consumerBookById(Long id){
        return consumerBookRepository.findById(id).get();
    }
    public boolean saveConsumerBook(ConsumerBook consumerBook){
       consumerBookRepository.save(consumerBook);
        return true;
    }
}
