package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabHost tbhconversores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbhconversores = findViewById(R.id.tbhConversores);
        tbhconversores.setup();

        tbhconversores.addTab(tbhconversores.newTabSpec( "longitud").setContent(R.id.tablongitud).setIndicator("L"));
        tbhconversores.addTab(tbhconversores.newTabSpec( "masa").setContent(R.id.tabmasa).setIndicator("P"));
        tbhconversores.addTab(tbhconversores.newTabSpec( "monedas").setContent(R.id.tabmonedas).setIndicator("M"));
    }

}