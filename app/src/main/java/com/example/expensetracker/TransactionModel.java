package com.example.expensetracker;

public class TransactionModel {
    private String id,description,amount,type,date;


    public TransactionModel(){
    }

    public TransactionModel(String id,String description,String amount,String type,String date){
        this.id=id;
        this.description=description;
        this.amount=amount;
        this.type=type;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
