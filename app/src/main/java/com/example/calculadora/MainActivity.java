package com.example.calculadora;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btn;
    DB miBD;
    ListView ltsProductos;
    Cursor datosProductosCursor = null;
    ArrayList<productos> productosArrayList=new ArrayList<productos>();
    ArrayList<productos> productosArrayListCopy=new ArrayList<productos>();
    productos misProductos;
    JSONArray jsonArrayDatosProductos;
    JSONObject jsonObjectDatosproductos;
    utilidades u;
    detectarinternet di;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            di = new detectarinternet(getApplicationContext());
            btn = findViewById(R.id.btnAgregarproductos);
            btn.setOnClickListener(v -> {
                AgregarProductos("nuevo");
            });
            obtenerDatosProductos();
            buscarproductos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_productos, menu);
        try {
            AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
            position = adapterContextMenuInfo.position;

            menu.setHeaderTitle(jsonArrayDatosProductos.getJSONObject(position).getJSONObject("value").getString("nombre"));
        }catch (Exception e){
            mostrarMsgToast(e.getMessage());
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        try {
            switch (item.getItemId()) {
                case R.id.mnxAgregar:
                    AgregarProductos("nuevo");
                    break;
                case R.id.mnxModificar:
                    AgregarProductos("modificar");
                    break;
                case R.id.mnxEliminar:
                    eliminarProducto();
                    break;
            }
        }catch (Exception ex){
            mostrarMsgToast(ex.getMessage());
        }
        return super.onContextItemSelected(item);
    }
    private void AgregarProductos(String accion){
        try {
            Bundle parametrosProductos = new Bundle();
            parametrosProductos.putString("accion", accion);
           if (jsonArrayDatosProductos.length()>0){
               parametrosProductos.putString("datos", jsonArrayDatosProductos.getJSONObject(position).toString());
           }
            Intent AgregarProductos = new Intent(getApplicationContext(), AgregarProductos.class);
            AgregarProductos.putExtras(parametrosProductos);
            startActivity(AgregarProductos);
        }catch (Exception e){
            mostrarMsgToast(e.getMessage());
        }
    }

    private void obtenerDatosProductosOffLine(){
        try {
            miBD = new DB(getApplicationContext(), "", null, 1);
            datosProductosCursor = miBD.administracion_productos("consultar", null);
            if (datosProductosCursor.moveToFirst()) {//si hay datos que mostrar
                jsonObjectDatosproductos = new JSONObject();
                JSONObject jsonValueObject = new JSONObject();
                jsonArrayDatosProductos = new JSONArray();
                do {
                    jsonObjectDatosproductos.put("_id", datosProductosCursor.getString(0));
                    jsonObjectDatosproductos.put("_rev", datosProductosCursor.getString(0));
                    jsonObjectDatosproductos.put("codigo", datosProductosCursor.getString(1));
                    jsonObjectDatosproductos.put("nombre", datosProductosCursor.getString(2));
                    jsonObjectDatosproductos.put("marca", datosProductosCursor.getString(3));
                    jsonObjectDatosproductos.put("presentacion", datosProductosCursor.getString(4));
                    jsonObjectDatosproductos.put("precio", datosProductosCursor.getString(5));
                    jsonObjectDatosproductos.put("urlPhoto", datosProductosCursor.getString(6));
                    jsonValueObject.put("value", jsonObjectDatosproductos);

                    jsonArrayDatosProductos.put(jsonValueObject);
                } while (datosProductosCursor.moveToNext());
                mostrarDatosProductos();
            } else {//sino que llame para agregar nuevos amigos...
                mostrarMsgToast("No hay datos de amigos que mostrar, por favor agregue nuevos amigos...");
                AgregarProductos("nuevo");
            }
        }catch (Exception e){
            mostrarMsgToast(e.getMessage());
        }
    }

    private void obtenerDatosProdcutosOnLine(){
        try {
            ConexionServer conexionServer = new ConexionServer();
            String resp = conexionServer.execute(u.url_consulta, "GET").get();

            jsonObjectDatosproductos=new JSONObject(resp);
            jsonArrayDatosProductos = jsonObjectDatosproductos.getJSONArray("rows");
            mostrarDatosProductos();
        }catch (Exception ex){
            mostrarMsgToast(ex.getMessage());
        }
    }

    private void buscarproductos() {
        TextView tempVal = findViewById(R.id.txtBuscarProductos);
        tempVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    productosArrayList.clear();
                    if( tempVal.getText().toString().trim().length()<1 ){//si no esta escribiendo, mostramos todos los registros
                        productosArrayList.addAll(productosArrayListCopy);
                    } else {//si esta buscando entonces filtramos los datos
                        for (productos pm : productosArrayList){
                            String nom = pm.getNombre();
                            String mar = pm.getMarca();
                            String pres = pm.getPresentacion();
                            String prec = pm.getPrecio();

                            String buscando = tempVal.getText().toString().trim().toLowerCase();//escribe en la caja de texto...

                            if(nom.toLowerCase().trim().contains(buscando) ||
                                    mar.trim().toLowerCase().contains(buscando) ||
                                    pres.trim().toLowerCase().contains(buscando)||
                                    prec.trim().toLowerCase().contains(buscando)
                            ){
                                productosArrayList.add(pm);
                            }
                        }
                    }
                    adaptadorImagenes adaptadorImagenes = new adaptadorImagenes(getApplicationContext(), productosArrayList);
                    ltsProductos.setAdapter(adaptadorImagenes);
                }catch (Exception e){
                    mostrarMsgToast(e.getMessage());
                }

            }
            @Override
            public void afterTextChanged(Editable s) {
            }
          });
        }

    private void obtenerDatosProductos() {
       //si tengo internet obtener datos productos online, sino, obtener datos productos offline
        if (di.hayConexionInternet()){
            mostrarMsgToast("Hay conexion a internet, mostrando datos de la nube");
            obtenerDatosProdcutosOnLine();
        }else {
            jsonArrayDatosProductos = new JSONArray();
            mostrarMsgToast("No hay conexion a internet, mostrando datos local");
            obtenerDatosProductosOffLine();
        }
    }

    private void eliminarProducto(){
        try {
            jsonObjectDatosproductos = jsonArrayDatosProductos.getJSONObject(position).getJSONObject("value");
            AlertDialog.Builder confirmacion = new AlertDialog.Builder(MainActivity.this);
            confirmacion.setTitle("Esta seguro de eliminar el registro?");
            confirmacion.setMessage(jsonObjectDatosproductos.getString("codigo"));
            confirmacion.setPositiveButton("Si", (dialog, which) -> {
                try {
                    if(di.hayConexionInternet()){
                        ConexionServer objEliminarProducto = new ConexionServer();
                        String resp =  objEliminarProducto.execute(u.url_mto +
                                jsonObjectDatosproductos.getString("_id")+ "?rev="+
                                jsonObjectDatosproductos.getString("_rev"), "DELETE"
                        ).get();
                        JSONObject jsonRespEliminar = new JSONObject(resp);
                        if(jsonRespEliminar.getBoolean("ok")){
                            jsonArrayDatosProductos.remove(position);
                            mostrarDatosProductos();
                        }
                    }
                    miBD = new DB(getApplicationContext(), "", null, 1);
                    datosProductosCursor = miBD.administracion_productos("eliminar", new String[]{jsonObjectDatosproductos.getString("_id")});//idAmigo
                    obtenerDatosProductos();
                    mostrarMsgToast("Registro Eliminado con exito...");
                    dialog.dismiss();//cerrar el cuadro de dialogo
                }catch (Exception e){
                    mostrarMsgToast(e.getMessage());
                }
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
        }while(datosProductosCursor.moveToNext());
        adaptadorImagenes adaptadorImagenes = new adaptadorImagenes(getApplicationContext(),productosArrayList);
        ltsProductos.setAdapter(adaptadorImagenes);
        registerForContextMenu(ltsProductos);
        productosArrayListCopy.addAll(productosArrayList);

    }
    private void mostrarMsgToast (String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

  private class ConexionServer extends AsyncTask<String,String,String>{
        HttpURLConnection urlConnection;
      @Override
      protected void onPostExecute(String s) {
          super.onPostExecute(s);
      }

      @Override
      protected String doInBackground(String... parametros) {
          StringBuilder result = new StringBuilder();
          try {
              String uri = parametros[0];
              String metodo = parametros[1];
              URL url = new URL(uri);
              urlConnection = (HttpURLConnection)url.openConnection();
              urlConnection.setRequestMethod(metodo);

              InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
              BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((inputStream)));
              String linea;
              while ((linea=bufferedReader.readLine())!=null){
                  result.append(linea);
              }
          }catch (Exception e){
              Log.i("GET", e.getMessage());
          }
          return result.toString();
      }


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