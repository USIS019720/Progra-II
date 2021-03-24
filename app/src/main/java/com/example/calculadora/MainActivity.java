package com.example.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnAgregarproductos);
        btn.setOnClickListener(v -> {
            AgregarProductos();
        });
    }
    private void AgregarProductos(){
        Intent AgregarProductos = new Intent(getApplicationContext(), AgregarProductos.class);
        startActivity(AgregarProductos);
    }
}