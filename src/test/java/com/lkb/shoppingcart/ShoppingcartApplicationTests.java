package com.lkb.shoppingcart;

import com.lkb.shoppingcart.bean.Budget;
import com.lkb.shoppingcart.bean.Expense;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingcartApplicationTests {
    private TestRestTemplate template = new TestRestTemplate();
    @Test
    public void contextLoads() {
        String url = "http://localhost:90/ConsumerBook/budget";
        Budget budget = new Budget();
        budget.setId(1);
        budget.setBudget(3.3);
        budget.setDescr("wahaha3");
        String result = template.postForObject(url,budget, String.class);
        Assert.assertEquals(result, "success");
    }

    @Test
    public void budgetById() {
        String url = "http://localhost:90/ConsumerBook/budget/1";
        String result = template.getForObject(url, String.class);
        Assert.assertEquals(result, "success");
    }

    @Test
    public void createConsumerBook() {
        String url = "http://localhost:90/ConsumerBook/";
        String name = "bb";
        String result = template.postForObject(url,name, String.class);
        Assert.assertEquals(result, "success");
    }

    @Test
    public void addExpense() {
        String url = "http://localhost:90/ConsumerBook/bb";
        Expense expense = new Expense();
        expense.setCostType("消费");
        expense.setDescr("wahaha");
        expense.setIncomeOrOutcome("income");
        expense.setName("test");
        String result = template.postForObject(url,expense, String.class);
        Assert.assertEquals(result, "success");
    }
}
