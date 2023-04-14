package com.example.CS205;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.media.MediaPlayer;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;




import com.example.CS205.network.ApiInterface;
import com.example.CS205.network.NetUtility;

import java.util.Map;


/**
 * MainActivity is the entry point to our application.
 */
public class MainActivity extends Activity {

    private Game game;
    String name;

    private Button button;
    private ApiInterface apiInterface;

    private MediaPlayer mediaPlayer;

    EditText nameInput;
    Animation animBlink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity.java", "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set content view to game, so that objects in the Game class can be rendered to the screen

//        Map<String, Integer> leaderboard = NetUtility.getLeaderboard();
//        boolean saveSuccess = NetUtility.saveScoreToLeaderboard("CS205", 1000);

        //For moving background animation
        final ImageView backgroundOne = (ImageView) findViewById(R.id.background1);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background2);
        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX);
                backgroundTwo.setTranslationX(translationX - width);
            }
        });
        animator.start();


        //BGM

        mediaPlayer = MediaPlayer.create(this, R.raw.openingmusic);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();


        button = (Button) findViewById(R.id.button);
        animBlink = AnimationUtils.loadAnimation(this,R.anim.blink);
        button.setVisibility(View.VISIBLE);
        button.startAnimation(animBlink);
        nameInput = (EditText)findViewById(R.id.nameInput);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();
                name=name.substring(5);
                openActivity2();

            }
        });
    }

    public void openActivity2() {
        game = new Game(this,name);
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
        // Release the MediaPlayer when the activity is destroyed
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    public void onBackPressed() {
        // Comment out "super.onBackPressed()" to disable button
        //super.onBackPressed();
    }

}
