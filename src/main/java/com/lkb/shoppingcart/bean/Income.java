package com.lkb.shoppingcart.bean;

import com.lkb.shoppingcart.common.time.TimeHelper;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Income extends Expense{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JoinColumn(name = "consumerBook_id")
    @ManyToOne
    private ConsumerBook consumerBook;
    private String name;
    private String costType;
    private String incomeOrOutcome;
    private double expense = 0;
    private Timestamp time;
    private String descr;
    public Income(){
        super();
    }
    public Income(Expense expense){
        if(expense.getTime() == null){
            expense.setTime(TimeHelper.time2SqlDate(TimeHelper.timeStamp2Date(String.valueOf(System.currentTimeMillis()),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
        }
        setTime(expense.getTime());
        setExpense(expense.getExpense());
        setIncomeOrOutcome(expense.getIncomeOrOutcome());
        setConsumerBook(expense.getConsumerBook());
        setCostType(expense.getCostType());
        setDescr(expense.getDescr());
        setName(expense.getName());
    }
}
