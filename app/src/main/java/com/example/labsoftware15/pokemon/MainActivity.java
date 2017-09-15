package com.example.labsoftware15.pokemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button boton;
    private Button boton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        boton = (Button)findViewById(R.id.boton1);

        boton.setOnClickListener( new View.OnClickListener() {
            public void onClick(View view){

                final TextView mTxtDisplay;
                ImageView mImageView;
                mTxtDisplay = (TextView) findViewById(R.id.text);



                int indicePokemon= (int) (Math.random()*800);
                Log.d("Aleatorio",indicePokemon+"");



                String url = "http://pokeapi.co/api/v2/pokemon/"+indicePokemon;


                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    mTxtDisplay.setText("Nombre: " + response.getString("name"));

                                    JSONArray jsonArray = response.getJSONArray("sprites");
                                    for (int i = 0; i < jsonArray.length(); i++)
                                    {
                                        try {
                                            JSONObject jsonObjectHijo = jsonArray.getJSONObject(i);

                                            String url=jsonObjectHijo.getString("front_default");

                                            Log.d("imagen",url);

                                        } catch (JSONException e) {
                                            Log.e("Parser JSON", e.toString());
                                        }
                                    }



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO Auto-generated method stub

                            }
                        });


                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsObjRequest);
            }
        });








// Access the RequestQueue through your singleton class.








    }
}
