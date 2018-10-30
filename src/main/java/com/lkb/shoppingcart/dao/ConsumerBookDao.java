package com.lkb.shoppingcart.dao;

import com.lkb.shoppingcart.bean.ConsumerBook;
import com.lkb.shoppingcart.bean.Income;
import com.lkb.shoppingcart.bean.Outcome;
import com.lkb.shoppingcart.common.time.TimeHelper;
import org.hibernate.query.internal.QueryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

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
    public List findByTimestamp(Timestamp timestamp) throws ParseException {
        List expenses=  consumerBookRepository.findByTimestamp(timestamp);
        List<Income> incomes = new ArrayList<>();
        for(int i=0;i<expenses.size();i++){
            Object[] aExpense = (Object[])expenses.get(i);
            Income income = new Income();
            income.setId(Long.valueOf(aExpense[0].toString()));
            income.setCostType(aExpense[1].toString());
            income.setDescr(aExpense[2].toString());
            income.setExpense(Double.valueOf(aExpense[3].toString()));
            income.setIncomeOrOutcome(aExpense[4].toString());
            income.setName(aExpense[5].toString());
            income.setTime(TimeHelper.time2SqlDateFromFormatTime(aExpense[6].toString(),"yyyy-MM-dd HH:mm:ss"));
            incomes.add(income);
        }
        return incomes;
    }
}
