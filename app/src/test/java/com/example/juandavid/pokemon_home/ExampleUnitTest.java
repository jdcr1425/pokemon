package com.example.juandavid.pokemon_home;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.juandavid.pokemon_home.Controllers.MainActivity;
import com.example.juandavid.pokemon_home.Tools.DescargarImagen;
import com.example.juandavid.pokemon_home.Tools.MySingleton;

import org.json.JSONObject;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine
 * (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    //Esta prueba es para comprobar que esta retornando la URL del pokemon correctamente
    public void IsReturningTheUrl() throws Exception {

        Pokemon pokemon1 = new Pokemon();

        assertEquals("http://pokeapi.co/api/v2/pokemon/10", pokemon1.getUrlPokemon(10));
    }

}
