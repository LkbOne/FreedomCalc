package com.lkb.shoppingcart.bean;

import com.google.gson.annotations.Expose;
import com.lkb.shoppingcart.common.time.TimeHelper;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Setter
@Getter
@Entity
// @Table(name="budget")
public class Budget implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    private String name = "";
    private double budget = 0 ;
    private Timestamp beginTime;
    private Timestamp endTime;
    private double leftBudget = 0;
    private String descr = "";
    public Budget(){
        beginTime = TimeHelper.time2SqlDate(TimeHelper.timeStamp2Date(String.valueOf(System.currentTimeMillis()),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
        endTime = TimeHelper.time2SqlDate(TimeHelper.calcFieldTime(2,"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
    }
    @Expose(serialize = false)
    @OneToOne
    @JoinColumn(name = "consumerBook_id")
    private ConsumerBook consumerBook;
}
