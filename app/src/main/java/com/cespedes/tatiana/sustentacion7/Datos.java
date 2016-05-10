package com.cespedes.tatiana.sustentacion7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.widget.Toast;

/**
 * Created by Tatiana on 4/05/2016.
 *
 */
public class Datos {
    public static final String TABLE_NAME = "pel";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";

    //Campos de la tabla
    public static class Columnas{
        public static final String ID = "_id";
        public static final String NOMBRE = "nombre";
        public static final String CANTIDAD = "cantidad";
        public static final String VALOR = "valor";
    }

    //Script de Creación de la tabla
    public static final String CREAR_TABLA =
            "create table "+ TABLE_NAME+" ( " + Columnas.ID+" "+INT_TYPE+" primary key autoincrement, " +
                    Columnas.NOMBRE +" "+STRING_TYPE+" not null, " +
                    Columnas.CANTIDAD + " " + INT_TYPE+" not null, " +
                    Columnas.VALOR +" "+ INT_TYPE+" not null);";

    //Scripts de inserción por defecto
    public static final String INSERTAR_DATOS =
            "insert into "+ TABLE_NAME +" values(null," + "\"Ganancias\"," + "\"0\"," + "\"0\"),"
                    + "(null," + "\"Iron Man\"," + "\"10\"," + "\"15000\"),"
                    + "(null," + "\"Viuda Negra\"," + "\"10\"," +  "\"12000\"),"
                    + "(null," + "\"Capitan America\"," + "\"10\"," +  "\"15000\"),"
                    + "(null," + "\"Hulk\"," + "\"10\"," +  "\"12000\"),"
                    + "(null," + "\"Bruja Escarlata\"," + "\"10\"," +  "\"15000\"),"
                    + "(null," + "\"SpiderMan\"," + "\"10\"," +  "\"10000\");";


    private BaseDeDatos peluche;
    private SQLiteDatabase bd;
    private Cursor c;


    public Datos(Context context) {
        peluche = new BaseDeDatos(context);
        bd = peluche.getWritableDatabase();
    }

    private ContentValues generarContent(String nombre, int cantidad, int valor){
        ContentValues registro = new ContentValues();
        registro.put(Columnas.NOMBRE , nombre);
        registro.put(Columnas.CANTIDAD, cantidad);
        registro.put(Columnas.VALOR, valor);
        return registro;
    }


    public void insertar(String nombre,int cantidad, int valor){
        bd.insert(TABLE_NAME, null, generarContent(nombre, cantidad, valor));// devuelve -1 si a habido algún problema
//        bd.close();//cerrar para que guarde
    }

    public int eliminarDatos(String nombre){
        int c = bd.delete(TABLE_NAME, Columnas.NOMBRE + "=?", new String[]{nombre});
        return c;
    }

    public int editar(String nombre, int cant, int valor){

        int c= bd.update(TABLE_NAME, generarContent(nombre, cant, valor), Columnas.NOMBRE + "=?", new String[]{nombre});
        //bd.close();
        return c;
    }

    public Cursor cargarCursor (){

        String[] colum = new String[]{
                Columnas.ID, Columnas.NOMBRE, Columnas.CANTIDAD, Columnas.VALOR};
        return bd.query(TABLE_NAME, colum, Columnas.NOMBRE + "!= ?",new String[] {"Ganancias"},null,null,null);

    }

    public Cursor buscar(String nombre){
        String[] colum = new String[]{
                Columnas.ID, Columnas.NOMBRE, Columnas.CANTIDAD, Columnas.VALOR};
        return bd.query(TABLE_NAME, colum, Columnas.NOMBRE + "= ?",new String[]{nombre},null,null,null);
    }



}