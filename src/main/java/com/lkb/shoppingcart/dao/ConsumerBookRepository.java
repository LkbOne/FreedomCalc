package com.lkb.shoppingcart.dao;

import com.lkb.shoppingcart.bean.Budget;
import com.lkb.shoppingcart.bean.ConsumerBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import sun.awt.SunHints;

import java.util.List;
import java.util.Set;

public interface ConsumerBookRepository extends PagingAndSortingRepository<ConsumerBook,Long> {
    List<ConsumerBook> findByName(String name);

//    @Query(value="select id,name from ConsumerBook")
//    List<ConsumerBook> findAll();
}
