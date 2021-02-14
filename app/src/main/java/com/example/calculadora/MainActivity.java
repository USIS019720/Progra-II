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

        tbhconversores.addTab(tbhconversores.newTabSpec( "longitud").setContent(R.id.tablongitud).setIndicator("L"));
        tbhconversores.addTab(tbhconversores.newTabSpec( "masa").setContent(R.id.tabmasa).setIndicator("P"));
        tbhconversores.addTab(tbhconversores.newTabSpec( "monedas").setContent(R.id.tabmonedas).setIndicator("M"));

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
    }
}

class conversores{
    Double [][] conversor = {
        {1.00, 100.00,39.37,1000000000.00,0.000539957,1000.00,3.28,1.09,0.001,0.00062}, //Longitud
        {1.00,2267.962,453592.4,45359.24,4535.924,453.5924,45.35924,4.53,0.453592,16.00}, //Masa
        {1.00,34.45,6.45,1.27,3.64,607.65,0.000021,6.13,7.74,8.75},//Monedas
        {}
    };

    public double convertir(int opcion, int de, int a, double cantidad){
        return conversor[opcion][a] / conversor[opcion][de] * cantidad;
          }

    }