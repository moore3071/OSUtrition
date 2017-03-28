package edu.osu.osutrition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class Berry_Cafe_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berry_cafe_menu);
    }
    public void toNutrition0(View view){
        Intent intent = new Intent(this, NutritionData0.class);
        startActivity(intent);
    }
    public void toNutrition1(View view){
        Intent intent = new Intent(this, NutritionData1.class);
        startActivity(intent);
    }
    public void toNutrition2(View view){
        Intent intent = new Intent(this, NutritionData2.class);
        startActivity(intent);
    }
    
}
