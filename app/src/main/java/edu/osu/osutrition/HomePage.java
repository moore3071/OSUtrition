package edu.osu.osutrition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            Log.d("STATE",savedInstanceState.toString());
        }
        Log.d("CREATION","onCreate() is being execued!");
        setContentView(R.layout.activity_home_page);
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
