package com.royalways.testproject;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class TtsActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextView txtEnglish;
    private TextView txtHindi;
    private TextView txtPunjabi;
    private Button btnEnglish;
    private Button btnHindi;
    private Button btnPunjabi;

    private final int REQ_CODE_SPEECH_INPUT = 100;

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtEnglish = (TextView) findViewById(R.id.txt_english);
        txtHindi = (TextView) findViewById(R.id.txt_hindi);
        txtPunjabi = (TextView) findViewById(R.id.txt_punjabi);

        btnEnglish = (Button) findViewById(R.id.btn_english);
        btnHindi = (Button) findViewById(R.id.btn_hindi);
        btnPunjabi = (Button) findViewById(R.id.btn_punjabi);


        tts = new TextToSpeech(this,this);


        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String output = txtEnglish.getText().toString();
                speechDataEnglish(output);
            }
        });

        btnHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String output = txtHindi.getText().toString();
                speechDataHindi(output);
            }
        });

        btnPunjabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String output = txtPunjabi.getText().toString();
                speechDataPunjabi(output);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

//    private void promptSpeechInput() {
//        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "hi");
//        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
//                getString(R.string.speech_prompt));
//        try {
//            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
//        } catch (ActivityNotFoundException a) {
//            Toast.makeText(getApplicationContext(),
//                    getString(R.string.not_support),
//                    Toast.LENGTH_SHORT).show();
//        }
//    }

    private void speechDataEnglish(String output) {

        tts.setLanguage(Locale.ENGLISH);
        tts.speak(output, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void speechDataHindi(String output) {

        tts.setLanguage(new Locale("hi"));
        tts.setSpeechRate(1.5f);
        tts.speak(output, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void speechDataPunjabi(String output) {

        try {
            tts.setLanguage(new Locale("pa"));
            tts.speak(output, TextToSpeech.QUEUE_FLUSH, null);
        }catch (Exception e){
            Log.d("not working",e.getMessage());
        }

    }



    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {

                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                Log.e("TTS", "This Language is not supported");
            } else {

//                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    @Override
    protected void onDestroy() {

        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
