package com.retrofittestforstudying;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.retrofittestforstudying.calculation.Calculation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtn(View view) {
        TextView tw = findViewById(R.id.textView);
        try {
            String screen = tw.getText().toString();
            Button btn = findViewById(view.getId());
            if (btn.getText().toString().equals("C")) {
                tw.setText("0");
                return;
            }
            if (btn.getText().toString().equals("=") && tw.getText().toString().equals("Div On Null")) {
                tw.setText("0");
                return;
            }
            if (tw.getText().toString().equals("0") || tw.getText().toString().equals("-")) {
                switch (btn.getText().toString()) {
                    case "/":
                    case "+":
                    case "*":
                        tw.setText("0");
                        return;
                }
            }

            if (tw.getText().toString().equals("0") || tw.getText().toString().equals("Div On Null")) {
                tw.setText(btn.getText().toString());
                return;
            }
            if (btn.getText().toString().equals("=")) {
                switch (tw.getText().toString().charAt(tw.getText().toString().length() - 1)) {
                    case '.':
                    case '-':
                    case '/':
                    case '+':
                    case '*':
                        tw.setText(tw.getText().toString().substring(0, tw.getText().toString().length() - 1));
                        break;
                }

                if (Calculation.IsDivOnNullResult(tw.getText().toString())) {
                    String message = "Div On Null";
                    tw.setText(message);
                    return;
                }

                String number = Calculation.Calculate(tw.getText().toString());
                tw.setText(number);
                return;
            }

            if (btn.getText().toString().equals(".")) {
                if (!Calculation.IsPointExist(screen)) {
                    screen += btn.getText().toString();
                    tw.setText(screen);
                }
                return;
            }

            if (!Character.isDigit(screen.charAt(screen.length() - 1)) && !Character.isDigit(btn.getText().toString().charAt(0))) {
                screen = screen.substring(0, screen.length() - 1) + btn.getText().toString();
            } else {
                screen += btn.getText().toString();
            }

            tw.setText(screen);

        } catch (Exception ignore) {
            tw.setText("0");
        }
    }
}