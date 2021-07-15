package org.jamesdev.apitest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import org.jamesdev.apitest.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = (TextView) findViewById(R.id.textview_result);

        Intent i = getIntent();

        result.setText(i.getStringExtra("Result"));

    }
}