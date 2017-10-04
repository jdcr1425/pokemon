package com.example.juandavid.pokemon_home;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan David on 22/09/2017.
 */

public class Pokemon {


    public String nombre;
    public String experiencia;
    JSONArray tipo = null;
    JSONObject imagen = null;
    JSONArray Habilidades = null;
    List<String> habilidades = new ArrayList<String>();


    public Pokemon(){

    }




    public String getNombre(JSONObject datos){
         String name="";
         try {
            name = datos.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return name;
    }

    public String getExperiencia(JSONObject datos){
        String experiencia="";
        try {
            experiencia = datos.getString("base_experience");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return experiencia;
    }


    public List<String> tipo(JSONObject datos){

        List<String> tipos = new ArrayList<String>();
        try {
            tipo=datos.getJSONArray("types");

            for (int i = 0; i < tipo.length(); i++) {
                JSONObject c = tipo.getJSONObject(i);
                // Ability node is JSON Object
                JSONObject tipo2 = c.getJSONObject("type");
                tipos.add(tipo2.getString("name"));
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

return tipos;

    }


    public String getUrl(JSONObject datos){
       String urlimagen="";
        try {

            imagen = datos.getJSONObject("sprites");
            urlimagen = imagen.getString("front_default");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return urlimagen;
    }

public List<String> getHabilidades(JSONObject datos){
    try {
        Habilidades=datos.getJSONArray("abilities");

        habilidades.clear();
        for (int i = 0; i < Habilidades.length(); i++) {
            JSONObject c = Habilidades.getJSONObject(i);
            // Ability node is JSON Object
            JSONObject habilidadesnew = c.getJSONObject("ability");


            habilidades.add(habilidadesnew.getString("name"));

        }




    } catch (JSONException e) {
        e.printStackTrace();
    }

return habilidades;

}




}



