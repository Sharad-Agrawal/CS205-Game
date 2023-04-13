package com.example.CS205;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.CS205.network.ApiInterface;
import com.example.CS205.network.NetUtility;

import java.util.Map;


/**
 * MainActivity is the entry point to our application.
 */
public class MainActivity extends Activity {

    private Game game;

    private Button button;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity.java", "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set content view to game, so that objects in the Game class can be rendered to the screen

        Map<String, Integer> leaderboard = NetUtility.getLeaderboard();
        boolean saveSuccess = NetUtility.saveScoreToLeaderboard("CS205", 1000);


        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();

            }
        });
    }

    public void openActivity2() {
        game = new Game(this);
        setContentView(game);
//        Intent intent = new Intent(this, Activity2.class);
//        startActivity(intent);
    }

    @Override
    protected void onStart() {
        Log.d("MainActivity.java", "onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity.java", "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity.java", "onPause()");
        game.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("MainActivity.java", "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity.java", "onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        // Comment out "super.onBackPressed()" to disable button
        //super.onBackPressed();
    }
}
