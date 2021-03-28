package com.example.calculadora;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btn;
    DB miBD;
    ListView ltsProductos;
    Cursor datosProductosCursor = null;
    ArrayList<productos> productosArrayList=new ArrayList<productos>();
    ArrayList<productos> productosArrayListCopy=new ArrayList<productos>();
    productos misProductos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnAgregarproductos);
        btn.setOnClickListener(v -> {
            AgregarProductos("nuevo", new String[]{});
        });
        mostrarDatosProductos();
    }
    private void AgregarProductos(String nuevo, String[] strings){
        Intent AgregarProductos = new Intent(getApplicationContext(), AgregarProductos.class);
        startActivity(AgregarProductos);
    }
    private void obtenerDatosProductos() {
        miBD = new DB(getApplicationContext(),"",null, 1);
        datosProductosCursor = miBD.administracion_productos("consultar",null);
        if (datosProductosCursor.moveToFirst()){
            mostrarDatosProductos();

        }else {
            mostrarMsgToast("no hay datos de productos que mostrar, porfavor agrege productos");
            AgregarProductos("nuevo", new String[]{});
        }
    }

    private void eliminarProducto(){
        try {
            AlertDialog.Builder confirmacion = new AlertDialog.Builder(MainActivity.this);
            confirmacion.setTitle("Esta seguro de eliminar el registro?");
            confirmacion.setMessage(datosProductosCursor.getString(1));
            confirmacion.setPositiveButton("Si", (dialog, which) -> {
                miBD = new DB(getApplicationContext(), "", null, 1);
                datosProductosCursor = miBD.administracion_productos("eliminar", new String[]{datosProductosCursor.getString(0)});//idAmigo
                obtenerDatosProductos();
                mostrarMsgToast("Registro Eliminado con exito...");
                dialog.dismiss();//cerrar el cuadro de dialogo
            });
            confirmacion.setNegativeButton("No", (dialog, which) -> {
                mostrarMsgToast("Eliminacion cancelada por el usuario...");
                dialog.dismiss();
            });
            confirmacion.create().show();
        }catch (Exception ex){
            mostrarMsgToast(ex.getMessage());
        }
    }




    private void mostrarDatosProductos (){
        ltsProductos = findViewById(R.id.ltsproducto);
        productosArrayList.clear();
        do{
            misProductos = new productos(
                    datosProductosCursor.getString(0),//idprducto
                    datosProductosCursor.getString(1),//codigo
                    datosProductosCursor.getString(2),//nombre
                    datosProductosCursor.getString(3),//marca
                    datosProductosCursor.getString(4),//presentacion
                    datosProductosCursor.getString(5),//precio
                    datosProductosCursor.getString(6) //urlPhoto
            );
            productosArrayList.add(misProductos);
            mostrarMsgToast(datosProductosCursor.getString(6));
        }while(datosProductosCursor.moveToNext());
        adaptadorImagenes adaptadorImagenes = new adaptadorImagenes(getApplicationContext(),productosArrayList);
        ltsProductos.setAdapter(adaptadorImagenes);
        registerForContextMenu(ltsProductos);
        productosArrayListCopy.addAll(productosArrayList);

    }

    private void mostrarMsgToast (String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
}
class productos{
    String idProducto;
    String codigo;
    String nombre;
    String marca;
    String presentacion;
    String precio;
    String urlImg;

    public productos(String idProducto, String codigo, String nombre, String marca, String presentacion, String precio, String urlImg) {
        this.idProducto = idProducto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.marca = marca;
        this.presentacion = presentacion;
        this.precio = precio;
        this.urlImg = urlImg;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}