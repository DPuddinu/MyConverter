package com.example.dario.myconverter;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.widget.PopupMenu;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

public class JsonRequest{

    private Context context;

    public JsonRequest(Context context) {
        this.context = context;
    }

    public void doRequest(final VolleyCallback callback){
        String url = "http://data.fixer.io/api/latest?access_key=ab3517852500e170c420bda3ce80dd4d";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        callback.onSuccess(response);
                                          }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        // Access the RequestQueue through your singleton class.

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }


}
