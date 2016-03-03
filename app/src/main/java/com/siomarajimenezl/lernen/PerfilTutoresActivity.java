package com.siomarajimenezl.lernen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PerfilTutoresActivity extends AppCompatActivity {

    TextView nombre, curso, escolaridad, descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_tutores);

        nombre = (TextView)findViewById(R.id.textName);
        curso = (TextView)findViewById(R.id.textCourse);
        escolaridad = (TextView)findViewById(R.id.textDegree);
        descripcion = (TextView)findViewById(R.id.textDescripcion);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            nombre.setText(extras.getString("NombreTutor"));
            curso.setText(extras.getString("Curso"));
            escolaridad.setText(extras.getString("Escolaridad"));
            descripcion.setText(extras.getString("Descripcion"));
        }

    }
}
