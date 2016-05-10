package com.cespedes.tatiana.sustentacion7;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Inventario extends AppCompatActivity {

    private ListView lista;
    private Datos peluche;
    private Cursor c;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        peluche = new Datos(this);

        lista = (ListView) findViewById(R.id.Linventari);

        String[] from = new String[]{Datos.Columnas.NOMBRE, Datos.Columnas.CANTIDAD, Datos.Columnas.VALOR};
        int[] to = new int[]{R.id.text1, R.id.text2, R.id.text3};

        c = peluche.cargarCursor();
        adapter = new SimpleCursorAdapter(this, R.layout.lista, c, from, to);
        lista.setAdapter(adapter);
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
