package com.example.dario.myconverter;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class PopupMenuControl {


    private JsonRequest request = new JsonRequest();

    public void fetchData(Context context,final Control control, final ChangesCalculator changesCalculator) {

        request.doRequest(context,new VolleyCallback() {
            @Override
            public void onSuccess(final JSONObject jsonObject) {

                try {
                    Iterator<String> iter = jsonObject.getJSONObject("rates").keys();
                    while(iter.hasNext()){

                        String key = iter.next();
                        control.getModel().getMenu1().getMenu().add(key);
                        control.getModel().getMenu2().getMenu().add(key);
                    }
                    control.getModel().getMenu1().setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
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
                    control.getModel().getMenu2().setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
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
