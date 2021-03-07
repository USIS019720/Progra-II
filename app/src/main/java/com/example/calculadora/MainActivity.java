package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabHost tbhagua;
    TextView temval;
    Button btnconvertir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbhagua = findViewById(R.id.tbhagua);
        tbhagua.setup();

        temval = findViewById(R.id.lblRespuesta);

        tbhagua.addTab(tbhagua.newTabSpec( "agua").setContent(R.id.tabagua).setIndicator("",getResources().getDrawable(R.drawable.ic_agua)));


    }

    }