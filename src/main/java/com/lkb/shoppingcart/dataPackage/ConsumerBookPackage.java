package com.lkb.shoppingcart.dataPackage;

import com.lkb.shoppingcart.bean.Budget;
import com.lkb.shoppingcart.bean.ConsumerBook;
import com.lkb.shoppingcart.bean.Income;
import com.lkb.shoppingcart.bean.Outcome;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Consumer;

@Getter
@Setter
public class ConsumerBookPackage {
    private long id;
    private List<Income> income;
    private List<Outcome> outcome;
    private Budget budget;
    private String name;
    private List<ExpenseNode> expenseNodes;
    public ConsumerBookPackage(ConsumerBook consumerBook, List<ExpenseNode> list){
        id = consumerBook.getId();
        income = consumerBook.getIncome();
        outcome = consumerBook.getOutcome();
        budget = consumerBook.getBudget();
        name = consumerBook.getName();
        expenseNodes = list;
    }
}
