package com.example.dario.myconverter;

public class ChangesCalculator {

    private double from;
    private double to;
    private double quantity;

    public double calculate(){
        return (quantity/from)*to;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getFrom() {
        return from;
    }
    public boolean isReady(){
        return from != 0 && to != 0 && quantity != 0;
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
