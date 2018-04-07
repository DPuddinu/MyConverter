package com.example.dario.myconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ChangesCalculator changesCalculator = new ChangesCalculator();
    private  JsonRequest request;
    private Button invio,from,to;
    private TextView risultato, textViewFrom,textViewTo;
    private EditText quantità;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        request = new JsonRequest(this);

        invio = findViewById(R.id.invio);
        from = findViewById(R.id.fromButton);
        to = findViewById(R.id.toButton);
        quantità = findViewById(R.id.etQuantita);
        risultato = findViewById(R.id.tvRisultato);
        textViewFrom = findViewById(R.id.from);
        textViewTo = findViewById(R.id.to);

        final PopupMenu menu1 = new PopupMenu(from.getContext(),from);
        final PopupMenu menu2 = new PopupMenu(to.getContext(),to);

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu1.show();
            }
        });

        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu2.show();
            }
        });

        invio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changesCalculator.setQuantity(Double.valueOf(quantità.getText().toString()));
                DecimalFormat df = new DecimalFormat("#.##");
                risultato.setText(df.format(changesCalculator.calculate()));
            }
        });

        fetchData(menu1,menu2);
    }

    public void fetchData(final PopupMenu popupMenu1,final PopupMenu popupMenu2) {

        request.doRequest(new VolleyCallback() {
            @Override
            public void onSuccess(final JSONObject jsonObject) {

                try {
                    Iterator<String> iter = jsonObject.getJSONObject("rates").keys();
                    while(iter.hasNext()){

                        String key = iter.next();
                        popupMenu1.getMenu().add(key);
                        popupMenu2.getMenu().add(key);
                    }
                    popupMenu1.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            try {
                                textViewFrom.setText(item.getTitle().toString());
                                changesCalculator.setFrom(Double.valueOf(jsonObject.getJSONObject("rates").getString(item.getTitle().toString())));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return false;
                        }
                    });
                    popupMenu2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            try {
                                textViewTo.setText(item.getTitle().toString());
                                changesCalculator.setTo(Double.valueOf(jsonObject.getJSONObject("rates").getString(item.getTitle().toString())));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return false;
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
