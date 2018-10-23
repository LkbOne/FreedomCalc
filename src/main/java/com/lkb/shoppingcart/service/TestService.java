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

import java.util.List;

@Component
@Service
public class TestService {
    @Autowired
    BudgetDao budgetDao;
    @Autowired
    ConsumerBookDao consumerBookDao;
    @Autowired
    ExpenseDao expenseDao;
//    Product product = new Product();
//    public Product product(){
//        return product;
//    }
//    public String setProductName(String name){
//        product.setName(name);
//        return "success";
//    }
//    public String setProjectPrice(Double price){
//        product.setPrice(price);
//        return "success";
//    }
    public ConsumerBook createConsumerBook(String name){
        ConsumerBook consumerBook = new ConsumerBook();
        consumerBook.setName(name);
        consumerBookDao.saveConsumerBook(consumerBook);
        return consumerBook;
    }
//    public ConsumerBook modifyConsumerBook(String book){
//
//    }
    public boolean addBudget(Budget budget,Long consumerBook_id){
        ConsumerBook consumerBook =consumerBookDao.consumerBookById(consumerBook_id);
        budget.setConsumerBook(consumerBook);
        return budgetDao.saveBudget(budget);
    }
    public ConsumerBook findConsumerBook(Long consumerBook_id){
        ConsumerBook consumerBook =consumerBookDao.consumerBookById(consumerBook_id);
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
    public ConsumerBook addExpense(Expense expense,Long consumerBook_id){
        ConsumerBook consumerBook =consumerBookDao.consumerBookById(consumerBook_id);
//        consumerBook.getExpense().add(expense);
//        consumerBookDao.saveConsumerBook(consumerBook);
        expense.setConsumerBook(consumerBook);
        expenseDao.saveExpense(expense);
        return consumerBook;
    }
//    public ConsumerBook modifyExense(){
//
//    }
//    public ConsumerBook modifyBudget(){
//
//    }

}
