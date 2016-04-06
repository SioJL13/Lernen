package com.siomarajimenezl.lernen;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PerfilTutoresActivity extends BaseActivity {

    TextView nombre, curso, escolaridad, descripcion;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_tutores);

        // load titulos del string.xml
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        //load icons del strings.xml
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        set(navMenuTitles, navMenuIcons);

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
