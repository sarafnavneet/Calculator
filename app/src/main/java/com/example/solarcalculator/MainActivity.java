package com.example.solarcalculator;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.graphics.Color.RED;

public class MainActivity extends AppCompatActivity {

    private EditText a1;
    private EditText a2;
    private EditText a3;
    private EditText a4;
    private EditText a5;
    private EditText a6;

    private EditText b1;
    private EditText b2;
    private EditText b3;
    private EditText b4;
    private EditText b5;
    private EditText b6;

    private CheckBox c1;
    private CheckBox c2;
    private CheckBox c3;
    private CheckBox c4;
    private CheckBox c5;
    private CheckBox c6;


    private List<EditText> usageList;
    private List<CheckBox> checkBoxes;
    private List<EditText> quantity;
    private ImageButton imageButton;
    private ImageButton imageButton2;
    private ImageButton imageButton3;

    public static String lang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLoacale();
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));


        imageButton = findViewById(R.id.button);
        if(lang.equals("hi"))
            imageButton.setBackgroundResource(R.drawable.sung);
        else if(lang.equals("gu")){
            imageButton.setBackgroundResource(R.drawable.sunguj);
        }

        usageList = new ArrayList<>();
        usageList.add( a1 = findViewById(R.id.a1));
        usageList.add( a2 = findViewById(R.id.a2));
        usageList.add( a3 = findViewById(R.id.a3));
        usageList.add( a4 = findViewById(R.id.a4));
        usageList.add( a5 = findViewById(R.id.a5));
        usageList.add( a6 = findViewById(R.id.a6));


        checkBoxes = new ArrayList<>();
        checkBoxes.add( c1 = findViewById(R.id.c1));
        checkBoxes.add( c2 = findViewById(R.id.c2));
        checkBoxes.add( c3 = findViewById(R.id.c3));
        checkBoxes.add( c4 = findViewById(R.id.c4));
        checkBoxes.add( c5 = findViewById(R.id.c5));
        checkBoxes.add( c6 = findViewById(R.id.c6));


        quantity =  new ArrayList<>();
        quantity.add(b1 = findViewById(R.id.b1));
        quantity.add(b2 = findViewById(R.id.b2));
        quantity.add(b3 = findViewById(R.id.b3));
        quantity.add(b4 = findViewById(R.id.b4));
        quantity.add(b5 = findViewById(R.id.b5));
        quantity.add(b6 = findViewById(R.id.b6));

        for(int i = 0; i<checkBoxes.size(); i++){
            int finalI = i;
            checkBoxes.get(i).setOnClickListener(v->usageList.get(finalI).setText(usageList.get(finalI).getHint()));
        }

//        for(int i = 0; i<quantity.size(); i++){
//            quantity.get(i).setOnFocusChangeListener(View::onHoverChanged);
//        }



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View view){
        if(empty()){
            Toast.makeText(this, getResources().getString(R.string.Enter_all_the_inputs), Toast.LENGTH_SHORT).show();
        }
        else {
            double va1 = Double.parseDouble(a1.getText().toString());
            double va2 = Double.parseDouble(a2.getText().toString());
            double va3 = Double.parseDouble(a3.getText().toString());
            double va4 = Double.parseDouble(a4.getText().toString());
            double va5 = Double.parseDouble(a5.getText().toString());
            double va6 = Double.parseDouble(a6.getText().toString());

            double vb1 = Double.parseDouble(b1.getText().toString());
            double vb2 = Double.parseDouble(b2.getText().toString());
            double vb3 = Double.parseDouble(b3.getText().toString());
            double vb4 = Double.parseDouble(b4.getText().toString());
            double vb5 = Double.parseDouble(b5.getText().toString());
            double vb6 = Double.parseDouble(b6.getText().toString());

            int nPanels = (int) Math.ceil (1.3 * ((10 * va1 * vb1) + (36 * va2 * vb2) + (70 * va3 * vb3) + (62 * va4 * vb4) + (125 * va5 * vb5) + (45.6 * va6 * vb6)) / (4.32 * 150));

            double inverterSize = 1.3 * ((10 * vb1) + (36 * vb2) + (70 * vb3) + (62 * vb4) + (125 * vb5) + (45.6 * vb6));

            double battery = (3 * ((10 * va1 * vb1) + (36 * va2 * vb2) + (70 * va3 * vb3) + (62 * va4 * vb4) + (125 * va5 * vb5) + (45.6 * va6 * vb6))) / (12 * 0.85 * 0.6);

            double cost = 80 * 1.2 * (10 * vb1 + 36 * vb2 + 70 * vb3 + 62 * vb4 + 125 * vb5 + 45.6 * vb6);

            double dailyConsumption = (10 * va1 * vb1) + (36 * va2 * vb2) + (70 * va3 * vb3) + (62 * va4 * vb4) + (125 * va5 * vb5) + (45.6 * va6 * vb6);

            DecimalFormat df=new DecimalFormat("#.##");

            Intent nextActivity = new Intent(getApplicationContext(), result.class);
            nextActivity.putExtra("No. of panels", nPanels + "");
            nextActivity.putExtra("Inverter Size", df.format(inverterSize) + "");
            nextActivity.putExtra("Battery", df.format(battery) + "");
            nextActivity.putExtra("Cost", df.format(cost) + "");
            nextActivity.putExtra("Daily Consumption", df.format(dailyConsumption)+"");

            startActivity(nextActivity);

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean empty(){
        for(EditText e: usageList){
            if(e.getText().toString().length()==0) {
                e.setFocusable(true);
                e.setFocusableInTouchMode(true);
                e.requestFocus();
                e.setSelection(e.getText().length());
                return true;
            }
        }
        for(EditText e: quantity){
            if(e.getText().toString().length()==0){
                e.setFocusable(true);
            e.setFocusableInTouchMode(true);
            e.requestFocus();
            e.setSelection(e.getText().length());

                return true;}

        }

        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset: {
                for (CheckBox c : checkBoxes) {
                    c.setChecked(false);
                }
                for (EditText e : usageList) {
                    e.getText().clear();
                }
                for (EditText e : quantity) {
                    e.getText().clear();
                }
            }
            break;
            case R.id.demo: {
                for (CheckBox c : checkBoxes) {
                    c.setChecked(true);
                }
                for (EditText e : usageList) {
                    e.setText(e.getHint().toString());
                }
                for (EditText e : quantity) {
                    e.setText("1");
                }

            }
            break;
            case R.id.hin: {
                setLocale("hi");
                recreate();


            }
            break;
            case R.id.eng: {

                setLocale("");
                recreate();
            }
            break;
            case R.id.guj:{
                setLocale("gu");
                recreate();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void setLocale(String lang){
        this.lang = lang;
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config  = new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());


        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My lang",lang);
        editor.apply();
    }


    public void loadLoacale(){
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My lang","");
        setLocale(language);
    }
}