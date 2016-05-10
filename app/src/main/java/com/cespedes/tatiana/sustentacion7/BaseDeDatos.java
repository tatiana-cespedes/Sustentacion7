package com.cespedes.tatiana.sustentacion7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tatiana on 4/05/2016.
 */
public class BaseDeDatos extends SQLiteOpenHelper {

    private static final String DB_NAME = "DBpeluchitos";

    public BaseDeDatos(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Datos.CREAR_TABLA);
         db.execSQL(Datos.INSERTAR_DATOS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists pelu");//elimina la anterior con drop
        db.execSQL(Datos.CREAR_TABLA);

    }




}