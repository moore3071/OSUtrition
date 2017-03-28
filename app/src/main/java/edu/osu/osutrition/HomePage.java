package edu.osu.osutrition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            Log.d("STATE", savedInstanceState.toString());
        }
        Log.d("CREATION", "onCreate() is being execued!");
        setContentView(R.layout.activity_home_page);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void toMapSearch(View view) {
        Intent intent = new Intent(this, Search_Maps.class);
        startActivity(intent);
    }

    public void toFilterSearch(View view) {
        Intent intent = new Intent(this, Search_Filter.class);
        startActivity(intent);
    }

    public void toLocationInfo(View view) {
        Intent intent = new Intent(this, Berry_Cafe_Info.class);
        startActivity(intent);
    }

    public void toLocationMenu(View view) {
        Intent intent = new Intent(this, Berry_Cafe_Menu.class);
        startActivity(intent);
    }

    public void toSettings(View view) {
        Intent intent = new Intent(this, Settings_Screen.class);
        startActivity(intent);
    }
}
