package com.cespedes.tatiana.sustentacion7;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    EditText eNombre, eCantidad;
    private Cursor c, c2;
    public Datos peluche;
    int valorventa=0;
    TextView tVenta, tGanancias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eNombre= (EditText)findViewById(R.id.ENombreV);
        eCantidad= (EditText)findViewById(R.id.ECantidadV);
        tVenta = (TextView) findViewById(R.id.Tvaloventa);
        tGanancias = (TextView) findViewById(R.id.TventasT);

        peluche = new Datos(this);

    }

    public void consultarVenta (View view){

        String nom = eNombre.getText().toString();
        String cantidad = eCantidad.getText().toString();


        if(nom.equals("")){
            Toast.makeText(this, "Búsqueda por nombre", Toast.LENGTH_SHORT).show();
        }else {
            int can = parseInt(cantidad);
            c = peluche.buscar(nom);

            if (c.moveToFirst()) {
                valorventa= (parseInt(c.getString(3)))*(can);//precio*cantidad

                int cantidadAct= parseInt(c.getString(2))-can;
                if(cantidadAct >= 0){

                    if(cantidadAct<=5) {
                        String titulo = "Pocos peluches de";
                        String contenido = "" + nom + ", cantidad= " + cantidadAct;
                        String ticker = "nom = " + nom + "    cant = " + cantidadAct;

                        NotificationCompat.Builder builder = new NotificationCompat.
                                Builder(getApplicationContext());

                        builder.setContentTitle(titulo)
                                .setContentText(contenido)
                                .setTicker(ticker)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentInfo("Dato");

                        Intent noIntent = new Intent(MainActivity.this, Inventario.class);

                        PendingIntent contIntent = PendingIntent.
                                getActivity(MainActivity.this, 0, noIntent, 0);
                        builder.setContentIntent(contIntent);

                        NotificationManager nm = (NotificationManager)
                                getSystemService(NOTIFICATION_SERVICE);

                        nm.notify(1, builder.build());
                    }
                    tVenta.setText("Valor de la venta = " + valorventa);
                    Toast.makeText(this, "Venta registrada", Toast.LENGTH_SHORT).show();
                    int val= parseInt(c.getString(3));
                    peluche.editar(nom, cantidadAct, val);


                    c2 = peluche.buscar("Ganancias");
                    if (c2.moveToFirst()) {
                        int Ganacias = parseInt(c2.getString(3));
                        int gan= Ganacias + valorventa;
                        peluche.editar("Ganancias", 0, gan);
                        tGanancias.setText("Ganancias Totales =  " + gan);

                    }
                }else{

                    Toast.makeText(this, "No hay existencias de " + nom + "  para realizar la venta, cant= " + cantidadAct, Toast.LENGTH_SHORT).show();
                }


            } else {
                Toast.makeText(this, "No existe en la base de datos", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void MostrarGanancias(View view){
        c2 = peluche.buscar("Ganancias");
        if (c2.moveToFirst()) {
            int Ganacias = parseInt(c2.getString(3));
            tGanancias.setText("Ganancias Totales =  " + Ganacias);

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
