package com.example.dario.myconverter;

public class ChangesCalculator {

    private double from;
    private double to;
    private double quantity;

    public double calculate(){
        return (quantity/from)*to;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getFrom() {
        return from;
    }
    public boolean isReady(){
        if(from!=0 && to !=0){
            return true;
        }
        return  false;
    }
    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }
}
