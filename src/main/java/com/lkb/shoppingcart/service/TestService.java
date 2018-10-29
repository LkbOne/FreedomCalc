package com.lkb.shoppingcart.service;

import com.lkb.shoppingcart.bean.Budget;
import com.lkb.shoppingcart.bean.ConsumerBook;
import com.lkb.shoppingcart.bean.Expense;
import com.lkb.shoppingcart.dao.BudgetDao;
import com.lkb.shoppingcart.dao.ConsumerBookDao;
import com.lkb.shoppingcart.dao.ExpenseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.*;

@Component
@Service
public class TestService {
    @Autowired
    BudgetDao budgetDao;
    @Autowired
    ConsumerBookDao consumerBookDao;
    @Autowired
    ExpenseDao expenseDao;
    public ConsumerBook createConsumerBook(String name){
        ConsumerBook consumerBook = new ConsumerBook();
        consumerBook.setName(name);
        consumerBookDao.saveConsumerBook(consumerBook);
        return consumerBook;
    }
    // i need a budget type perfer to number of int
    // need to discuss
    public boolean addBudget(Budget budget,Long consumerBook_id){
        ConsumerBook consumerBook = consumerBookDao.consumerBookById(consumerBook_id);
        budget.setConsumerBook(consumerBook);
        budget.setLeftBudget(budget.getBudget());
        if(consumerBook.getExpense()!=null&&consumerBook.getExpense().size()>0){
            for(int i=0;i<consumerBook.getExpense().size();i++) {
                budget = calcBudget(budget, consumerBook.getExpense().get(i));
            }
        }
        return budgetDao.saveBudget(budget);
    }
    public ConsumerBook findConsumerBook(Long consumerBook_id){
        ConsumerBook consumerBook = consumerBookDao.consumerBookById(consumerBook_id);
         return fileter(consumerBook);
    }
    public ConsumerBook fileter(ConsumerBook consumerBook){
        List<Expense> expenses = consumerBook.getExpense();
        for(int i=0;i<expenses.size();i++){
            Expense expense = expenses.get(i);
            expense.setConsumerBook(null);
            expenses.set(i,expense);
        }
        consumerBook.setExpense(expenses);
        if(consumerBook.getBudget()!=null){
            Budget budget = consumerBook.getBudget();
            budget.setConsumerBook(null);
            consumerBook.setBudget(budget);
        }
        return consumerBook;
    }
    public Budget calcBudget(Budget budget,Expense expense){
        String incomeOrOutcome = expense.getIncomeOrOutcome();
        double left=budget.getLeftBudget();
        if(incomeOrOutcome.equals("收入")){
            left=budget.getLeftBudget()+expense.getExpense();
        }else if(incomeOrOutcome.equals("支出")){
            left = budget.getLeftBudget() - expense.getExpense();
        }
        budget.setLeftBudget(left);
        return budget;
    }

    public ConsumerBook addExpense(Expense expense,Long consumerBook_id){
        ConsumerBook consumerBook = consumerBookDao.consumerBookById(consumerBook_id);
        if(consumerBook.getBudget() != null) {
            budgetDao.saveBudget(calcBudget(consumerBook.getBudget(), expense));
        }
        expense.setConsumerBook(consumerBook);
        expenseDao.saveExpense(expense);
        return consumerBook;
    }
    public double[] calcMoney(List<Expense> expenses){
        double[] calcMoney = new double[]{0,0};
        for(int i=0;i<expenses.size();i++){
            Expense expense = expenses.get(i);
            if(expense.getIncomeOrOutcome().equals("收入")){
                calcMoney[0] += expense.getExpense();
            }else if(expense.getIncomeOrOutcome().equals("支出")){
                calcMoney[1] += expense.getExpense();
            }
        }
        return calcMoney;
    }
    public Map<String,Object> calcOutcomeAndIncome(List<Expense> expenses){
        Map<String,Object> map = new HashMap<>();
        List<Expense> outCome = new ArrayList<>();
        List<Expense> inCome = new ArrayList<>();
        for(int i=0;i<expenses.size();i++){
            Expense expense = expenses.get(i);
            expense.setConsumerBook(null);
            if(expense.getIncomeOrOutcome().equals("收入")){
                inCome.add(expense);
            }else if(expense.getIncomeOrOutcome().equals("支出")){
                outCome.add(expense);
            }
        }
        double[] incomeOrOutComeMoney = calcMoney(expenses);
        map.put("OutComeList",outCome);
        map.put("InComeList",inCome);
        map.put("InCome",incomeOrOutComeMoney[0]);
        map.put("OutCome",incomeOrOutComeMoney[1]);
        map.put("AllIncome",incomeOrOutComeMoney[0] - incomeOrOutComeMoney[1]);
        return map;
    }
    public Map<String,Object> expenseOfComsumerBook(Long consumerBook_id){
        ConsumerBook consumerBook = consumerBookDao.consumerBookById(consumerBook_id);
        return calcOutcomeAndIncome(consumerBook.getExpense());
    }
    public List<Map<String, String>> allConsumerBookIdAndName(){
        Iterator<ConsumerBook> iterator = consumerBookDao.findAllIdAndName();
        List<Map<String,String>> list = new ArrayList<>();
        while(iterator.hasNext()){
            ConsumerBook consumerBook = iterator.next();
            Map<String,String> map = new HashMap<>();
            map.put("id",String.valueOf(consumerBook.getId()));
            map.put("name",consumerBook.getName());
            list.add(map);
        }
        return list;
    }
//    public ConsumerBook modifyExense(){
//
//    }
//    public ConsumerBook modifyBudget(){
//
//    }

}
