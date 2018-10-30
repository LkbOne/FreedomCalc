package com.lkb.shoppingcart.dataPackage;

import com.lkb.shoppingcart.bean.Income;
import com.lkb.shoppingcart.bean.Outcome;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Pie {
    String type = "";
    double expense = 0;
    List expenses = new ArrayList();
    public Pie(Income income){
        expenses.add(income);
        type = income.getCostType();
        expense = income.getExpense();
    }
    public Pie(Outcome outcome){
        expenses.add(outcome);
        type = outcome.getCostType();
        expense = outcome.getExpense();
    }
    public Pie addIncome(Income income){
        expenses.add(income);
        expense += income.getExpense();
        return this;
    }
    public Pie addOutcome(Outcome outcome){
        expenses.add(outcome);
        expense += outcome.getExpense();
        return this;
    }
}
