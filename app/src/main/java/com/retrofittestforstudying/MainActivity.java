package com.retrofittestforstudying;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtn(View view) {
        TextView tw = findViewById(R.id.textView);

        try {
            Button btn = findViewById(view.getId());
            if (tw.getText().toString().equals("0")) {
                tw.setText(btn.getText().toString());
            }

        } catch (Exception ignored) {
        }
    }
}