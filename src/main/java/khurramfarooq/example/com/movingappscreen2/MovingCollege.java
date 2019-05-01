package khurramfarooq.example.com.movingappscreen2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import java.util.Locale;
import android.util.Log;
import android.widget.TextView;

public class MovingCollege extends AppCompatActivity {

    ImageButton Backbutton;
    ImageButton imageButton_tCollege;
    ImageButton checklistbutton2;

    private TextToSpeech mTTS;
    private TextView mTextView;
    private Button mButtonSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moving_college);

        mButtonSpeak = findViewById(R.id.sayit7);

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

        mTextView = findViewById(R.id.tv7);
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
                Intent intent = new Intent(MovingCollege.this, CollegeTransition.class);
                startActivity(intent);
            }
        });

        checklistbutton2 = (ImageButton) findViewById(R.id.checklistbutton2);
        checklistbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovingCollege.this, CollegeChecklist.class);
                startActivity(intent);
            }
        });

        imageButton_tCollege = (ImageButton) findViewById(R.id.imageButton_tCollege);
        imageButton_tCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovingCollege.this, CollegeInfo.class);
                startActivity(intent);
            }
        });
    }

    public void open(View view){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tcd.ie/"));
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
