package com.example.dario.myconverter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Control {
    private Model model = new Model();
    private Activity activity;
    private ChangesCalculator changesCalculator;

    public Control( Activity activity,ChangesCalculator changesCalculator) {
        this.activity = activity;
        this.changesCalculator=changesCalculator;
    }

    public void setupUI(){
        model.setEnter((Button)activity.findViewById(R.id.invio));
        model.setFrom((Button)activity.findViewById(R.id.fromButton));
        model.setTo((Button)activity.findViewById(R.id.toButton));
        model.setAmount((EditText)activity.findViewById(R.id.etQuantita));
        model.setResults((TextView)activity.findViewById(R.id.tvRisultato));
        model.setTextViewFrom((TextView)activity.findViewById(R.id.from));
        model.setTextViewTo((TextView)activity.findViewById(R.id.to));
        model.setMenu1(new PopupMenu(model.getFrom().getContext(),model.getFrom()));
        model.setMenu2(new PopupMenu(model.getTo().getContext(),model.getTo()));
    }

    public void setupListeners(){
        model.getEnter().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("cliccato"," invio");
                changesCalculator.setQuantity(Double.valueOf(model.getAmount().getText().toString()));
                if(changesCalculator.isReady()){
                    DecimalFormat df = new DecimalFormat("#.##");
                    model.getResults().setText(df.format(changesCalculator.calculate()));
                }
                else {
                    Toast.makeText(activity, "Inserire tutti i dati", Toast.LENGTH_LONG).show();
                }
            }
        });

        model.getFrom().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(model.getMenu1());
            }
        });

        model.getTo().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showMenu(model.getMenu2());
            }
        });
    }
    private void showMenu(PopupMenu menu){
        menu.show();
    }

    public Model getModel() {
        return model;
    }

}
