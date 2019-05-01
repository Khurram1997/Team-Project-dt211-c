package khurramfarooq.example.com.movingappscreen2;

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
    private TextToSpeech mTTS;
    private TextView mTextView;
    private Button mButtonSpeak;

    ImageButton Backbutton;
    ImageButton checklistbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moving_house);
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

    public void open(View view){
       Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.myhome.ie/"));
       startActivity(browserIntent);
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
