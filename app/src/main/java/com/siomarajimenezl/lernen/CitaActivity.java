package com.siomarajimenezl.lernen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;

public class CitaActivity extends AppCompatActivity {

    private DatePicker date;
    private String nombre, curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);

        date = (DatePicker)findViewById(R.id.datePicker);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            nombre = extras.getString("NombreTutor");
            curso = extras.getString("Curso");
        }

    }

    public void continuarCitaActivity(View v){
        Intent i = new Intent(this, FinCitaActivity.class);

        i.putExtra("Dia", date.getDayOfMonth());
        i.putExtra("Mes", date.getMonth());
        i.putExtra("Ano", date.getYear());
        i.putExtra("NombreTutor", nombre);
        i.putExtra("Curso", curso);

        startActivity(i);
    }



}
