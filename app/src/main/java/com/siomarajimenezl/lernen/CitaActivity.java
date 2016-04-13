package com.siomarajimenezl.lernen;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;

public class CitaActivity extends BaseActivity {

    private DatePicker date;
    private String nombre, curso;
    //Drawer
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load titles from strings.xml

        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);//load icons from strings.xml

        set(navMenuTitles, navMenuIcons);

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
