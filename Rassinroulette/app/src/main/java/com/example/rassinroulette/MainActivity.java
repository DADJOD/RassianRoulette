package com.example.rassinroulette;

import android.annotation.TargetApi;
import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends Activity {
    protected SoundPool soundPool;
    protected int soundShot;
    protected int soundShotFalse;
    protected int soundBaraban;
    private ImageView bloodImage;
    protected int randomBullet;

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        super.setRequestedOrientation(requestedOrientation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSoundPool();
        loadSound();
        init();
    }

    private void init() {
        bloodImage = findViewById(R.id.imageViewBlood);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void createSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    private void loadSound() {
        soundShot = soundPool.load(this, R.raw.revolver_shot, 1);
        soundShotFalse = soundPool.load(this, R.raw.gun_false, 1);
        soundBaraban = soundPool.load(this, R.raw.revolver_baraban, 1);
    }

    public void onClickShot(View view) {
        if (randomBullet == 1) {
            soundPool.play(soundShot, 1, 1, 1, 0, 1);
            bloodImage.setVisibility(View.VISIBLE);
        } else {
            soundPool.play(soundShotFalse, 1, 1, 1, 0, 1);
        }
    }

    public void onClickBaraban(View view) {
        soundPool.play(soundBaraban, 1, 1, 1, 0, 1);
        bloodImage.setVisibility(View.GONE);
        randomBullet = new Random().nextInt(6);
        Log.d("MainActivity", "---------------------------------- " + randomBullet);  //данное сообщение позволяет увидеть в logcat какое число выпало из randomBullet
    }
}