package com.siomarajimenezl.lernen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lista;
    ArrayList<Tutors> tutors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView)findViewById(R.id.listView3);

        tutors = new ArrayList<Tutors>();
        tutors.add(new Tutors("Carlos Castro", "Español", "Comunicacion", "Egresado del Tec del Monterrey Campus Monterrey."));
        tutors.add(new Tutors("Patricia Huerta", "Matematicas Computacionales", "ISC", "Maestra de la materia en el ITESM campus Guadalajara."));
        tutors.add(new Tutors("Juan Rodriguez", "Robotica", "IMT","Egresado de la UDG en el 2015, y actualmente trabajo en Flextronics"));
        tutors.add(new Tutors("Maria del Campo", "Etica y Civica", "Preparatoria", "Maestra en la preparatoria Juan Escutia."));
        tutors.add(new Tutors("Jose Alfredo Jimenez", "Artes Visuales", "Secundaria","Egresado de la carrera de Arte en NYU."));

        MyAdapter myAdapter = new MyAdapter(tutors, this);
        lista.setAdapter(myAdapter);
        lista.setOnItemClickListener(this);

    }

    public void cambiarBuscarActivity(View v){
        Intent intent = new Intent(this,BuscarActivity.class);
        startActivity(intent);
    }

    public void cambiarPerfilUserActivity(View v){
        Intent  intent = new Intent(this,PerfilUserActivity.class);
        startActivity(intent);
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
