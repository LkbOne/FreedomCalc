package com.lkb.shoppingcart.controller;

import com.lkb.shoppingcart.bean.Budget;
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
    @RequestMapping(value = "/budget", method = RequestMethod.POST)
    String getProductName(@RequestBody Budget budget){
        System.out.println("budget:"+budget);
        testService.addBudget(budget);
        return "success";
    }

    @RequestMapping(value = "/budget/{id}", method = RequestMethod.GET)
    String getProductName(@PathVariable("id") Long id){
        System.out.println("id:"+id);
        testService.findBudget(id);
        return "success";
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)
    String createConsumerBook(@RequestBody String name){
        System.out.println("name:"+name);
        testService.createConsumerBook(name);
        return "success";
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    String addExpense2ConsumerBook(@PathVariable ("name")String name,@RequestBody Expense expense){
        System.out.println("name:"+name);
        System.out.println("expense:"+expense.getCostType());
        testService.addExpense(expense);
        return "success";
    }
}
