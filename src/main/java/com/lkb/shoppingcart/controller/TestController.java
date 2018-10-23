package com.lkb.shoppingcart.controller;

import com.lkb.shoppingcart.bean.Budget;
import com.lkb.shoppingcart.bean.ConsumerBook;
import com.lkb.shoppingcart.bean.Expense;
import com.lkb.shoppingcart.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Controller
@RequestMapping(value = "/ConsumerBook")
public class TestController {
    @Autowired
    TestService testService;
//    @RequestMapping(value = "/product", method = RequestMethod.GET)
//    Product getProductName(){
//        return testService.product();
//    }
//    @RequestMapping(value = "/product", method = RequestMethod.POST)
//    String setProductPrice(@RequestBody double price){
//        return testService.setProjectPrice(price);
//    }
//
//    @RequestMapping(value = "/product/{name}", method = RequestMethod.GET)
//    String setProductName(@PathVariable("name") String name){
//        return testService.setProductName(name);
//    }
//    @RequestMapping(method = RequestMethod.POST)
//    String createConsumerBook(@RequestBody String name){
//    }
    @RequestMapping(value = "/budget/{consumerBookId}", method = RequestMethod.POST)
    String addBudget(@PathVariable ("consumerBookId")Long consumerBookId,@RequestBody Budget budget){
        testService.addBudget(budget,consumerBookId);
        return "success";
    }

    @RequestMapping(value = "/{consumerBookId}", method = RequestMethod.GET)
    ConsumerBook getConsumerBook(@PathVariable("consumerBookId") Long consumerBookId){
//        System.out.println("consumerBookId"+consumerBookId);
//        return testService.findConsumerBook(consumerBookId);
        return testService.findConsumerBook(consumerBookId);

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
}
