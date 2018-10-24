package com.lkb.shoppingcart.dao;

import com.lkb.shoppingcart.bean.ConsumerBook;
import org.hibernate.query.internal.QueryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

//    @PersistenceContext
//    private EntityManager em;
    public Iterator<ConsumerBook> findAllIdAndName(){
         return consumerBookRepository.findAll().iterator();
    }
}
