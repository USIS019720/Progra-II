package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {

    TabHost tbhagua;
    TextView temval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbhagua = findViewById(R.id.tbhagua);
        tbhagua.setup();

        temval = findViewById(R.id.lblRespuesta);

        tbhagua.addTab(tbhagua.newTabSpec( "agua").setContent(R.id.tabagua).setIndicator("",getResources().getDrawable(R.drawable.ic_agua)));
        tbhagua.addTab(tbhagua.newTabSpec( "area").setContent(R.id.tabconvertidor).setIndicator("",getResources().getDrawable(R.drawable.ic_agua)));

        }


    public void calcular(View view){
        TextView tempVal = (TextView)findViewById(R.id.txtnum1);
        double num1 = Double.parseDouble(tempVal.getText().toString());


        double respuesta = 1;
        RadioButton optOperacionesBasicas = findViewById(R.id.optcaso1);
        if( optOperacionesBasicas.isChecked() ) {
            respuesta = num1 = 6 ;
        }

        optOperacionesBasicas = findViewById(R.id.optcaso2);
        if( optOperacionesBasicas.isChecked() ) {
            respuesta = (num1-18) * 0.45 + 6;
        }

        optOperacionesBasicas = findViewById(R.id.optcaso3);
        if( optOperacionesBasicas.isChecked() ) {
            respuesta = (num1-28)*0.65+(28-18)*0.45+6;
        }

        tempVal = findViewById(R.id.lblRespuesta);
        tempVal.setText("Respuesta: "+ respuesta );
}
}

