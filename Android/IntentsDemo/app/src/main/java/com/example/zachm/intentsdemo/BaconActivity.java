package com.example.zachm.intentsdemo;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BaconActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacon);

        Bundle data = getIntent().getExtras();
        if (data == null) {
            return;
        }
        String message = data.getString("passedText");
        final TextView baconText = (TextView) findViewById(R.id.baconText);
        baconText.setText(message);
    }

    public void change(View view) {
        Intent i = new Intent(this, FirstActivity.class);
        startActivity(i);
    }
}
