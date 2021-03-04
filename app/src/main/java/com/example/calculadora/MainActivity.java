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
    TabHost tbhconversores;
    Button btnconvertir;
    TextView temval;
    Spinner spnopcionDe, spnopcionA;
    conversores miconversor = new conversores();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbhconversores = findViewById(R.id.tbhConversores);
        tbhconversores.setup();

        temval = findViewById(R.id.lblRespuesta);

        tbhconversores.addTab(tbhconversores.newTabSpec( "longitud").setContent(R.id.tablongitud).setIndicator("",getResources().getDrawable(R.drawable.ic_longitud)));
        tbhconversores.addTab(tbhconversores.newTabSpec( "masa").setContent(R.id.tabmasa).setIndicator("",getResources().getDrawable(R.drawable.ic_masa)));
        tbhconversores.addTab(tbhconversores.newTabSpec( "monedas").setContent(R.id.tabmonedas).setIndicator("",getResources().getDrawable(R.drawable.ic_monedas)));
        tbhconversores.addTab(tbhconversores.newTabSpec( "Almacenamiento").setContent(R.id.tabAlmacenamiento).setIndicator("",getResources().getDrawable(R.drawable.ic_almacenamiento)));
        tbhconversores.addTab(tbhconversores.newTabSpec( "Tiempo").setContent(R.id.tabTiempo).setIndicator("",getResources().getDrawable(R.drawable.ic_tiempo)));
        tbhconversores.addTab(tbhconversores.newTabSpec( "Temperatura").setContent(R.id.tabTemperatura).setIndicator("",getResources().getDrawable(R.drawable.ic_temperatura)));
        tbhconversores.addTab(tbhconversores.newTabSpec( "volumen").setContent(R.id.tabvolumen).setIndicator("",getResources().getDrawable(R.drawable.ic_volumen)));
        tbhconversores.addTab(tbhconversores.newTabSpec( "area").setContent(R.id.tabarea).setIndicator("",getResources().getDrawable(R.drawable.ic_area)));

        btnconvertir = findViewById(R.id.btnCalcular);
        btnconvertir.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                try {
                    temval = (TextView)findViewById(R.id.txtCantidad);
                    double cantidad = Double.parseDouble(temval.getText().toString());
                    spnopcionDe = findViewById(R.id.cboDe);
                    spnopcionA =  findViewById(R.id.cboA);
                    temval = findViewById(R.id.lblRespuesta);
                    temval.setText("Respuesta: " + miconversor.convertir(0, spnopcionDe.getSelectedItemPosition(), spnopcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    temval = findViewById(R.id.lblRespuesta);
                    temval.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            });



         btnconvertir = findViewById(R.id.btnCalcularp);
         btnconvertir.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                temval = findViewById(R.id.txtCantidadp);
                double cantidad = Double.parseDouble(temval.getText().toString());

                spnopcionDe = findViewById(R.id.cboDep);
                spnopcionA = findViewById(R.id.cboAp);
                temval = findViewById(R.id.lblRespuestap);

                temval.setText("Respuesta: " + miconversor.convertir(1, spnopcionDe.getSelectedItemPosition(), spnopcionA.getSelectedItemPosition(), cantidad));
            }catch (Exception e){
                temval = findViewById(R.id.lblRespuestap);
                temval.setText("Por favor ingrese los valores correspondiente");
                Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    });


        btnconvertir = findViewById(R.id.btnCalcularm);
        btnconvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    temval = findViewById(R.id.txtCantidadm);
                    double cantidad = Double.parseDouble(temval.getText().toString());

                    spnopcionDe = findViewById(R.id.cboDem);
                    spnopcionA = findViewById(R.id.cboAm);
                    temval = findViewById(R.id.lblRespuestam);

                    temval.setText("Respuesta: " + miconversor.convertir(2, spnopcionDe.getSelectedItemPosition(), spnopcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    temval = findViewById(R.id.lblRespuestam);
                    temval.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnconvertir = findViewById(R.id.btnCalcularq);
        btnconvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    temval = findViewById(R.id.txtCantidadq);
                    double cantidad = Double.parseDouble(temval.getText().toString());

                    spnopcionDe = findViewById(R.id.cboDeq);
                    spnopcionA = findViewById(R.id.cboAq);
                    temval = findViewById(R.id.lblRespuestaq);

                    temval.setText("Respuesta: " + miconversor.convertir(3, spnopcionDe.getSelectedItemPosition(), spnopcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    temval = findViewById(R.id.lblRespuestaq);
                    temval.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnconvertir = findViewById(R.id.btnCalculart);
        btnconvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    temval = findViewById(R.id.txtCantidadt);
                    double cantidad = Double.parseDouble(temval.getText().toString());

                    spnopcionDe = findViewById(R.id.cboDet);
                    spnopcionA = findViewById(R.id.cboAt);
                    temval = findViewById(R.id.lblRespuestat);

                    temval.setText("Respuesta: " + miconversor.convertir(4, spnopcionDe.getSelectedItemPosition(), spnopcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    temval = findViewById(R.id.lblRespuestat);
                    temval.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnconvertir = findViewById(R.id.btnCalcularJ);
        btnconvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    temval = findViewById(R.id.txtCantidadJ);
                    double cantidad = Double.parseDouble(temval.getText().toString());

                    spnopcionDe = findViewById(R.id.cboDeJ);
                    spnopcionA = findViewById(R.id.cboAJ);
                    temval = findViewById(R.id.lblRespuestaJ);

                    temval.setText("Respuesta: " + miconversor.convertir(5, spnopcionDe.getSelectedItemPosition(), spnopcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    temval = findViewById(R.id.lblRespuestaJ);
                    temval.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnconvertir = findViewById(R.id.btnCalcularV);
        btnconvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    temval = findViewById(R.id.txtCantidadV);
                    double cantidad = Double.parseDouble(temval.getText().toString());

                    spnopcionDe = findViewById(R.id.cboDeV);
                    spnopcionA = findViewById(R.id.cboAV);
                    temval = findViewById(R.id.lblRespuestaV);

                    temval.setText("Respuesta: " + miconversor.convertir(6, spnopcionDe.getSelectedItemPosition(), spnopcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    temval = findViewById(R.id.lblRespuestaV);
                    temval.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnconvertir = findViewById(R.id.btnCalcularH);
        btnconvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    temval = findViewById(R.id.txtCantidadH);
                    double cantidad = Double.parseDouble(temval.getText().toString());

                    spnopcionDe = findViewById(R.id.cboDeH);
                    spnopcionA = findViewById(R.id.cboAH);
                    temval = findViewById(R.id.lblRespuestaH);

                    temval.setText("Respuesta: " + miconversor.convertir(7, spnopcionDe.getSelectedItemPosition(), spnopcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    temval = findViewById(R.id.lblRespuestaH);
                    temval.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

class conversores{
    Double [][] conversor = {
        {1.00, 100.00,39.37,1000000000.00,0.000539957,1000.00,3.28,1.09,0.001,0.00062}, //Longitud
        {1.00,2267.962,453592.4,45359.24,4535.924,453.5924,45.35924,4.53,0.453592,16.00}, //Masa
        {1.00,34.45,6.45,1.27,3.64,607.65,0.000021,6.13,7.74,8.75},//Monedas
        {1.00,0.125,0.001,0.00125,0.555551,0.000000125,0.000000001,0.000000000125,0.000000000001,0.000000000000125},//Almacenamiento
        {1.00,86400.00,1440.00,24.00,0.142857,0.0328767,0.00273973,0.000273973,0.000027379,86400000.00},//Tiempo
        {1.00,32.00,273.15},//Temperatura
        {1.00,1000.00,1000.00,0.001,202.88,67.628,4.22,1.056,0.26,61.023},//volumen
        {1.00,0.0001,0.00000001,0.0000000001,0.155,0.00107,0.000119,0.0000000247,0.0000000000386,100.00},//area
    };

    public double convertir(int opcion, int de, int a, double cantidad){
        return conversor[opcion][a] / conversor[opcion][de] * cantidad;
          }

    }