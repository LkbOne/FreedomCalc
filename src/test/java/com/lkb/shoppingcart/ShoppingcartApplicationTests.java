package com.lkb.shoppingcart;

import com.lkb.shoppingcart.bean.Budget;
import com.lkb.shoppingcart.bean.Expense;
import com.lkb.shoppingcart.common.time.TimeHelper;
import com.lkb.shoppingcart.dao.ConsumerBookDao;
import com.lkb.shoppingcart.service.TestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingcartApplicationTests {
    private TestRestTemplate template = new TestRestTemplate();
    @Autowired
    TestService testService;
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
        Assert.assertEquals(result, "{\"id\":1,\"income\":null,\"outcome\":null,\"budget\":null,\"name\":\"pangpang\"}");
    }

    @Test
    public void addExpense() {
        String url = "http://localhost:90/ConsumerBook/Expense/1";
        Expense expense = new Expense();
        expense.setCostType("消费");
        expense.setDescr("wahahaee");
//        Timestamp timestamp =  TimeHelper.time2SqlDate(TimeHelper.calcFieldTime(2,"yyyy-MM-dd"),"yyyy-MM-dd");
//        expense.setTime(timestamp);
        expense.setExpense(0.5);
        expense.setIncomeOrOutcome("income");
        expense.setName("test");
        String result = template.postForObject(url,expense, String.class);
        Assert.assertEquals(result, "success");

        expense.setExpense(1);

        expense.setIncomeOrOutcome("outcome");
        result = template.postForObject(url,expense, String.class);
        Assert.assertEquals(result, "success");
    }
    @Autowired
    ConsumerBookDao consumerBookDao;
    @Test
    public void selectTimeStamp() throws ParseException {
        Timestamp timestamp =  TimeHelper.time2SqlDate(TimeHelper.calcFieldTime(2,"yyyy-MM-dd"),"yyyy-MM-dd");
        List a=consumerBookDao.findByTimestamp(timestamp);

        System.out.println("sdasdas");
        System.out.println("asssssadasdasdas");
    }
    @Test
    public void getExpenseForTimeStamp() throws ParseException {
        Date date = TimeHelper.nowDateFor000(-5);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Timestamp timestamp =  TimeHelper.time2SqlDate(format.format(date.getTime()),"yyyy-MM-dd");

        List a= testService.getExpenseForTimeStamp(timestamp);

        System.out.println("sdasdas");
        System.out.println("asssssadasdasdas");
    }
//    String url = "http://localhost:90/ConsumerBook/1/expense";
}
