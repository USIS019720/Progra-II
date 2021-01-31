package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void calcular(View view){
        TextView tempVal = (TextView)findViewById(R.id.txtnum1);
        double num1 = Double.parseDouble(tempVal.getText().toString());

        tempVal = (TextView)findViewById(R.id.txtnum2);
        double num2 = Double.parseDouble(tempVal.getText().toString());

        double respuesta = 1;

        Spinner cboOperacionesBasicas = findViewById(R.id.cboOperacionesBasicas);
        switch (cboOperacionesBasicas.getSelectedItemPosition()) {
            case 0://suma
                respuesta = num1 + num2;
                break;

            case 1://Resta
                respuesta = num1 - num2;
                break;

            case 2://Multiplicacion
                respuesta = num1 * num2;
                break;

            case 3://Division
                respuesta = num1 / num2;
                break;

            case 5: //Exponente
                respuesta = Math.pow(num1, num2);
                break;

            case 6: //Raiz
                respuesta = Math.pow(num1, 1/num2);
                break;

            case 7: //Mod
                respuesta = num1 % num2;
                break;

            case 8: //Num mayor
                if (num1 > num2) {
                    respuesta = num1;
                } else  {
                    respuesta = num2;
                }
                break;
        }
        tempVal = findViewById(R.id.lblRespuesta);
        tempVal.setText("Respuesta: "+ respuesta );
    }
}