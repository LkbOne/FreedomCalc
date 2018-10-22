package com.lkb.shoppingcart.dao;

import com.lkb.shoppingcart.bean.Expense;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExpenseRepository extends PagingAndSortingRepository<Expense,Long> {
    List<Expense> findByName(String name);
}
