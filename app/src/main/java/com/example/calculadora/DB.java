package com.example.calculadora;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    Context miContext;
    static String nombreDB = "db_Productos";
    static String tblproductos = "CREATE TABLE tblproductos (idproducto integer primary key autoincrement,codigo text, nombre text,marca text, presentacion text, precio text, urlPhoto text)";

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombreDB, factory, version);
        miContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tblproductos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      //No porque es para migrar a una version o actualizar a una nueva version...
    }
    public Cursor administracion_productos (String accion, String [] datos){
        try {
        Cursor datosCursor = null;
        SQLiteDatabase  sqLiteDatabaseW = getWritableDatabase();
        SQLiteDatabase  sqLiteDatabaseR = getReadableDatabase();
        switch (accion){
            case "consultar":
                datosCursor = sqLiteDatabaseR.rawQuery("select * from tblproductos order by codigo", null);
                break;
            case "nuevo":
                sqLiteDatabaseW.execSQL("INSERT INTO tblproductos(codigo, nombre, marca, presentacion, precio, urlPhoto) VALUES ('"+datos[1]+"','"+datos[2]+"','"+datos[3]+"','"+datos[4]+"','"+datos[5]+"','"+datos[6]+"')");
                break;
            case "modificar":
               sqLiteDatabaseW.execSQL("UPDATE tblproductos SET codigo='" + datos[1] + "',nombre='" + datos[2] + "',presentacion='" + datos[3] + "',marca='" + datos[4] + "',precio='" + datos[5] + "',urlPhoto='" + datos[6] + "' WHERE idProductos='" + datos[0] + "'");
            case "eliminar":
                sqLiteDatabaseW.execSQL("DELETE FROM tblproductos WHERE idProdutos='" + datos[0] + "'");
                break;
        }

        return datosCursor;
    }catch (Exception e){
            Toast.makeText(miContext, "Error en la administracion de la BD "+ e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
}