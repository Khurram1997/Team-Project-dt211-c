package khurramfarooq.example.com.movingapp;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.TextView;
import java.util.Locale;

public class CollegeTransition extends AppCompatActivity {

    // Image buttons to start new activites/tasks
    ImageButton imageButton_MovingCollege;
    ImageButton Maps;
    ImageButton Photodiary;
    ImageButton Phonebook;
    ImageButton Backbutton;

    // Text to Speech variables
    private TextToSpeech mTTS;
    private TextView mTextView;
    private Button mButtonSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_transition);

        // Implementation of Text to Speech
        mButtonSpeak = findViewById(R.id.sayit4);

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

        mTextView = findViewById(R.id.tv4);
        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

        // Buttons to bring you to new activities (intents) / start new actions
        imageButton_MovingCollege = (ImageButton) findViewById(R.id.imageButton_MovingCollege);
        imageButton_MovingCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollegeTransition.this, MovingCollege.class);
                startActivity(intent);
            }
        });

        Maps = (ImageButton) findViewById(R.id.Maps);
        Maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollegeTransition.this, Maps.class);
                startActivity(intent);
            }
        });

        Photodiary = (ImageButton) findViewById(R.id.Photodiary);
        Photodiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollegeTransition.this, Photodiary.class);
                startActivity(intent);
            }
        });

        Phonebook = (ImageButton) findViewById(R.id.Phonebook);
        Phonebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollegeTransition.this, ListPhoneContactsActivity.class);
                startActivity(intent);
            }
        });

        Backbutton = (ImageButton) findViewById(R.id.BackbuttonP);
        Backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollegeTransition.this, SelectTransition.class);
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