package com.lkb.shoppingcart.bean;

import com.lkb.shoppingcart.common.time.TimeHelper;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Setter
@Getter
@Entity
//@Table(name="budget")
public class Budget implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name="aa";
    private double budget=0;
    private String beginTime="";
    private String endTime="";
    private double leftBudget = 0;
    private String descr="";
    public Budget(){
        this.beginTime = TimeHelper.timeStamp2Date(String.valueOf(System.currentTimeMillis()),"yyyy-MM-dd HH:mm:ss");
    }
    @OneToOne
    @JoinColumn(name = "consumerBook_id")
    private ConsumerBook consumerBook;
}
