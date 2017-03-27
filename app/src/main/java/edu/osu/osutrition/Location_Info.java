package edu.osu.osutrition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Location_Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location__info);
    }

    public void toLocationMenu(View view) {
        Intent intent = new Intent(this, Location_Menu.class);
        startActivity(intent);
    }

    public void toMap(View view) {
        Intent intent = new Intent(this, Search_Maps.class);
        startActivity(intent);
    }
}
