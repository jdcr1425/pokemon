package com.example.juandavid.pokemon_home;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.juandavid.pokemon_home.Controllers.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

/**
 * Created by Juan David on 03/10/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    //Con esta prueba aseguramos que cuando le demos al boton seleccionar, si este haciendo los cambios en los texview que muestran los datos del pokemon
    public void AsegurarCambioDeTexto(){
        onView(withId(R.id.textView3))
                .perform(typeText("hola"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button6)).perform(click());

        onView(withId(R.id.textView3)).check(matches(withText("hola")));
    }

    @Test
    //Comprobar que el boton de pelea esta deshabilidado siempre, al comienzo de la aplicacion.
    public void comprobarEstadoBotonPelea(){

        onView(withId(R.id.button)).check(matches(not(isEnabled())));


        }





}
