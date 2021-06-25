package com.example.solarcalculator;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale(MainActivity.lang);
        setContentView(R.layout.result);



        EditText o1 = findViewById(R.id.o1);
        EditText o2 = findViewById(R.id.o2);
        EditText o3 = findViewById(R.id.o3);
        EditText o4 = findViewById(R.id.o4);
        EditText o5 = findViewById(R.id.o5);

        TextView sp1 = findViewById(R.id.sp1);
        TextView sp2 = findViewById(R.id.sp2);
        TextView sp3 = findViewById(R.id.sp3);

        String specifications = getResources().getString(R.string.Specifcation);
        Bundle bundle = getIntent().getExtras();
        String dc = String.valueOf(bundle.getString("Daily Consumption"))+"W" +getResources().getString(R.string.hr_day);

        String np = String.valueOf(bundle.getString("No. of panels")) +getResources().getString(R.string.panels);
        sp1.setText(specifications+":\n150"+ getResources().getString(R.string.watt_solar_panels)+". 14-21.6"+ getResources().getString(R.string.volts));

        String is = String.valueOf(bundle.getString("Inverter Size")) +" "+ getResources().getString(R.string.watts_or)+"\n"
                +getResources().getString(R.string.greater);

        int inverterValue = (int)Double.parseDouble(bundle.getString("Inverter Size"));
        int div = inverterValue/100;
        int rem = inverterValue%100;
        if(rem<=10){
            sp2.setText(specifications+":\n"+ div*100+getResources().getString(R.string.watts)+", 12"+ getResources().getString(R.string.volt_solar_inverters)+".");
        }

        else{
            sp2.setText(specifications+":\n"+ (div+1)*100+getResources().getString(R.string.watts)+" , 12"+ getResources().getString(R.string.volt_solar_inverters)+".");
        }


        String battery = String.valueOf(bundle.getString("Battery")) + " Ah, +" +getResources().getString(R.string.for_3day_autonomy);
        int batteryValue = (int)Double.parseDouble(bundle.getString("Battery"));
        int divB = batteryValue/100;
        int remB = batteryValue%100;
        if(remB<50){
            sp3.setText(specifications+":\n"+ "12V, "+((divB*100)+50)+ " Ah, +"+getResources().getString(R.string.for_3day_autonomy));
        }

        else{
            sp3.setText(specifications+":\n"+ "12V, " +(divB+1)*100+ " Ah, +"+getResources().getString(R.string.for_3day_autonomy));
        }


        String cost = String.valueOf(bundle.getString("Cost")) +" Rs";

        o1.setText(np);
        o2.setText(is);
        o3.setText(battery);
        o4.setText(cost);
        o5.setText(dc);
    }
    private void setLocale(String lang){

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config  = new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());


        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My lang",lang);
        editor.apply();
    }
}
