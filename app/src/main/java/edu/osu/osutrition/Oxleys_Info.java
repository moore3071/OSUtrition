package edu.osu.osutrition;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Matias on 3/27/2017.
 */

public class Oxleys_Info extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxleys_info);
    }

    public void toLocationMenu(View view) {
        Intent intent = new Intent(this, Oxleys_Menu.class);
        startActivity(intent);
    }

    public void toMap(View view) {
        Intent intent = new Intent(this, Search_Maps.class);
        startActivity(intent);
    }
}
