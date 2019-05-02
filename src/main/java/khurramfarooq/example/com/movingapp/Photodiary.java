package khurramfarooq.example.com.movingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class Photodiary extends AppCompatActivity {

    // Text to Speech variables
    private TextToSpeech mTTS;
    private TextView mTextView;
    private Button mButtonSpeak;

    // Image buttons to start new activites/tasks
    ImageButton PDbackbutton;

    // Image Gallery manipulation variables
    ImageView image;
    Button btnGallery;
    final int REQUEST_IMAGE_GALLERY = 2;
    Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photodiary);

        // Implementation of Text to Speech
        mButtonSpeak = findViewById(R.id.sayitpd);

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

        mTextView = findViewById(R.id.tvpd);
        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

        // Declaring imageview and button to add image
        image = (ImageView) findViewById(R.id.img);
        btnGallery = (Button) findViewById(R.id.butt);


        // Back button to go back to previous page
        PDbackbutton = (ImageButton) findViewById(R.id.PDbackbutton);
        PDbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Photodiary.this, CollegeTransition.class);
                startActivity(intent);
            }
        });

        // New onClickListener to open the gallery so the user can make a selection of a photo
        btnGallery.setOnClickListener(new  View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openGallery();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_GALLERY)
        {
            imgUri = data.getData();
            image.setImageURI(imgUri);
        }
    }

    // Creates new intent and opens the Gallery on te phone
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, REQUEST_IMAGE_GALLERY);
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
}// END of Photodiary class