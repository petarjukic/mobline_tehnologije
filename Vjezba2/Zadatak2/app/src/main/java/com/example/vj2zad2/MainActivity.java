package com.example.vj2zad2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstNum;
    EditText secondNum;
    Spinner spinner;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.operators, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float res;
                String selected = spinner.getSelectedItem().toString();

                res = getNumbers(selected);
                sendResult(res);
            }
        });
    }

    public float getNumbers(String operator) {
        float result = 0;
        firstNum = (EditText) findViewById(R.id.textView6);
        secondNum = (EditText) findViewById(R.id.textView7);

        String firstNumber = firstNum.getText().toString();
        float fNum = new Float(firstNumber).floatValue();
        String secondNumber = secondNum.getText().toString();
        float sNum = new Float(secondNumber).floatValue();

        if(sNum == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "GRESKA", Toast.LENGTH_LONG);
            toast.show();
        }

        switch(operator) {
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
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("rezultat", String.valueOf(res));
        startActivity(intent);
    }
}