package edu.osu.osutrition;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    //DB name and table names
    private final static String DB_NAME = "dining_info";
    private final static String TABLE_LOCATIONS = "dining_locations";
    private final static String TABLE_MENUS = "dining_menus";
    private final static String TABLE_ITEMS = "dining_items";
    private final static String TABLE_CUISINES = "dining_cuisines";
    private final static String TABLE_LOCATIONS_CUISINE = "dining_locations_cuisines_relation";
    private final static String TABLE_MENU_ITEMS = "dining_menus_items_relation";

    //Insert statements for each table
    //TODO make up a schema for items
    private static String INSERT_LOCATION = "insert into " + TABLE_LOCATIONS + "(id, name, address, diningstyle, photo, summary, menu_id) values (?,?,?,?,?,?,?)";
    private static String INSERT_MENU = "insert into " + TABLE_MENUS + "(id, name) values (?, ?)";
    private static String INSERT_ITEM = "insert into " + TABLE_ITEMS + "() values ()";
    private static String INSERT_CUISINE = "insert into " + TABLE_CUISINES + "(name) values (?)";
    private static String INSERT_LOCATION_CUISINE = "insert into " + TABLE_LOCATIONS_CUISINE + "(menu, cuisine) values (?,?)";
    private static String INSERT_MENU_ITEM = "insert into " + TABLE_MENU_ITEMS + "(menu, item) values (?,?)";

    private Context app;
    private SQLiteDatabase db;


    //Constructor
    public DatabaseHelper(Context c) {
        app = c.getApplicationContext();
        CustomOpenHelper openHelper = new CustomOpenHelper(app);
        this.db = openHelper.getWritableDatabase();
    }

    //The main logic for updating any entry
    public long updateEntry(String table, String[] vals) {
        SQLiteStatement insert;
        switch(table) {
            case TABLE_LOCATIONS:
                insert = this.db.compileStatement(INSERT_LOCATION);
                break;
            case TABLE_MENUS:
                insert = this.db.compileStatement(INSERT_MENU);
                break;
            case TABLE_ITEMS:
                insert = this.db.compileStatement(INSERT_ITEM);
                break;
            case TABLE_CUISINES:
                insert = this.db.compileStatement(INSERT_CUISINE);
                break;
            case TABLE_LOCATIONS_CUISINE:
                insert = this.db.compileStatement(INSERT_LOCATION_CUISINE);
                break;
            case TABLE_MENU_ITEMS:
                insert = this.db.compileStatement(INSERT_MENU_ITEM);
                break;
            default:
                return -1;
        }
        insert.bindAllArgsAsStrings(vals);
        return insert.executeInsert();
    }

    //Retrieve a list of cuisines
    private List<String> getCuisines() {
        List<String> list = new ArrayList<String>();
        Cursor cursor = this.db.query(TABLE_CUISINES, new String[] {"name"}, null, null, null, null, "name desc");
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;

    }

    //Returns a list of location info for use in maps
    public List<ArrayList<String>> getLocations() {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        int count = 0;
        Cursor cursor = this.db.query(TABLE_LOCATIONS, new String[] { "name", "address", "summary" }, null , null, null, null, "name desc");
        if (cursor.moveToFirst()) {
            do {
                result.add(new ArrayList<String>());
                result.get(count).add(cursor.getString(0));
                result.get(count).add(cursor.getString(1));
                result.get(count).add(cursor.getString(2));
                count++;
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return result;
    }

    //Updates the list of locations, cuisines, and their link. This is called by
    //Volley when the API is hit.
    public void updateLocations(JSONObject locs) {
        JSONArray searchThis;
        List<String> cuisines = getCuisines();
        try {
            this.db.delete(TABLE_LOCATIONS, null, null);
            searchThis = locs.getJSONObject("data").getJSONArray("locationsMenus");
            for(int i=0; i<searchThis.length(); i++) {
                JSONObject tmp = searchThis.getJSONObject(i);
                String[] vars = new String[7];
                vars[0] = tmp.getString("id");
                vars[1] = tmp.getString("locationName");
                vars[2] = tmp.getString("address1")+" "+tmp.getString("city")+" "+tmp.getString("state")+" "+tmp.getString("zip");
                vars[3] = tmp.getString("diningStyle");
                vars[4] = tmp.getString("photoUrl");
                vars[5] = tmp.getString("summary");
                vars[6] = tmp.getJSONArray("locationMenu").getJSONObject(0).getJSONArray("sections").getJSONObject(0).getString("sectionID");
                updateEntry(TABLE_LOCATIONS, vars);

                //Add any missing cuisines
                JSONArray searchTwo = tmp.getJSONArray("cuisines");
                this.db.delete(TABLE_LOCATIONS_CUISINE, null, null);
                for(int l=0; l<searchTwo.length(); l++) {
                    String currentCuisine = searchTwo.getJSONObject(l).getString("cuisineType");
                    if(cuisines.contains(currentCuisine)) {
                        updateEntry(TABLE_CUISINES, new String[] {currentCuisine});
                    }
                    //Link locations to cuisines
                    updateEntry(TABLE_LOCATIONS_CUISINE, new String[] {tmp.getString("id"), currentCuisine});
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //TODO implement this function using the above as a guide
    //Updates the list of locations, cuisines, and their link. This is called by
    //Volley when the API is hit.
    public void updateMenus(JSONObject menu) {

    }

    //Ignore this, it's needed for the DB helper to function
    private static class CustomOpenHelper extends SQLiteOpenHelper {
        CustomOpenHelper(Context context) {
            super(context, DB_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_LOCATIONS + "(id INTEGER PRIMARY KEY, name TEXT, address TEXT," +
                    " diningstyle TEXT, photo TEXT, summary TEXT, menu_id INTEGER)");
            db.execSQL("CREATE TABLE " + TABLE_MENUS + "(id INTEGER PRIMARY KEY, name TEXT)");
            db.execSQL("CREATE TABLE " + TABLE_ITEMS + "(id INTEGER PRIMARY KEY, name TEXT)");
            db.execSQL("CREATE TABLE " + TABLE_CUISINES + "(id INTEGER PRIMARY KEY, name TEXT)");
            db.execSQL("CREATE TABLE " + TABLE_LOCATIONS_CUISINE + "(id INTEGER PRIMARY KEY, menu INTEGER, cuisine TEXT)");
            //TODO add to this schema to include every we need
            db.execSQL("CREATE TABLE " + TABLE_MENU_ITEMS + "(id INTEGER PRIMARY KEY, menu INTEGER, item INTEGER)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

}
