package com.example.dario.myconverter;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

public class JsonRequest{


    private static String url = "http://data.fixer.io/api/latest?access_key=ab3517852500e170c420bda3ce80dd4d";

        public void doRequest(Context context,final VolleyCallback callback){


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
