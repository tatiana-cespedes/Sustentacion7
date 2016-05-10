package com.cespedes.tatiana.sustentacion7;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import static java.lang.Integer.parseInt;


public class Agregar extends AppCompatActivity {

    EditText eNombre, eCantidad, ePrecio;
    TextView tbus;
    private Cursor c;
    public Datos peluche;
    private ListView lista;
    SimpleCursorAdapter adapter;

    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        eNombre = (EditText) findViewById(R.id.ENombre);
        eCantidad = (EditText) findViewById(R.id.ECantidad);
        ePrecio = (EditText) findViewById(R.id.EValor);

        tbus= (TextView)findViewById(R.id.tBus);

        eNombre.setText("");
        eCantidad.setText("");
        ePrecio.setText("");

        peluche = new Datos(this);

    }



    public void bguardar(View view){

        String nombre = eNombre.getText().toString();
        String cantidad = eCantidad.getText().toString();
        String precio = ePrecio.getText().toString();

        if(nombre.equals("") || cantidad.equals("") || precio.equals("") ){
            Toast.makeText(this, "Parámetros no validos", Toast.LENGTH_SHORT).show();

        }else{
            int can = parseInt(cantidad);
            int pre = parseInt(precio);

            c= peluche.buscar(nombre);
            if (c.moveToFirst()) {
                Toast.makeText(this, "Ya existe en la base de datos", Toast.LENGTH_SHORT).show();

            } else {
                peluche.insertar(nombre, can, pre);
                eNombre.setText("");
                eCantidad.setText("");
                ePrecio.setText("");

                Toast.makeText(this, "Peluche guardado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void consultar (View view){

        String nom = eNombre.getText().toString();
        if(nom.equals("")){
            Toast.makeText(this, "Búsqueda por nombre", Toast.LENGTH_SHORT).show();
        }else {


            c = peluche.buscar(nom);

            if (c.moveToFirst()) {
                eCantidad.setText( c.getString(2));//cantidad
                ePrecio.setText((c.getString(3)));//valor
                tbus.setText(c.getString(0));//id

            } else {
                Toast.makeText(this, "No existe en la base de datos", Toast.LENGTH_SHORT).show();

            }
        }
    }


    public void modificar (View view){

        String nombre = eNombre.getText().toString();
        String cantidad = eCantidad.getText().toString();
        String precio = ePrecio.getText().toString();
        if(nombre.equals("") || cantidad.equals("") || precio.equals("") ){
            Toast.makeText(this, "Parámetros no validos / Busqueda por nombre", Toast.LENGTH_SHORT).show();
        }else {
            int can = parseInt(cantidad);
            int pre = parseInt(precio);
            c = peluche.buscar(nombre);
            if (c.moveToFirst()) {
                int cant = peluche.editar(nombre, can, pre);
                if (cant == 1) {
                    Toast.makeText(this, "Datos modificados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Dato no existe", Toast.LENGTH_SHORT).show();

                }

            } else {
                Toast.makeText(this, "No existe en la base de datos", Toast.LENGTH_SHORT).show();

            }

        }
    }

    public void eliminar (View v){

        String nom = eNombre.getText().toString();

        if(nom.equals("") ){
            Toast.makeText(this, "Búsqueda por nombre", Toast.LENGTH_SHORT).show();
        }else {

            c= peluche.buscar(nom);
            if (c.moveToFirst()) {
                int cant = peluche.eliminarDatos(nom);
                eNombre.setText("");
                eCantidad.setText("");
                ePrecio.setText("");

                if (cant == 1) {
                    Toast.makeText(this, "Dato borrado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No existe estudiante", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "No existe en la base de datos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);//poner los puntitos del menu
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();//le lleva a id el id del item seleccionado
        if(id==R.id.menu_Agregar) {//si fue seleccionado en el menú la opción Config
            Toast.makeText(this, "Agregar, Editar, Eliminar", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Agregar.class);// permiten llamar aplicaciones externas
            startActivity(i);
            return true;
        }

        if(id==R.id.menu_inventario) {//si fue seleccionado en el menú la opción Config
            Toast.makeText(this, "Inventario", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Inventario.class);// permiten llamar aplicaciones externas
            startActivity(i);
            return true;
        }
        if(id==R.id.menu_venta) {//si fue seleccionado en el menú la opción Config
            Toast.makeText(this, "Venta", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);// permiten llamar aplicaciones externas
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


}

