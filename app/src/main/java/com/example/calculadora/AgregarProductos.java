package com.example.calculadora;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgregarProductos extends AppCompatActivity {
    FloatingActionButton btnAtras;
    ImageView imgFotoProducto;
    Intent tomarFotoIntent;
    String urlCompletaimg,idproducto,accion="nuevo";
    Button btn;
    DB miBD;
    TextView tempVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productos);

        miBD = new DB(getApplicationContext(),"",null, 2);
        btnAtras = findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(v->{
            mostrarDatosProductos();
        });
        imgFotoProducto = findViewById(R.id.imgFotoProducto);
        imgFotoProducto.setOnClickListener(v->{
            tomarFotoProducto();
        });
        btn = findViewById(R.id.btnGuardarProducto);
        btn.setOnClickListener(v ->{

            tempVal = findViewById(R.id.txtCodigo);
            String codigo = tempVal.getText().toString();

            tempVal = findViewById(R.id.txtNombre);
            String nombre = tempVal.getText().toString();

            tempVal = findViewById(R.id.txtMarca);
            String marca = tempVal.getText().toString();

            tempVal = findViewById(R.id.txtPresentacion);
            String presentacion = tempVal.getText().toString();

            tempVal = findViewById(R.id.txtPrecio);
            String precio = tempVal.getText().toString();

            String[] datos= {idproducto,codigo,nombre,marca,presentacion,precio,urlCompletaimg};
            miBD.administracion_productos(accion,datos);
            mostrarVistaPrincipal();
            mostrarMsgToast("registro guardado con exito.");
    });
        mostrarDatosProductos();
    }
    private void mostrarDatosProductos() {
        try{
            Bundle recibirParametros = getIntent().getExtras();
            accion = recibirParametros.getString("accion");
            if(accion.equals("modificar")){
                String[] datos = recibirParametros.getStringArray("datos");
                idproducto = datos[0];

                tempVal = findViewById(R.id.txtCodigo);
                tempVal.setText(datos[1]);

                tempVal = findViewById(R.id.txtNombre);
                tempVal.setText(datos[2]);

                tempVal = findViewById(R.id.txtMarca);
                tempVal.setText(datos[3]);

                tempVal = findViewById(R.id.txtPresentacion);
                tempVal.setText(datos[4]);

                tempVal = findViewById(R.id.txtPrecio);
                tempVal.setText(datos[5]);

                urlCompletaimg = datos[6];
                Bitmap bitmap = BitmapFactory.decodeFile((urlCompletaimg));
                imgFotoProducto.setImageBitmap(bitmap);
            }
        }catch (Exception e){
            mostrarMsgToast(e.getMessage());
        }
    }
        private void mostrarVistaPrincipal(){
            Intent iprincipal = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(iprincipal);
    }
    private void tomarFotoProducto(){
        tomarFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (tomarFotoIntent.resolveActivity(getPackageManager())!=null){
            File photoProducto = null;
            try {
                photoProducto = crearImagenProducto();
            }catch (Exception e){
                mostrarMsgToast(e.getMessage());
            }
            if (photoProducto!=null ){
                try{
                    Uri uriPhotoProducto = FileProvider.getUriForFile(AgregarProductos.this,"com.example.calculadora.fileprovider",photoProducto);
                    tomarFotoIntent.putExtra(MediaStore.EXTRA_OUTPUT,uriPhotoProducto);
                    startActivityForResult(tomarFotoIntent,1);
                }catch (Exception e){
                    mostrarMsgToast(e.getMessage());
                }
            } else {
                mostrarMsgToast("No fue posible tomar la foto");
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if(requestCode==1 && resultCode==RESULT_OK){
                Bitmap imagenBitmap = BitmapFactory.decodeFile(urlCompletaimg);
                imgFotoProducto.setImageBitmap(imagenBitmap);
            }
        }catch(Exception e){
        mostrarMsgToast(e.getMessage());
    }
    }
    private File crearImagenProducto() throws Exception {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreProducto = "imagen"+ timeStamp +"_";
        File diralmacenamiento = getExternalFilesDir(Environment.DIRECTORY_DCIM);
        if (diralmacenamiento.exists()==false){
            diralmacenamiento.mkdirs();
        }
        File image = File.createTempFile(nombreProducto, ".jpg",diralmacenamiento);
        urlCompletaimg = image.getAbsolutePath();
        return image;
    }
    private void mostrarMsgToast(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
    }
}