package edu.osu.osutrition;

import android.app.Activity;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import static com.google.android.gms.auth.api.credentials.PasswordSpecification.gt;

public class APIHelper {

    private static final String API_BASE = "content.osu.edu/v2/api/v1/";
    private Activity activity;
    private RequestQueue queue;

    public void APIHelper(Activity a) {
        activity = a;
    }

    public void queryAPI(final String endpoint) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, API_BASE+endpoint, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
// TODO Auto-generated method stub
                if(endpoint=="dining/locations/menus") {
                    DatabaseHelper.updateLocations(response);
                } else {
                    DatabaseHelper.updateMenus(response);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
// TODO Auto-generated method stub

            }
        });

        MySingleton.getInstance(activity.getApplicationContext()).getRequestQueue().add(jsObjRequest);
    }
}
