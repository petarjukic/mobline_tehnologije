package com.example.vjezba2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstNum;
    EditText operator;
    EditText secondNum;
    Button btn;

    TextView rezultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float res;
                res = getNumbers();
                sendResult(res);
            }
        });
    }

    public float getNumbers() {
        firstNum = (EditText) findViewById(R.id.textView);
        operator = (EditText) findViewById(R.id.textView2);
        secondNum = (EditText) findViewById(R.id.textView3);


        String firstNumber = firstNum.getText().toString();
        float fNum = new Float(firstNumber).floatValue();

        String secondNumber = secondNum.getText().toString();
        float sNum = new Float(secondNumber).floatValue();

        if(sNum == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "GRESKA", Toast.LENGTH_LONG);
            toast.show();
        }
        float result = 0;
        switch(operator.getText().toString()) {
            case "+":
                result = fNum + sNum;
                break;
            case "-":
                result = fNum - sNum;
                break;
            case "*":
                result = fNum * sNum;
                break;
            case "/":
                result = fNum / sNum;
                break;
            default:
                Toast toast = Toast.makeText(getApplicationContext(), "GRESKA", Toast.LENGTH_LONG);
                toast.show();
                break;
        }
        return result;
    }

    public void sendResult(float res) {
        rezultat = (TextView) findViewById(R.id.textView4);
        String mytext = Float.toString(res);
        rezultat.setText(mytext);
    }
}