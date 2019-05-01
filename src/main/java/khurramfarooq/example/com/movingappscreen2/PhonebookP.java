package khurramfarooq.example.com.movingappscreen2;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;

import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.ArrayList;
import java.util.Locale;
import android.util.Log;
import android.widget.TextView;



import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;



public class PhonebookP extends AppCompatActivity {

    ImageButton BackbuttonP;
    Button btnAdd_Contact;

    private TextToSpeech mTTS;
    private TextView mTextView;
    private Button mButtonSpeak;

    //Button btnAdd_Contact_onClick;

    private GoogleApiClient client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook_p);

        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        mButtonSpeak = findViewById(R.id.sayit10);

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

        mTextView = findViewById(R.id.tv9);
        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });


        BackbuttonP = (ImageButton) findViewById(R.id.BackbuttonP);
        BackbuttonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhonebookP.this, HousingTransition.class);
                startActivity(intent);
            }
        });

        /*
        btnAdd_Contact_onClick = (Button) findViewById(R.id.btnAdd_Contact_onClick);
        btnAdd_Contact_onClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            }
        });
        */

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



    //Button getBtnAdd_Contact = (Button) findViewById(R.id.btnAdd_Contact);


    //public void btnAdd_Contact_onClick(View view) {
      //  fxAdd_RawContact();
    //}


    public void fxAdd_RawContact(){
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        int rawContact_NewID = ops.size();

        //Add new blank contact
        try {
            ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                    .build());
        }
        catch (Exception e) {
            Log.e("ADD", "could not find account_type null");
            return;
        }


        //get+init values
        String sFirstname = String.valueOf(((EditText)findViewById(R.id.txtFirstname)).getText());
        String sLastname = String.valueOf(((EditText) findViewById(R.id.txtLastname)).getText());
        String sDsiplayname = sLastname + " " + sFirstname;

        String sPhoneNr = String.valueOf(((EditText) findViewById(R.id.txtTelephone)).getText());
        String sEmail = String.valueOf(((EditText) findViewById(R.id.txtEmail)).getText());


        //Set values to new contacts
        //Person
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)

                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContact_NewID) //new rawContactID
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, sDsiplayname)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, sFirstname)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, sLastname)
                .build()
        );

        //Email
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContact_NewID)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.DATA, sEmail)
                .build()
        );

        //Phone
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContact_NewID)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.DATA, sPhoneNr)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
                .build()
        );


        //Check and get the results
        ContentProviderResult[] res = new ContentProviderResult[0];
        try {
            res = getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        }
        catch (RemoteException e){
            Log.e("getContentResolver()", e.getMessage());
        }
        catch (OperationApplicationException e){
            Log.e("getContentResolver()", e.toString());
        }
        catch (Exception e){
            Log.e("getContentResolver()", e.toString());
        }
        finally {

        }


        //Check result
        if (res != null && res[0] != null) {
            Uri newContactUri = res[0] .uri;
            Log.d("AddContact", "URI added contact:" + newContactUri);
        }
        else {
            Log.e("AddContact", "contact could not be added");
        }
    }

    //public void onStart(){
      //  Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        //intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
    //}
}