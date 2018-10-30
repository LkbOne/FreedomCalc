package com.lkb.shoppingcart.dao;

import com.lkb.shoppingcart.bean.Outcome;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
@Repository
public interface OutcomeRepository extends PagingAndSortingRepository<Outcome,Long> {
    List<Outcome> findByName(String name);
}

//

//
