package irishirajj.github.io.rjmusicplayer;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    AudioManager audiomanager;//This will make a variable to interact with system audio.
    public void play(View view){
        player.start();
    }
    public void pause(View view){
        player.pause();
    }
    public void stop(View view){
        player.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player=MediaPlayer.create(this, R.raw.lovemelikeyoudo);

        audiomanager= (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int maxVol= audiomanager.getStreamMaxVolume(audiomanager.STREAM_MUSIC);
        int curVol= audiomanager.getStreamVolume(audiomanager.STREAM_MUSIC);


        //STEP 1 :First Take the SeekBar in your variable:
        SeekBar seekVol= findViewById(R.id.seekVol);
        //STEP 2 :Set the maximum and the minimum value for the volume:
        seekVol.setMax(maxVol);
        seekVol.setProgress(curVol);
        //STEP 3: Now, to get the maxVol and curVol, we need to interact with the system:
        //We should write the code for this before the current code .


        //STEP 5:Now, we will set the function which which change the volume if there is a change in the seekbar
        seekVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audiomanager.setStreamVolume(audiomanager.STREAM_MUSIC,progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        //TO ADD THE BAR THAT DISPLAYS THE SONG PROGRESS:
        SeekBar seekProg =findViewById(R.id.seekProg);
        seekProg.setMax(player.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekProg.setProgress(player.getCurrentPosition());
            }
        },0, 500);
        seekProg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    player.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

 }
}