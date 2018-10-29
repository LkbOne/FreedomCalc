package com.lkb.shoppingcart;

import com.lkb.shoppingcart.bean.Budget;
import com.lkb.shoppingcart.bean.Expense;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingcartApplicationTests {
    private TestRestTemplate template = new TestRestTemplate();
    @Test
    public void testCalcTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String endDate=null;
        String beginDate=format.format(Calendar.getInstance().getTime());
        calendar.add(Calendar.YEAR,1);
        endDate=format.format(calendar.getTime());
        System.out.println("beginDate:"+beginDate);
        System.out.println("endDate:"+endDate);
    }
    @Test
    public void addBudget() {
        String url = "http://localhost:90/ConsumerBook/budget/1";
        Budget budget = new Budget();
        budget.setId(1);
        budget.setBudget(3.3);
        budget.setDescr("wahaha3");
        String result = template.postForObject(url,budget, String.class);
        Assert.assertEquals(result, "success");
    }

//    @Test
//    public void addBudget() {
//        String url = "http://localhost:90/ConsumerBook/budget/1";
//        String result = template.postForObject(url, String.class);
//        Assert.assertEquals(result, "success");
//    }

    @Test
    public void createConsumerBook() {
        String url = "http://localhost:90/ConsumerBook/";
        String name = "pangpang";
        String result = template.postForObject(url,name, String.class);
        Assert.assertEquals(result, "{\"id\":2,\"expense\":null,\"budget\":null,\"name\":\"pangpang\"}");
    }

    @Test
    public void addExpense() {
        String url = "http://localhost:90/ConsumerBook/Expense/1";
        Expense expense = new Expense();
        expense.setCostType("消费");
        expense.setDescr("wahaha");
        expense.setExpense(0.5);
        expense.setIncomeOrOutcome("支出");
        expense.setName("test");
        String result = template.postForObject(url,expense, String.class);
        Assert.assertEquals(result, "success");
    }
//    String url = "http://localhost:90/ConsumerBook/1/expense";
}
