package khurramfarooq.example.com.movingappscreen2;

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

    ImageButton phonebook;
    ImageButton imageButton_MovingHouse;
    ImageButton Maps;
    ImageButton Photodiary;
    ImageButton Backbutton;

    private TextToSpeech mTTS;
    private TextView mTextView;
    private Button mButtonSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_transition);

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


        phonebook = (ImageButton) findViewById(R.id.phonebook);
        phonebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HousingTransition.this, PhonebookP.class);
                startActivity(intent);
            }
        });


        Maps = (ImageButton) findViewById(R.id.Maps);
        Maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HousingTransition.this, Maps.class);
                startActivity(intent);
            }
        });

        Photodiary = (ImageButton) findViewById(R.id.Photodiary);
        Photodiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HousingTransition.this, Photodiary.class);
                startActivity(intent);
            }
        });

        Backbutton = (ImageButton) findViewById(R.id.BackbuttonP);
        Backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HousingTransition.this, SelectTransition.class);
                startActivity(intent);
            }
        });
    }

    private void speak()
    {
        String text = mTextView.getText().toString();
        mTTS.setPitch(90/50);
        mTTS.setSpeechRate(70/50);

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected  void onDestroy() {
        if(mTTS != null)
        {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }
}