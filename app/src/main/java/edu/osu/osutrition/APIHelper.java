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

    private static final String API_BASE = "https://content.osu.edu/v2/api/v1/";
    public static final String LOCATION_ENDPOINT = "dining/locations/menus";
    public static final String MENU_ENDPOINT = "dining/full/menu/section/";

    private DatabaseHelper db;
    private Activity activity;

    public APIHelper(Activity a) {
        activity = a;
        db = new DatabaseHelper(a.getApplicationContext());
    }

    //Call this to query an API endoint. Note that menu endpoints need a number to specify
    //the menu id
    //XXX
    public synchronized void queryAPI(final String endpoint) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, API_BASE+endpoint, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                switch(endpoint) {
                    case LOCATION_ENDPOINT:
                        db.updateLocations(response);
                        break;
                    case MENU_ENDPOINT:
                        db.updateMenus(response);
                        break;
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
// TODO Auto-generated method stub

            }
        });

        if(jsObjRequest!=null&&activity!=null) {
            MySingleton.getInstance(activity.getApplicationContext()).getRequestQueue().add(jsObjRequest);
        }
    }
}
