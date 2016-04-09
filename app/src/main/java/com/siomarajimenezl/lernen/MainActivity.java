package com.siomarajimenezl.lernen;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
//TODO: Checar por que se sube la lista cuando le das click

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    ListView lista;
    ArrayList<Tutors> tutors;
    //Drawer
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load titles from strings.xml

        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);//load icons from strings.xml

        set(navMenuTitles,navMenuIcons);

        lista = (ListView)findViewById(R.id.listView3);

        tutors = new ArrayList<Tutors>();
        tutors.add(new Tutors("Carlos Castro", "Español", "Comunicacion", "Egresado del Tec del Monterrey Campus Monterrey."));
        tutors.add(new Tutors("Patricia Huerta", "Matematicas Computacionales", "ISC", "Maestra de la materia en el ITESM campus Guadalajara."));
        tutors.add(new Tutors("Juan Rodriguez", "Robotica", "IMT","Egresado de la UDG en el 2015, y actualmente trabajo en Flextronics"));
        tutors.add(new Tutors("Maria del Campo", "Etica y Civica", "Preparatoria", "Maestra en la preparatoria Juan Escutia."));
        tutors.add(new Tutors("Jose Alfredo Jimenez", "Artes Visuales", "Secundaria","Egresado de la carrera de Arte en NYU."));
        tutors.add(new Tutors("Jose Alfredo Jimenez", "Artes Visuales", "Secundaria","Egresado de la carrera de Arte en NYU."));
        tutors.add(new Tutors("Jose Alfredo Jimenez", "Artes Visuales", "Secundaria","Egresado de la carrera de Arte en NYU."));
        tutors.add(new Tutors("Jose Alfredo Jimenez", "Artes Visuales", "Secundaria","Egresado de la carrera de Arte en NYU."));


        MyAdapter myAdapter = new MyAdapter(tutors, this);
        lista.setAdapter(myAdapter);
        lista.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent i = new Intent(getApplicationContext(), PerfilTutoresActivity.class);

        i.putExtra("NombreTutor", tutors.get(position).getNombre());
        i.putExtra("Curso", tutors.get(position).getCourse());
        i.putExtra("Escolaridad", tutors.get(position).getDegree());
        i.putExtra("Descripcion", tutors.get(position).getDescripcion());

        startActivity(i);
    }
}
