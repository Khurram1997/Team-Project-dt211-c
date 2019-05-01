package khurramfarooq.example.com.movingappscreen2;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Photodiary extends AppCompatActivity {

    ImageButton PDbackbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photodiary);

        PDbackbutton = (ImageButton) findViewById(R.id.PDbackbutton);
        PDbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Photodiary.this, CollegeTransition.class);
                startActivity(intent);
            }
        });
    }
}
