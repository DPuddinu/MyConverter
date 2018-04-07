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

    private Context context;
    private JsonRequest request;

    public PopupMenuControl(Context context) {
        this.context = context;
        request = new JsonRequest(context);
    }


    public void fetchData(final PopupMenu popupMenu1, final PopupMenu popupMenu2, final TextView from, final TextView to, final ChangesCalculator changesCalculator) {

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
                                from.setText(item.getTitle().toString());
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
                                to.setText(item.getTitle().toString());
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
