package edu.osu.osutrition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Search_Filter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__filter);
    }

    public void toLocationInfo(View view) {
        Intent intent = new Intent(this, Berry_Cafe_Info.class);
        startActivity(intent);
    }
}
