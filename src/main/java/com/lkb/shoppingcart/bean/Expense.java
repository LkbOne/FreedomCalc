package com.lkb.shoppingcart.bean;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
//@Table(name="Expense")
public class Expense implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String costType;
    private String incomeOrOutcome;
    private double expense = 0;
    private String time;
    private String descr;
    @JoinColumn(name = "consumerBook_id")
    @ManyToOne
    private ConsumerBook consumerBook;
}
