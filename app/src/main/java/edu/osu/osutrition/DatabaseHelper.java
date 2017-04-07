package edu.osu.osutrition;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper {
    private static String DB_NAME = "dining_info";
    private static String TABLE_LOCATIONS = "dining_locations";
    private static String TABLE_MENUS = "dining_menus";
    private static String TABLE_ITEMS = "dining_items";
    private static String TABLE_CUISINES = "dining_cuisines";
    private static String TABLE_LOCATIONS_CUISINE = "dining_locations_cuisines_relation";
    private static String TABLE_MENU_ITEMS = "dining_menus_items_relation";

    private Context app;
    private SQLiteDatabase db;


    public void DatabaseHelper(Context c) {
        app = c.getApplicationContext();
        CustomOpenHelper openHelper = new CustomOpenHelper(app);
        this.db = openHelper.getWritableDatabase();
    }

    //Horribly inefficient, but delete the entry if it exists and
    //then write it
    public long updateEntry(int id, Map<String, Object> vals) {
        return 1;
    }

    private long updateLocation(int id, String name, String address, String diningStyle, String image, String summary, int menu) {

    }

    public static void updateLocations(JSONObject locs) {
    }

    public static void updateMenus(JSONObject menu) {

    }

    private static class CustomOpenHelper extends SQLiteOpenHelper {
        CustomOpenHelper(Context context) {
            super(context, DB_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_LOCATIONS + "(id INTEGER PRIMARY KEY, name TEXT, address TEXT," +
                    " diningstyle TEXT, photo TEXT, summary TEXT, menu_id INTEGER)");
            db.execSQL("CREATE TABLE " + TABLE_MENUS + "(id INTEGER PRIMARY KEY, name TEXT)");
            db.execSQL("CREATE TABLE " + TABLE_ITEMS + "(id INTEGER PRIMARY KEY, name TEXT");
            db.execSQL("CREATE TABLE " + TABLE_CUISINES + "(id INTEGER PRIMARY KEY, name TEXT");
            db.execSQL("CREATE TABLE " + TABLE_LOCATIONS_CUISINE + "(id INTEGER PRIMARY KEY, menu INTEGER, cuisine INTEGER");
            db.execSQL("CREATE TABLE " + TABLE_MENU_ITEMS + "(id INTEGER PRIMARY KEY, menu INTEGER, item INTEGER");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

}
