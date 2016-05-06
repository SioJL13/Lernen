package com.siomarajimenezl.lernen;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class PerfilTutoresActivity extends BaseActivity  {

    TextView nombre, materia, escolaridad, descripcion;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    ImageView picture;

    //JSON Request
    private JSONObject jsonObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_tutores);

        // load titulos del string.xml
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        //load icons del strings.xml
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        set(navMenuTitles, navMenuIcons);

        nombre = (TextView) findViewById(R.id.textName);
        materia = (TextView) findViewById(R.id.textMateria);
        escolaridad = (TextView) findViewById(R.id.textDegree);
        descripcion = (TextView) findViewById(R.id.textDescripcion);
        picture = (ImageView)findViewById(R.id.imageView3);


        try {
            jsonObject = new JSONObject(getIntent().getStringExtra("pos_json"));
            String name = jsonObject.get("nombre").toString();
            String course = jsonObject.get("materia").toString();
            String degree = jsonObject.get("escolaridad").toString();
            String bio = jsonObject.get("biografia").toString();
            String foto = jsonObject.get("foto").toString();


            //Log.d("D", nombres);
            nombre.setText(name);
            materia.setText(course);
            escolaridad.setText(degree);
            descripcion.setText(bio);
            Picasso.with(getApplicationContext())
                    .load(foto)
                    .into(picture);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void hacerCitaActivity(View v){
        Intent intent = new Intent(this, CitaActivity.class);

        intent.putExtra("NombreTutor", nombre.getText());
        intent.putExtra("Curso", materia.getText());

        startActivity(intent);
    }


}
