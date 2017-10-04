package com.example.juandavid.pokemon_home.Controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juandavid.pokemon_home.Tools.DescargarImagen;
import com.example.juandavid.pokemon_home.R;

public class Ganador extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganador);

        //Nos traemos los datos del pokemon ganador. Su nombre, imagen
        Bundle bundle = getIntent().getExtras();

        String nombre = bundle.getString("name");
        String url = bundle.getString("url");

        //Descargar la imagen y colocandola en el ImageView
        new DescargarImagen((ImageView) findViewById(R.id.imageView3))
                .execute(url);

        TextView Nombre = (TextView) findViewById(R.id.nombre);
        Nombre.setText(nombre);

        //Regresar a la actividad principal
        Button btn = (Button) findViewById(R.id.regresar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ganador.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Cerrar la aplicacion
        Button btn2 = (Button) findViewById(R.id.salir);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });

    }
}
