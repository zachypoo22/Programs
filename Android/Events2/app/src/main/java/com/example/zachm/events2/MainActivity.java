package com.example.zachm.events2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        TextView textView = (TextView) findViewById(R.id.textField);
                        textView.setText("Good job!");
                    }
                }
        );
    }
}
