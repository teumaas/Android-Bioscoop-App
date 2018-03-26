package nl.avans.informatica.bioscoopapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    private Button button;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        tv1 = (TextView) findViewById(R.id.contactHeader);
        tv2 = (TextView) findViewById(R.id.contactDescription);
        tv3 = (TextView) findViewById(R.id.contactDetailsHeader);
        tv4 = (TextView) findViewById(R.id.contactDetails);

        tv1.setText(R.string.contact_header);
        tv2.setText(R.string.contact_description);
        tv3.setText(R.string.contact_details_header);
        tv4.setText(R.string.contact_details);


        button = (Button) findViewById(R.id.navigationButton);
        button.setText(R.string.navigation_button_name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=Lovensdijkstraat 61+Breda");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

    }
}
