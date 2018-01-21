package com.example.zachm.intentsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void onClick(View view) {
        Intent i = new Intent(this, BaconActivity.class);

        final EditText firstInput = (EditText) findViewById(R.id.firstInput);
        String text = firstInput.getText().toString();
        i.putExtra("passedText", text);

        startActivity(i);
    }
}
