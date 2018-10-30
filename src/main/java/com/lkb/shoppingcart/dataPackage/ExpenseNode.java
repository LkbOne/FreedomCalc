package com.lkb.shoppingcart.dataPackage;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class ExpenseNode {
    String time = "";
    List expenseList = new ArrayList();
    public ExpenseNode(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        time = sdf.format(date.getTime());
    }
}
