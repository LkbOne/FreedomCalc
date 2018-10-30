package com.lkb.shoppingcart.dao;

import com.lkb.shoppingcart.bean.Income;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

@Repository
public interface IncomeRepository extends PagingAndSortingRepository<Income,Long> {
    List<Income> findByName(String name);
    @Query(value = "select * FROM income where income.time>= ?1", nativeQuery = true)
    List<Income> findByTime(Timestamp timestamp);
}
