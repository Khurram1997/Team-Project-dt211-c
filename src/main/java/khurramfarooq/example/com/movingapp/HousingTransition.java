package khurramfarooq.example.com.movingapp;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;

public class HousingTransition extends AppCompatActivity {

    // Image buttons to start new activites/tasks
    ImageButton phonebook;
    ImageButton imageButton_MovingHouse;
    ImageButton Maps;
    ImageButton Photodiary;
    ImageButton Backbutton;

    // Text to Speech variables
    private TextToSpeech mTTS;
    private TextView mTextView;
    private Button mButtonSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_transition);

        // Implementation of Text to Speech
        mButtonSpeak = findViewById(R.id.sayit2);

        mTTS = new TextToSpeech( this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS)
                {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if(result== TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Log.e("TTS", "Initialization failed" );
                    }
                }
            }
        });

        mTextView = findViewById(R.id.tv2);
        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

        // Button to bring user to Moving House page
        imageButton_MovingHouse = (ImageButton) findViewById(R.id.imageButton_MovingHouse);
        imageButton_MovingHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "works!", Toast.LENGTH_LONG.show());
                //New intent to ensure user ends up, at home page of app after log in is successful
                Intent intent = new Intent(HousingTransition.this, MovingHouse.class);
                startActivity(intent);
            }
        });

        // Button to bring user to Phonebook page
        phonebook = (ImageButton) findViewById(R.id.phonebook);
        phonebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HousingTransition.this, ListPhoneContactsActivity.class);
                startActivity(intent);
            }
        });

        // Button to bring user to Maps page
        Maps = (ImageButton) findViewById(R.id.Maps);
        Maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HousingTransition.this, Maps.class);
                startActivity(intent);
            }
        });

        // Button to bring user to Photodiary page
        Photodiary = (ImageButton) findViewById(R.id.Photodiary);
        Photodiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HousingTransition.this, Photodiary.class);
                startActivity(intent);
            }
        });

        // Button to bring user back to Home page
        Backbutton = (ImageButton) findViewById(R.id.BackbuttonP);
        Backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HousingTransition.this, SelectTransition.class);
                startActivity(intent);
            }
        });
    }

    // Implementation of Text to Speech
    private void speak()
    {
        String text = mTextView.getText().toString();
        mTTS.setPitch(90/50);
        mTTS.setSpeechRate(70/50);

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    // Implementation of Text to Speech
    @Override
    protected  void onDestroy() {
        if(mTTS != null)
        {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }// END of Text to Speech Implementation
}// END of SelectTransition class