package com.example.solarcalculator;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        EditText o1 = findViewById(R.id.o1);
        EditText o2 = findViewById(R.id.o2);
        EditText o3 = findViewById(R.id.o3);
        EditText o4 = findViewById(R.id.o4);


        Bundle bundle = getIntent().getExtras();
        String np = String.valueOf(bundle.getString("No. of panels"));
        String is = String.valueOf(bundle.getString("Inverter Size"));
        String battery = String.valueOf(bundle.getString("Battery"));
        String cost = String.valueOf(bundle.getString("Cost"));
        o1.setText(np);
        o2.setText(is);
        o3.setText(battery);
        o4.setText(cost);
    }
}
