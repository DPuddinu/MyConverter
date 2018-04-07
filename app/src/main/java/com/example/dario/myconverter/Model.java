package com.example.dario.myconverter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

public class Model {

    private Button enter,from,to;
    private TextView results, textViewFrom,textViewTo;
    private EditText amount;
    private PopupMenu menu1,menu2;

    public PopupMenu getMenu1() {
        return menu1;
    }

    public void setMenu1(PopupMenu menu1) {
        this.menu1 = menu1;
    }

    public PopupMenu getMenu2() {
        return menu2;
    }

    public void setMenu2(PopupMenu menu2) {
        this.menu2 = menu2;
    }

    public Button getEnter() {
        return enter;
    }

    public void setEnter(Button enter) {
        this.enter = enter;
    }

    public Button getFrom() {
        return from;
    }

    public void setFrom(Button from) {
        this.from = from;
    }

    public Button getTo() {
        return to;
    }

    public void setTo(Button to) {
        this.to = to;
    }

    public TextView getResults() {
        return results;
    }

    public void setResults(TextView results) {
        this.results = results;
    }

    public TextView getTextViewFrom() {
        return textViewFrom;
    }

    public void setTextViewFrom(TextView textViewFrom) {
        this.textViewFrom = textViewFrom;
    }

    public TextView getTextViewTo() {
        return textViewTo;
    }

    public void setTextViewTo(TextView textViewTo) {
        this.textViewTo = textViewTo;
    }

    public EditText getAmount() {
        return amount;
    }

    public void setAmount(EditText amount) {
        this.amount = amount;
    }
}
