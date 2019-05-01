package khurramfarooq.example.com.movingappscreen2;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import java.util.Locale;
import android.util.Log;
import android.widget.TextView;

public class HousingChecklist extends AppCompatActivity {

    ImageButton Backbutton;
    ImageButton checklistbutton3;

    private TextToSpeech mTTS;
    private TextView mTextView;
    private Button mButtonSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_checklist);

        mButtonSpeak = findViewById(R.id.sayit5);

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

        mTextView = findViewById(R.id.tv5);
        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

        checklistbutton3 = (ImageButton) findViewById(R.id.checklistbutton3);
        checklistbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HousingChecklist.this, ToDoList.class);
                startActivity(intent);
            }
        });

        /*
        Backbutton = (ImageButton) findViewById(R.id.BackbuttonP);
        Backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HousingChecklist.this, MovingHouse.class);
                startActivity(intent);
            }
        });*/

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
