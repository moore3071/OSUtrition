package edu.osu.osutrition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class Oxleys_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxleys_menu);
    }
    public void toNutrition0(View view){
        Intent intent = new Intent(this, NutritionData0.class);
        startActivity(intent);
    }

    
}
