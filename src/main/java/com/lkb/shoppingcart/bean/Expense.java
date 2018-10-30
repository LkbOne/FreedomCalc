package com.lkb.shoppingcart.bean;

import com.google.gson.annotations.Expose;
import com.lkb.shoppingcart.common.time.TimeHelper;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
//@Entity
//@Table(name="Expense")
public class Expense implements Serializable,ExpenseInt{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    @JoinColumn(name = "consumerBook_id")
//    @ManyToOne
    private ConsumerBook consumerBook;
    private String name;
    private String costType;
    private String incomeOrOutcome;
    private double expense = 0;
    private Timestamp time;
    private String descr;
    public Expense(){
//        time = TimeHelper.time2SqlDate(TimeHelper.timeStamp2Date(String.valueOf(System.currentTimeMillis()),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
    }
}
