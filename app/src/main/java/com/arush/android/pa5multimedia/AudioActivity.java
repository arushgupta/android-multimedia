package com.arush.android.pa5multimedia;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class AudioActivity extends AppCompatActivity {
    static MediaPlayer mediaPlayerShortAudio;
    static MediaPlayer mediaPlayerLongAudio;
    TextView textView;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (mediaPlayerShortAudio == null) {
            mediaPlayerShortAudio = MediaPlayer.create(this, R.raw.swvader02);
        }
        if (mediaPlayerLongAudio == null) {
            mediaPlayerLongAudio = MediaPlayer.create(this, R.raw.star_wars_theme_song);
        }
        textView = (TextView) findViewById(R.id.textView);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    public void doShortAudio(View view) {
        mediaPlayerShortAudio.start();
    }

    private Timer timer;

    public void doLongAudio(View view) {
        Button button = (Button) view;
        if (mediaPlayerLongAudio.isPlaying()) {
            timer.cancel();
            timer.purge();
            timer = null;
            mediaPlayerLongAudio.pause();
            button.setText("Start Audio");
        }
        else {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                private Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        updateDisplay();
                    }
                };
                @Override
                public void run() {
                    runOnUiThread(myRunnable);
                }
            }, 0, 100); //update every 100ms
            mediaPlayerLongAudio.start();
            button.setText("Pause Audio");
        }
        updateDisplay();
    }

    private void updateDisplay() {
        String s = "Duration: " + mediaPlayerLongAudio.getDuration() + "\n" +
                "CurrentPosition: " + mediaPlayerLongAudio.getCurrentPosition();
        progressBar.setMax(mediaPlayerLongAudio.getDuration());
        progressBar.setProgress(mediaPlayerLongAudio.getCurrentPosition());
        textView.setText(s);
    }
}
