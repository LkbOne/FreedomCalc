package com.lkb.shoppingcart.service;

import com.lkb.shoppingcart.bean.*;
import com.lkb.shoppingcart.common.time.TimeHelper;
import com.lkb.shoppingcart.dao.*;
import com.lkb.shoppingcart.dataPackage.ConsumerBookPackage;
import com.lkb.shoppingcart.dataPackage.ExpenseNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

@Component
@Service
public class TestService {
    @Autowired
    BudgetDao budgetDao;
    @Autowired
    ConsumerBookDao consumerBookDao;
//    @Autowired
//    ExpenseDao expenseDao;
    @Autowired
    IncomeDao incomeDao;
    @Autowired
    OutcomeDao outcomeDao;
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
        if(consumerBook.getOutcome()!=null&&consumerBook.getOutcome().size()>0){
                budget = calcBudget(budget, consumerBook);
        }
        return budgetDao.saveBudget(budget);
    }
    public ConsumerBookPackage findConsumerBook(Long consumerBook_id) throws ParseException {
         ConsumerBook consumerBook = consumerBookDao.consumerBookById(consumerBook_id);
         return new ConsumerBookPackage(fileter(consumerBook),getExpenseForTimeStamp(TimeHelper.getTimeStamp(-6)));
    }
    public ConsumerBook fileter(ConsumerBook consumerBook){
        consumerBook.setComsumerBookOfIncome2Null();
        consumerBook.setComsumerBookOfOutcome2Null();
        consumerBook.setComsumerOfBudget2Null();
        consumerBook.setOutcome(consumerBook.reverseOutcome());
        consumerBook.setIncome(consumerBook.reverseIncome());
        return consumerBook;
    }
    public Budget calcBudget(Budget budget,ConsumerBook consumerBook){
        budget.setLeftBudget(budget.getLeftBudget() - consumerBook.calcOutcome());
        return budget;
    }
    public ConsumerBook addExpense(Expense expense,Long consumerBook_id){
        ConsumerBook consumerBook = consumerBookDao.consumerBookById(consumerBook_id);
        if(consumerBook.getBudget() != null) {
            budgetDao.saveBudget(calcBudget(consumerBook.getBudget(), consumerBook));
        }
        expense.setConsumerBook(consumerBook);
        if(expense.getIncomeOrOutcome().equals("income")){
            incomeDao.saveIncome(new Income(expense));
        }else{
            outcomeDao.saveOutcome(new Outcome(expense));
        }

        return consumerBook;
    }
    public Map<String,Object> calcOutcomeAndIncome(ConsumerBook consumerBook){
        Map<String,Object> map = new HashMap<>();
        double incomeOfAll = consumerBook.calcIncome();
        double outcomeOfAll = consumerBook.calcOutcome();
        consumerBook.setComsumerBookOfIncome2Null();
        consumerBook.setComsumerBookOfOutcome2Null();
        map.put("OutComeList",consumerBook.getOutcome());
        map.put("InComeList",consumerBook.getIncome());
        map.put("InCome",incomeOfAll);
        map.put("OutCome",outcomeOfAll);
        map.put("AllIncome",incomeOfAll - outcomeOfAll);
        return map;
    }
    public Map<String,Object> expenseOfComsumerBook(Long consumerBook_id){
        ConsumerBook consumerBook = consumerBookDao.consumerBookById(consumerBook_id);
        return calcOutcomeAndIncome(consumerBook);
    }
    public Map<String,Object> expenseOfComsumerBook2Pie(Long consumerBook_id){
        ConsumerBook consumerBook = consumerBookDao.consumerBookById(consumerBook_id);
        return consumerBook.pieMap();
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
    public List getExpenseForTimeStamp(Timestamp timestamp ) throws ParseException {
        List<Income> incomes=consumerBookDao.findByTimestamp(timestamp);
        int j = 0;
        List<ExpenseNode> expenseNodes = new ArrayList<>();
        for(int i = 0;i>= -6;i--){
            Date date000 = TimeHelper.nowDateFor000(i);
            long dataForMil = date000.getTime();
            List<Income> dataPackageIncome = new ArrayList<>();
            for(;j<incomes.size();j++){
                Income aIncome = incomes.get(j);
                long incomeTime = aIncome.getTime().getTime();
                if(incomeTime >= dataForMil){
                    dataPackageIncome.add(aIncome);
                    continue;
                }
                break;
            }
            ExpenseNode expenseNode = new ExpenseNode(date000);
            expenseNode.setExpenseList(dataPackageIncome);
            expenseNodes.add(expenseNode);
        }
        return expenseNodes;
    }
}
