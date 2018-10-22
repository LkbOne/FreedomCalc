package com.lkb.shoppingcart.dao;

import com.lkb.shoppingcart.bean.Budget;
import com.lkb.shoppingcart.bean.ConsumerBook;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ConsumerBookRepository extends PagingAndSortingRepository<ConsumerBook,Long> {
    List<ConsumerBook> findByName(String name);
}
