package com.lkb.shoppingcart.dao;

import com.lkb.shoppingcart.bean.Budget;
import com.lkb.shoppingcart.bean.ConsumerBook;
import com.lkb.shoppingcart.bean.Income;
import com.lkb.shoppingcart.bean.Outcome;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import sun.awt.SunHints;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface ConsumerBookRepository extends PagingAndSortingRepository<ConsumerBook,Long> {
    List<ConsumerBook> findByName(String name);
    @Query(value = "SELECT * FROM pangpang.outcome where outcome.time >= ?1 \n" +
            "UNION ALL \n" +
            "select * from pangpang.income where income.time >= ?1 \n" +
            "order by time desc;",nativeQuery = true)
    List findByTimestamp(Timestamp timestamp);
//    @Query(value="select id,name from ConsumerBook")
//    List<ConsumerBook> findAll();
}
