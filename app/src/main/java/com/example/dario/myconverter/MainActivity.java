package com.example.dario.myconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.PopupMenu;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity {

    private ChangesCalculator changesCalculator = new ChangesCalculator();
    private JsonRequest request;

    private Control control = new Control(this,changesCalculator);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        control.setupUI();
        control.setupListeners();
        request = new JsonRequest(this);
        fetchData(control.getModel().getMenu1(),control.getModel().getMenu2());
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
                                control.getModel().getTextViewFrom().setText(item.getTitle().toString());
                                changesCalculator.setFrom(Double.valueOf(jsonObject.getJSONObject("rates").getString(item.getTitle().toString())));
                                Log.e("from: ",""+changesCalculator.getFrom());

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
                                control.getModel().getTextViewTo().setText(item.getTitle().toString());
                                changesCalculator.setTo(Double.valueOf(jsonObject.getJSONObject("rates").getString(item.getTitle().toString())));
                                Log.e("to: ",""+changesCalculator.getTo());
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
