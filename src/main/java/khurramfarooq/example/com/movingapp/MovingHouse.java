package khurramfarooq.example.com.movingapp;

import android.content.Intent;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Locale;

public class MovingHouse extends AppCompatActivity {

    // Text to Speech variables
    private TextToSpeech mTTS;
    private TextView mTextView;
    private Button mButtonSpeak;

    // Image buttons to start new activites/tasks
    ImageButton Backbutton;
    ImageButton checklistbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moving_house);

        // Implementation of Text to Speech
        mButtonSpeak = findViewById(R.id.sayit3);

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

        mTextView = findViewById(R.id.tv3);
        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

        // Buttons to bring you to new activities (intents) / start new actions
        Backbutton = (ImageButton) findViewById(R.id.BackbuttonP);
        Backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovingHouse.this, HousingTransition.class);
                startActivity(intent);
            }
        });

        checklistbutton = (ImageButton) findViewById(R.id.checklistbutton);
        checklistbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovingHouse.this, HousingChecklist.class);
                startActivity(intent);
            }
        });

    }

    // A web button to open the housing website
    public void open(View view){
       Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.myhome.ie/"));
       startActivity(browserIntent);
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