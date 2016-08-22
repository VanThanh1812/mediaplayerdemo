package com.example.vanthanh.mediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
TextView txttenbaihat,txtbatdau,txtketthuc;
    private double tgBatDau=0.0;
    private double tgKetThuc=0.0;
    MediaPlayer mediaPlayer;
    private  static int loadSeekBar=0;
    SeekBar seekBar;
    Handler myHandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtbatdau=(TextView)findViewById(R.id.textView3);
        txtketthuc=(TextView)findViewById(R.id.textView4);
        txttenbaihat=(TextView)findViewById(R.id.textView);
        mediaPlayer=MediaPlayer.create(this,R.raw.buongdoitaynhaura);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
    }

    public void play(View view) {
        mediaPlayer.start();
        tgBatDau=mediaPlayer.getCurrentPosition();
        tgKetThuc=mediaPlayer.getDuration();
       // Toast.makeText(getApplicationContext(),String.valueOf(tgBatDau), Toast.LENGTH_SHORT).show();
        if (loadSeekBar == 0){
            seekBar.setMax((int)tgKetThuc);
        }
        seekBar.setProgress((int) tgBatDau);
        long phutBatDau= TimeUnit.MILLISECONDS.toMinutes((long) tgBatDau);
        long giayBatDau=TimeUnit.MILLISECONDS.toSeconds((long)tgBatDau)-
                TimeUnit.MINUTES.toSeconds(phutBatDau);
        //
        txtbatdau.setText(String.format("%d min %d sec", phutBatDau, giayBatDau));

        long phutKetthuc=TimeUnit.MILLISECONDS.toMinutes((long) tgKetThuc);
        long giayKetThuc=TimeUnit.MILLISECONDS.toSeconds((long) tgKetThuc)-
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) tgKetThuc));
        txtketthuc.setText(String.format("%d min %d sec",phutKetthuc,giayKetThuc));

        //
        myHandler.postDelayed(capnhatSeekbar,100);
 //       seekBar.setProgress((int)tgBatDau);
    }
    private Runnable capnhatSeekbar=new Runnable() {
        @Override
        public void run() {
            tgBatDau=mediaPlayer.getCurrentPosition();
            tgKetThuc=mediaPlayer.getDuration();
            // Toast.makeText(getApplicationContext(),String.valueOf(tgBatDau), Toast.LENGTH_SHORT).show();
            if (loadSeekBar == 0){
                seekBar.setMax((int)tgKetThuc);
            }
            seekBar.setProgress((int) tgBatDau);
            long phutBatDau= TimeUnit.MILLISECONDS.toMinutes((long) tgBatDau);
            long giayBatDau=TimeUnit.MILLISECONDS.toSeconds((long)tgBatDau)-
                    TimeUnit.MINUTES.toSeconds(phutBatDau);
            //
            txtbatdau.setText(String.format("%d min %d sec",phutBatDau,giayBatDau));
            //
            myHandler.postDelayed(this,100);

        }
    };
    public void pause(View view) {
        mediaPlayer.pause();
    }

    public void rew(View v) {
        double tghientai=tgBatDau-5000;
        if(tghientai>=0) {
            mediaPlayer.seekTo((int)tghientai);
        }
    }

    public void ff(View v) {
        double tghientai=tgBatDau+5000;
        if(tghientai<=tgKetThuc) {
            mediaPlayer.seekTo((int)tghientai);
        }
    }
}
