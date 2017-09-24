package com.example.juandavid.pokemon_home.Actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.juandavid.pokemon_home.Tools.DescargarImagen;
import com.example.juandavid.pokemon_home.Singleton.MySingleton;
import com.example.juandavid.pokemon_home.Factory.PokeFactory;
import com.example.juandavid.pokemon_home.Pokemon.Pokemon;
import com.example.juandavid.pokemon_home.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    String urlimagen = "";
    String urlimagen2 = "";
    String expeiencia = "";
    String expeiencia2 = "";
    String nombre1 = "";
    String nombre2 = "";

    List<String> tipos2 = new ArrayList<String>();
    List<String> habilidades = new ArrayList<String>();
    List<String> habilidades2 = new ArrayList<String>();

    Pokemon pokemon1;
    Pokemon pokemon2;

    Button btn;
    Button boton;

    TextView mTxtDisplay;
    TextView mTxtDisplay2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Uso del Factory para crear nuestros pokemones
        PokeFactory factoria = new PokeFactory();
        pokemon1 = factoria.getPokemon("pokemon");
        pokemon2 = factoria.getPokemon("pokemon");

        btn = (Button) findViewById(R.id.button);
        //Deshabilitamos el boton que nos dirige hacia la Pelea, para evitar errores.
        btn.setEnabled(false);


        //Le agregamos la funcionalidad al boton de Seleccionar
        boton = (Button) findViewById(R.id.button6);
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                mTxtDisplay = (TextView) findViewById(R.id.textView3);
                mTxtDisplay2 = (TextView) findViewById(R.id.textView2);

                //Generacion de un numero random, para enseguida ser concatenado a la url de la api.
                int indicePokemon = (int) (Math.random() * 721);

                String url = "http://pokeapi.co/api/v2/pokemon/" + indicePokemon + "";

                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        nombre1 = pokemon1.getNombre(response);
                        expeiencia = pokemon1.getExperiencia(response);
                        habilidades = pokemon1.getHabilidades(response);
                        List<String> tipos = pokemon1.tipo(response);


                        //Declaracion del nombre , los tipos y la experiencia del pokemon1
                        if (tipos.size() < 2) {
                            mTxtDisplay.setText(nombre1 + "\nTipo : " + tipos.get(0) + "\nEXP : " + expeiencia);
                            tipos.clear();
                        } else {
                            mTxtDisplay.setText(nombre1 + "\nTipo : " + tipos.get(0) + " , " + tipos.get(1) + "\nEXP : " + expeiencia);
                            tipos.clear();
                        }

                        urlimagen = pokemon1.getUrl(response);

                        //Con esta linea descargamos la imagen desde la Url y la colocamos en el ImageView
                        new DescargarImagen((ImageView) findViewById(R.id.imageView))
                                .execute(urlimagen);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsObjRequest);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //Este seria el JsonObjectRequest para el pokemon 2

                //Generacion de un numero random, para enseguida ser concatenado a la url de la api.
                int indicePokemon2 = (int) (Math.random() * 721);

                String url2 = "http://pokeapi.co/api/v2/pokemon/" + indicePokemon2 + "";

                JsonObjectRequest jsObjRequest2 = new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        nombre2 = pokemon2.getNombre(response);
                        expeiencia2 = pokemon2.getExperiencia(response);
                        habilidades2 = pokemon2.getHabilidades(response);
                        List<String> tipos = pokemon2.tipo(response);
                        //Declaracion del nombre , los tipos y la experiencia del pokemon2
                        if (tipos.size() < 2) {
                            mTxtDisplay2.setText(nombre2 + "\nTipo : " + tipos.get(0) + "\nEXP : "
                                    + expeiencia2);
                            tipos.clear();
                        } else {
                            mTxtDisplay2.setText(nombre2 + "\nTipo : " + tipos.get(0) + " , " + tipos.get(1) + "\nEXP : " + expeiencia2);
                            tipos.clear();
                        }
                        urlimagen2 = pokemon2.getUrl(response);

                        //Con esta linea descargamos la imagen desde la Url y la colocamos en el ImageView
                        new DescargarImagen((ImageView) findViewById(R.id.imageView2))
                                .execute(urlimagen2);

                        //Aqui habilitamos el boton de pelea
                        btn.setEnabled(true);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsObjRequest2);

            }

        });

        irApelea();

    }

    //Con este metodo Vamos a la actividad que simula la batalla.
    // con el putExtra() establecemoslosparamentros que queremos pasarle a la siguiente actividad.
    public void irApelea() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Pelea.class);
                intent.putExtra("url1", urlimagen);
                intent.putExtra("url2", urlimagen2);
                intent.putStringArrayListExtra("habilidades", (ArrayList<String>) habilidades);
                intent.putStringArrayListExtra("habilidades2", (ArrayList<String>) habilidades2);
                intent.putExtra("experiencia1", expeiencia);
                intent.putExtra("experiencia2", expeiencia2);
                intent.putExtra("name", nombre1);
                intent.putExtra("name2", nombre2);
                startActivity(intent);
            }
        });

    }

}
