package com.example.dario.myconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ChangesCalculator changesCalculator = new ChangesCalculator();

    private Control control = new Control(this,changesCalculator);
    private PopupMenuControl popupMenuControl = new PopupMenuControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        control.setupUI();
        control.setupListeners();
        popupMenuControl.fetchData(this,control,changesCalculator);
    }
}
