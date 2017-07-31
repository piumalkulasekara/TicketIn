package com.example.piumalk.ticketin;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by piumalk on 7/31/2017.
 */

public class Parser {

    public Parser() {
    }

    public static void parseDate(Context context, final String date, final int user, final int from, final int to, final int price) {

        StringRequest request = new StringRequest(Request.Method.POST, "http://90867480.ngrok.io/api/book", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAAAAAGGGGGG", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user", String.valueOf(user));
                params.put("from", String.valueOf(from));
                params.put("to", String.valueOf(to));
                params.put("price", "LKR " + price);
                params.put("date", date);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }


}
