package com.me.hatem.familytube.services;


import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by NS
 */

public class VolleyRequest {

    private final Context _context;
    public VolleyRequest(Context context) {
        _context    = context;
    }

    /* GET */
    public void GET (final VolleyCallback callback, String url, final Map post_params) {
        StringRequest stringRequest = new StringRequest( url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                callback.onSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                try {
                    String ms = volleyError.getMessage().toString();
                    Toast.makeText(_context, "There is error in : "+ms , Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(_context, "Something Went Wrong : "+ e.toString() , Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return post_params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map header = new HashMap();
                header.put("User-Agent","ANDROID");
                return header;
            }
        };
        MySingleton.getInstance(_context).addToRequestQueue(stringRequest);

    }

    public interface VolleyCallback{
        void onSuccess(String result);
    }

}
