package com.lkb.shoppingcart.controller;

import com.lkb.shoppingcart.bean.Budget;
import com.lkb.shoppingcart.bean.ConsumerBook;
import com.lkb.shoppingcart.bean.Expense;
import com.lkb.shoppingcart.dataPackage.ConsumerBookPackage;
import com.lkb.shoppingcart.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Controller
@RequestMapping(value = "/ConsumerBook")
public class TestController {
    @Autowired
    TestService testService;
    @RequestMapping(value = "/budget/{consumerBookId}", method = RequestMethod.POST)
    String addBudget(@PathVariable ("consumerBookId")Long consumerBookId,@RequestBody Budget budget){
        testService.addBudget(budget,consumerBookId);
        return "success";
    }

    @RequestMapping(value = "/{consumerBookId}", method = RequestMethod.GET)
    ConsumerBookPackage getConsumerBook(@PathVariable("consumerBookId") Long consumerBookId) throws ParseException {
        return testService.findConsumerBook(consumerBookId);
    }
    @RequestMapping(value = "/{consumerBookId}/expense", method = RequestMethod.GET)
    Map<String, Object> getExpenseOfComsumerBook(@PathVariable("consumerBookId") Long consumerBookId){
        return testService.expenseOfComsumerBook(consumerBookId);
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    List<Map<String, String>> getAllConsumerBookIdAndName(){
        return testService.allConsumerBookIdAndName();
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)

    ConsumerBook createConsumerBook(@RequestBody String name){
         return testService.createConsumerBook(name);
    }
    @RequestMapping(value = "/Expense/{consumerBookId}", method = RequestMethod.POST)
    String addExpense2ConsumerBook(@PathVariable ("consumerBookId")Long consumerBookId,@RequestBody Expense expense){
        testService.addExpense(expense,consumerBookId);
        return "success";
    }

    @RequestMapping(value = "/Expense/{consumerBookId}/pie", method = RequestMethod.GET)
    Map<String,Object> showExpense2Pie(@PathVariable ("consumerBookId")Long consumerBookId){
        return testService.expenseOfComsumerBook2Pie(consumerBookId);
    }
}
