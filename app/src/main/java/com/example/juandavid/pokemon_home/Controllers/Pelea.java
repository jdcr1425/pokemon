package com.example.juandavid.pokemon_home.Controllers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandavid.pokemon_home.Tools.DescargarImagen;
import com.example.juandavid.pokemon_home.R;

import java.util.List;
import java.util.Random;

public class Pelea extends Activity {

    ProgressBar vida1;
    ProgressBar vida2;

    Button boton1;
    Button boton2;
    Button boton3;
    Button boton11;
    Button boton22;
    Button boton33;

    TextView nombre1;
    TextView nombre2;
    TextView Vida1;
    TextView Vida2;

    String life1;
    String life2;
    String url1;
    String url2;
    String experiencia;
    String experiencia2;
    String Nombre1;
    String Nombre2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelea);

        //textviews para la mostrar la vida en forma numerica
        Vida1 = (TextView) findViewById(R.id.vidauno);
        Vida2 = (TextView) findViewById(R.id.vidados);

        life1 = Vida1.getText().toString();
        life2 = Vida2.getText().toString();

        //Barra de progreso para mostrar la vida de cada pokemon.
        vida1 = (ProgressBar) findViewById(R.id.progressBar);
        vida2 = (ProgressBar) findViewById(R.id.progressBar2);

        //Le asiganamos 100 de 'vida' a cada pokemon.
        vida1.setProgress(100);
        vida2.setProgress(100);

        //COn esto iremos capturar los datos que fueron mandados desde la actividad anterior.
        Bundle bundle = getIntent().getExtras();

        Nombre1 = bundle.getString("name");
        Nombre2 = bundle.getString("name2");
        url1 = bundle.getString("url1");
        url2 = bundle.getString("url2");
        List<String> habilidades = bundle.getStringArrayList("habilidades");
        List<String> habilidades2 = bundle.getStringArrayList("habilidades2");
        experiencia = bundle.getString("experiencia1");
        experiencia2 = bundle.getString("experiencia2");



        boton1 = (Button) findViewById(R.id.button15);
        boton2 = (Button) findViewById(R.id.button14);
        boton3 = (Button) findViewById(R.id.button13);

        //para mostrar nombre en los textViews de la vista,
        nombre1 = (TextView) findViewById(R.id.nombre1);
        nombre1.setText(Nombre1);

        nombre2 = (TextView) findViewById(R.id.nombre2);
        nombre2.setText(Nombre2);

        //Estas condiciones se implementaron para manejar y  mostrar la cantidad de habilidades de cada pokemon.
        if (habilidades.size() == 1) {
            boton2.setVisibility(View.INVISIBLE);
            boton3.setVisibility(View.INVISIBLE);
            boton1.setText(habilidades.get(0));
        }
        if (habilidades.size() == 2) {
            boton1.setText(habilidades.get(0));
            boton2.setText(habilidades.get(1));
            boton3.setVisibility(View.INVISIBLE);
        }
        if (habilidades.size() == 3) {
            boton1.setText(habilidades.get(0));
            boton2.setText(habilidades.get(1));
            boton3.setText(habilidades.get(2));
        }

        boton11 = (Button) findViewById(R.id.button18);
        boton22 = (Button) findViewById(R.id.button17);
        boton33 = (Button) findViewById(R.id.button16);

        if (habilidades2.size() == 1) {
            boton22.setVisibility(View.INVISIBLE);
            boton33.setVisibility(View.INVISIBLE);
            boton11.setText(habilidades2.get(0));
        }
        if (habilidades2.size() == 2) {
            boton11.setText(habilidades2.get(0));
            boton22.setText(habilidades2.get(1));
            boton33.setVisibility(View.INVISIBLE);
        }
        if (habilidades2.size() == 3) {
            boton11.setText(habilidades2.get(0));
            boton22.setText(habilidades2.get(1));
            boton33.setText(habilidades2.get(2));
        }

        // Descargar imagen desde url y mostrarla en un textview
        new DescargarImagen((ImageView) findViewById(R.id.imageView))
                .execute(url1);

        new DescargarImagen((ImageView) findViewById(R.id.imageView2))
                .execute(url2);

        setUP();


        //Iniciamos el boton que dara inicio a la pelea y su respectivo evento.

        final ImageButton fight = (ImageButton) findViewById(R.id.imageButton);

        fight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int exp1, exp2;

                exp1 = Integer.parseInt(experiencia);
                exp2 = Integer.parseInt(experiencia2);


                //El pokemon con mas experiencia tendra el primer turno
                if (exp1 > exp2) {
                    boton1.setEnabled(true);
                    boton2.setEnabled(true);
                    boton3.setEnabled(true);

                    Toast.makeText(getApplicationContext(), "Comienza " + Nombre1 ,
                            Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(getApplicationContext(), "Comienza " + Nombre2 ,
                            Toast.LENGTH_SHORT).show();
                    boton11.setEnabled(true);
                    boton22.setEnabled(true);
                    boton33.setEnabled(true);
                }
                //Cada boton tiene un evento donde se llama el metodo Attack() y chech() , para atacar y verificar la vida siempre.
                boton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Attack(vida2, Vida2);
                        check(vida1, vida2);
                        msj(Nombre2);

                        boton2.setEnabled(false);
                        boton3.setEnabled(false);
                        boton1.setEnabled(false);

                        boton22.setEnabled(true);
                        boton33.setEnabled(true);
                        boton11.setEnabled(true);
                    }
                });

                boton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Attack(vida2, Vida2);
                        check(vida1, vida2);
                        msj(Nombre2);

                        boton2.setEnabled(false);
                        boton3.setEnabled(false);
                        boton1.setEnabled(false);

                        boton22.setEnabled(true);
                        boton33.setEnabled(true);
                        boton11.setEnabled(true);
                    }
                });

                boton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Attack(vida2, Vida2);
                        check(vida1, vida2);
                        msj(Nombre2);

                        boton2.setEnabled(false);
                        boton3.setEnabled(false);
                        boton1.setEnabled(false);

                        boton22.setEnabled(true);
                        boton33.setEnabled(true);
                        boton11.setEnabled(true);
                    }
                });

                boton11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Attack(vida1, Vida1);
                        check(vida1, vida2);
                        msj(Nombre1);

                        boton22.setEnabled(false);
                        boton33.setEnabled(false);
                        boton11.setEnabled(false);

                        boton2.setEnabled(true);
                        boton3.setEnabled(true);
                        boton1.setEnabled(true);
                    }
                });

                boton22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Attack(vida1, Vida1);
                        check(vida1, vida2);
                        msj(Nombre1);

                        boton22.setEnabled(false);
                        boton33.setEnabled(false);
                        boton11.setEnabled(false);

                        boton2.setEnabled(true);
                        boton3.setEnabled(true);
                        boton1.setEnabled(true);
                    }
                });

                boton33.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Attack(vida1, Vida1);
                        check(vida1, vida2);
                        msj(Nombre1);

                        boton22.setEnabled(false);
                        boton33.setEnabled(false);
                        boton11.setEnabled(false);

                        boton2.setEnabled(true);
                        boton3.setEnabled(true);
                        boton1.setEnabled(true);

                    }
                });

                fight.setEnabled(false);

            }
        });

    }

    //Deshabilitar todos los botones, que respresentan las habilidades de cada pokemon.
    public void setUP() {
        boton1.setEnabled(false);
        boton2.setEnabled(false);
        boton3.setEnabled(false);

        boton11.setEnabled(false);
        boton22.setEnabled(false);
        boton33.setEnabled(false);

    }
    //Con este metodo vamos a verificar la vida de cada pokemon para saber asi quien gana la batalla.
    public void check(ProgressBar a, ProgressBar b) {

        //Esto se usa para cambiar el color de la barra de vida de un nivel bueno (azul) a un nivel critico(rojo)
        if (a.getProgress() < 40) {
            a.getProgressDrawable().setColorFilter(
                    Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if (b.getProgress() < 40) {
                b.getProgressDrawable().setColorFilter(
                        Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

                }


        //aqui defeinimos que el pokemon 2 gana la batalla y lo mostarmos en otra actividad.
        if (a.getProgress() < 1) {
            Toast.makeText(this, "¡¡¡¡GAME OVER, " + Nombre2 + " Ganó!!!!",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Pelea.this, Ganador.class);

            intent.putExtra("url", url2);
            intent.putExtra("name", Nombre2);

            startActivity(intent);

        } else {
        //aqui defeinimos que el pokemon 1 gana la batalla y lo mostramos en otra actividad.
            if (b.getProgress() < 1) {
                Toast.makeText(this, "¡¡¡¡GAME OVER, " + Nombre1 + " Ganó!!!!",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Pelea.this, Ganador.class);
                intent.putExtra("url", url1);
                intent.putExtra("name", Nombre1);

                startActivity(intent);

            }

        }
    }
    //Metodo para determinar cuanto daño recibe el pokemon, y para actualizar los estados de la barra de progreso que simula la vida.
    public void Attack(ProgressBar afectado, TextView vida_num) {

        Random rr = new Random();
        int ataque = rr.nextInt(afectado.getProgress()) + 1;
        afectado.setProgress(afectado.getProgress() - ataque);
        int operacion = (Integer.parseInt(vida_num.getText().toString()) - ataque);
        vida_num.setText(operacion + "");

    }


    public void msj(String nombre) {

        String[] msj={"Wuau","Eso debió doler",nombre+" esta en graves problemas","Perfecto","Gran ataque"};

        int indice = (int) (Math.random() * 5);

        Toast.makeText(getApplicationContext(), msj[indice],
                Toast.LENGTH_SHORT).show();


    }


}
