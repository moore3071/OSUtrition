package edu.osu.osutrition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Berry_Cafe_Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berry_cafe__info);
    }

    public void toBerryCafeMenu(View view) {
        Intent intent = new Intent(this, Berry_Cafe_Menu.class);
        startActivity(intent);
    }

    public void toMap(View view) {
        Intent intent = new Intent(this, Search_Maps.class);
        startActivity(intent);
    }
}
