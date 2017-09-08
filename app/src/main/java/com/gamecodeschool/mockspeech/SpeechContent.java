package com.gamecodeschool.mockspeech;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SpeechContent extends AppCompatActivity implements MediaPlayerInterface {

    private TextView mTextMessage;
    private MediaPlayerService player;
    boolean serviceBound = false;
    private int speechID;
    private int speecherId;
    private Intent intent;
    public static final String Broadcast_PAUSE_AUDIO = "com.gamecodeschool.mockspeech.mediaplayer.PauseAudio";
    public static final String Broadcast_RESUME_AUDIO = "com.gamecodeschool.mockspeech.mediaplayer.ResumeAudio";
    Bundle bundle;
    android.support.v4.app.FragmentTransaction ft;

    SpeechItem speech;
    Speecher speecher;
    ArrayList<Words> words;
    SpeechDAO dao;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_english:
                    switchToFragment(new SpeechFragment());
                    break;
                case R.id.navigation_japanese:
                    switchToFragment(new TranslationFragment());
                    break;
                case R.id.navigation_voc:
                    switchToFragment(new WordsFragment());
                    break;
            }
            return false;
        }

    };

    //Binding this Client to the AudioPlayer Service
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MediaPlayerService.LocalBinder binder = (MediaPlayerService.LocalBinder) service;
            player = binder.getService();
            serviceBound = true;

            Toast.makeText(SpeechContent.this, "Service Bound", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceBound = false;
        }
    };

    public void playAudio(String media) {
        //Check is service is active
        if (!serviceBound) {
            Intent playerIntent = new Intent(this, MediaPlayerService.class);
            playerIntent.putExtra("media", media);
            startService(playerIntent);
            bindService(playerIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        } else {
            //Service is active
            //Send media resume BroadcastReceiver
            Intent broadcastIntent = new Intent(Broadcast_RESUME_AUDIO);
            sendBroadcast(broadcastIntent);
        }
    }

    @Override
    public void pauseAudio(String media) {
        if (serviceBound) {
            Intent broadcastIntent = new Intent(Broadcast_PAUSE_AUDIO);
            sendBroadcast(broadcastIntent);
        } else {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_content);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        intent = getIntent();
        speechID = intent.getIntExtra("speechId", 0);
        speecherId = intent.getIntExtra("speecherId", 0);
        //Load the speech content with specific speechID
        loadSpeech(speechID);
        bundle = new Bundle();
        bundle.putSerializable("speech", speech);
        bundle.putSerializable("speecher", speecher);
        bundle.putParcelableArrayList("words", words);

        if (savedInstanceState == null) {
            SpeechFragment newFragment = new SpeechFragment();
            newFragment.setArguments(bundle);
            ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.content, newFragment).commit();
        }
    }

    public void switchToFragment(android.support.v4.app.Fragment fragment) {
        fragment.setArguments(bundle);
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment).commit();

    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("ServiceState", serviceBound);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        serviceBound = savedInstanceState.getBoolean("ServiceState");
    }

    private void loadSpeech(int speechId) {
        dao = new SpeechDAO(getApplicationContext());
        dao.createDatabase();
        dao.open();
        speech = dao.getSpeechById(speechId);
        speecher = dao.getSpeecherById(speechId);
        words = dao.getWordsById(speechId);

        if (speech != null) {
            Toast.makeText(this, "Speech loaded!", Toast.LENGTH_SHORT);
        } else {
            Toast.makeText(this, "Speech is not ready!", Toast.LENGTH_SHORT);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceBound) {
            unbindService(serviceConnection);
            //service is active
            player.stopSelf();
        }
    }
}
