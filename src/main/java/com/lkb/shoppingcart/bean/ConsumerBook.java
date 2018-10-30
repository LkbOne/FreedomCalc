package com.lkb.shoppingcart.bean;

import com.lkb.shoppingcart.dataPackage.Pie;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Setter
@Getter
@Entity
//@Table(name="ConsumerBook")
public class ConsumerBook implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "consumerBook")
    private List<Income> income;
    @OneToMany(mappedBy = "consumerBook")
    private List<Outcome> outcome;
    @OneToOne(mappedBy = "consumerBook")
    private Budget budget;
    private String name;
    public ConsumerBook(){}
    public ConsumerBook(String name){
        this.name = name;
    }
    public ConsumerBook(long id,List<Income> income,List<Outcome> outcome,Budget budget,String name){
        this.id = id;
        this.budget = budget;
        this.income = income;
        this.outcome = outcome;
        this.name = name;
    }
    public List reverse(List list){
        List list1 = new ArrayList<>();
        for(int i=list.size()-1;i>=0;i--){
            list1.add(list.get(i));
        }
        return list1;
    }
    public List<Outcome> reverseOutcome(){
        return (List<Outcome>)reverse(outcome);
    }
    public List<Income> reverseIncome(){
        return (List<Income>)reverse(income);
    }
    public List<Outcome> setComsumerBookOfOutcome2Null(){
        for(int i=0;outcome != null && i < outcome.size();i++){
            Outcome expense = outcome.get(i);
            expense.setConsumerBook(null);
            outcome.set(i,expense);
        }
        return outcome;
    }
    public List<Income> setComsumerBookOfIncome2Null(){
        for(int i = 0;income != null&& i < income.size();i++){
            Income expense = income.get(i);
            expense.setConsumerBook(null);
            income.set(i,expense);
        }
        return income;
    }
    public void setComsumerOfBudget2Null(){
        if(budget != null){
            budget.setConsumerBook(null);
        }
    }

    public double calcOutcome(){
        double outcomeOfAll = 0;
        for(int i = 0;i < outcome.size();i++){
            outcomeOfAll = outcome.get(i).getExpense();
        }
        return outcomeOfAll;
    }
    public List<Pie> calcIncomePie(){
        setComsumerBookOfIncome2Null();
        List<Pie> pies = new ArrayList<>();
        Map<String,Pie> mapList = new HashMap<>();
        List<String> keyString = new ArrayList<>();
        for(int i = 0; i<income.size();i++){
            Income aIncome = income.get(i);
            if(!mapList.containsKey(aIncome.getCostType())){
                mapList.put(aIncome.getCostType(),new Pie(aIncome));
                keyString.add(aIncome.getCostType());
            }else{
                Pie pie =  mapList.get(aIncome.getCostType());
                mapList.put(aIncome.getCostType(),pie.addIncome(aIncome));
            }
        }
        for(int i=0;i<keyString.size();i++){
            pies.add(mapList.get(keyString.get(i)));
        }
        return pies;
    }
    public Map<String,Double> calcTotalcomePie(){
        Map<String,Double> map = new HashMap<String,Double>();
        map.put("totalIncome",calcIncome());
        map.put("totalOutcome",calcOutcome());
        return map;
    }
    public Map<String,Object> pieMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("totalCome",calcTotalcomePie());
        map.put("outcome",calcOutcomePie());
        map.put("income",calcIncomePie());
        return map;
    }
    public List<Pie> calcOutcomePie(){
        setComsumerBookOfOutcome2Null();
        List<Pie> pies = new ArrayList<>();
        Map<String,Pie> mapList = new HashMap<>();
        List<String> keyString = new ArrayList<>();
        for(int i = 0; i<outcome.size();i++){
            Outcome aOutcome = outcome.get(i);
            if(!mapList.containsKey(aOutcome.getCostType())){
                mapList.put(aOutcome.getCostType(),new Pie(aOutcome));
                keyString.add(aOutcome.getCostType());
            }else{
                Pie pie =  mapList.get(aOutcome.getCostType());
                mapList.put(aOutcome.getCostType(),pie.addOutcome(aOutcome));
            }
        }
        for(int i=0;i<keyString.size();i++){
            pies.add(mapList.get(keyString.get(i)));
        }
        return pies;
    }
    public double calcIncome(){
        double incomeOfAll = 0;
        for(int i = 0;i < income.size();i++){
            incomeOfAll = income.get(i).getExpense();
        }
        return incomeOfAll;
    }
}
