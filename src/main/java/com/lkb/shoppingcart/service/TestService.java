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
        consumerBookDao.saveConsumerBook(consumerBook);
        return consumerBook;
    }
//    public ConsumerBook modifyConsumerBook(String book){
//
//    }
    public boolean addBudget(Budget budget){
        ConsumerBook consumerBook =consumerBookDao.consumerBookByName("bb");
        budget.setConsumerBook(consumerBook);
        return budgetDao.saveBudget(budget);
    }
    public boolean findBudget(Long id){
        Budget budget=budgetDao.budgetById(id);
        return true;
    }
    public ConsumerBook addExpense(Expense expense){

        ConsumerBook consumerBook1 =consumerBookDao.consumerBookByName("bb");
        consumerBook1.getExpense().add(expense);
//        consumerBookDao.saveConsumerBook(consumerBook);
//        expense.setConsumerBook(consumerBook);
        expenseDao.saveExpense(expense);

        return consumerBook1;
    }
//    public ConsumerBook modifyExense(){
//
//    }
//    public ConsumerBook modifyBudget(){
//
//    }

}
