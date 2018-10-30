package com.lkb.shoppingcart.dao;

import com.lkb.shoppingcart.bean.Budget;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends PagingAndSortingRepository<Budget,Long> {
//    List<Budget> findByName(String name);
}
