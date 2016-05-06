package com.siomarajimenezl.lernen;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class FinCitaActivity extends BaseActivity {

    private TimePicker tiempo;
    private EditText locacion;
    private Firebase ref, autRef;
    private AuthData authData;
    private String nombre, curso, fecha, hora, numCita;
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

        autRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                numCita = snapshot.child("Contador").getValue().toString();
                Log.d("Contador", numCita);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });


    }

    public void finCitaActivity(View v){
        Intent i = new Intent(this, MainActivity.class);
        //TODO: REVISAR ERRORES
        hora = String.valueOf(tiempo.getHour()) + ":" + String.valueOf(tiempo.getMinute());

        Map<String, String>citaDatos = new HashMap<String, String>();
        citaDatos.put("Tutor", nombre);
        citaDatos.put("Curso", curso);
        citaDatos.put("Fecha", fecha);
        citaDatos.put("Hora", hora);
        citaDatos.put("Lugar", locacion.getText().toString());

        Firebase userRef = autRef.child(numCita);

        userRef.setValue(citaDatos);

        autRef.child("Contador").setValue(Integer.parseInt(numCita) + 1);

        //setAlarm(getApplicationContext());

        Toast.makeText(getApplicationContext(),
                "Cita creada",
                Toast.LENGTH_LONG).show();

        startActivity(i);
    }

    public void setAlarm(View view) {

        // Define a time value of 5 seconds
        Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;

        // Define our intention of executing AlertReceiver
        Intent alertIntent = new Intent(this, AlertReceiver.class);

        // Allows you to schedule for your application to do something at a later date
        // even if it is in he background or isn't active
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // set() schedules an alarm to trigger
        // Trigger for alertIntent to fire in 5 seconds
        // FLAG_UPDATE_CURRENT : Update the Intent if active
        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime,
                PendingIntent.getBroadcast(this, 1, alertIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT));

    }

}
