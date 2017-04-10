package edu.osu.osutrition;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

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
                boolean network = ErrorDisplay.isNetworkUp(activity);
                if(network) {
                    ErrorDisplay.makeError(activity, "Failed to get info", "Information couldn't be retrieved from OSU api. The app may not function correctly.", false);
                } else {
                    ErrorDisplay.makeError(activity, "Failed to get info", "Please enable networking. This app may not function or function inaccurately without metworking.", false);
                }
            }
        });

        if(jsObjRequest!=null&&activity!=null) {
            MySingleton.getInstance(activity.getApplicationContext()).getRequestQueue().add(jsObjRequest);
        }
    }
}
