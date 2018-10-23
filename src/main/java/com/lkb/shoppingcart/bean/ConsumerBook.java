package com.lkb.shoppingcart.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
@Setter
@Getter
@Entity
//@Table(name="ConsumerBook")
public class ConsumerBook implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "consumerBook")
    private List<Expense> expense;
    @OneToOne(mappedBy = "consumerBook")
    private Budget budget;
    private String name;
    public ConsumerBook(){}
    public ConsumerBook(String name){
        this.name = name;
    }
    public ConsumerBook(long id,List<Expense> expense,Budget budget,String name){
        this.id = id;
        this.budget =budget;
        this.expense =expense;
        this.name = name;
    }
}
