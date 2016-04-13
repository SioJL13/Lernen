package com.siomarajimenezl.lernen;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

public class FinCitaActivity extends BaseActivity {

    private TimePicker tiempo;
    private EditText locacion;
    private Firebase ref, autRef;
    private AuthData authData;
    private String nombre, curso, fecha, hora;
    private int dia,mes,ano;
    //Drawer
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_cita);

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load titles from strings.xml

        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);//load icons from strings.xml

        set(navMenuTitles, navMenuIcons);

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://vivid-heat-5652.firebaseio.com/Cliente");

        authData = ref.getAuth();
        autRef = ref.child(authData.getUid()).child("Citas");

        tiempo = (TimePicker)findViewById(R.id.timePicker);
        locacion = (EditText)findViewById(R.id.editText5);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            nombre = extras.getString("NombreTutor");
            curso = extras.getString("Curso");
            dia = extras.getInt("Dia");
            mes = extras.getInt("Mes");
            ano = extras.getInt("Ano");

        }

        fecha = String.valueOf(dia) + "/" + String.valueOf(mes) + "/" + String.valueOf(ano);


    }

    public void finCitaActivity(View v){
        Intent i = new Intent(this, MainActivity.class);

        hora = String.valueOf(tiempo.getHour()) + ":" + String.valueOf(tiempo.getMinute());

        Map<String, String>citaDatos = new HashMap<String, String>();
        citaDatos.put("Tutor", nombre);
        citaDatos.put("Curso", curso);
        citaDatos.put("Fecha", fecha);
        citaDatos.put("Hora", hora);
        citaDatos.put("Lugar", locacion.getText().toString());

        Map<String, Map<String, String>> users = new HashMap<String, Map<String, String>>();
        users.put("Cita", citaDatos);

        autRef.setValue(users);

        Toast.makeText(getApplicationContext(),
                "Cita creada",
                Toast.LENGTH_LONG).show();

        startActivity(i);
    }

}
