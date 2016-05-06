package com.siomarajimenezl.lernen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PerfilUserActivity extends BaseActivity {

    private Firebase ref;
    private AuthData authData;
    TextView nombreUsuario, correoUsuario,telUsuario, degreeUsuario, bio;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private String email_user,password_user;
    ProgressDialog progress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_user);

        // load titulos del string.xml
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        //load icons del strings.xml
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        set(navMenuTitles, navMenuIcons);

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://vivid-heat-5652.firebaseio.com/Cliente");

        authData = ref.getAuth();
        Firebase autRef = ref.child(authData.getUid());

        nombreUsuario = (TextView)findViewById(R.id.textUsuario);
        correoUsuario = (TextView)findViewById(R.id.textEmail);
        telUsuario = (TextView)findViewById(R.id.textPhone);
        degreeUsuario = (TextView)findViewById(R.id.textDegree);
        bio = (TextView)findViewById(R.id.textBio);



        autRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                try {
                    email_user = snapshot.child("Email").getValue().toString();
                    password_user = snapshot.child("Password").getValue().toString();

                    nombreUsuario.setText(snapshot.child("Nombre").getValue().toString());
                    correoUsuario.setText(snapshot.child("Email").getValue().toString());
                    Log.d("PRUEBA", snapshot.child("Telephone") + "");
                    telUsuario.setText(snapshot.child("Telephone").getValue().toString());
                    degreeUsuario.setText(snapshot.child("Degree").getValue().toString());
                    bio.setText(snapshot.child("Bio").getValue().toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    public void cambiarEditarActivity(View v){
        Intent intent = new Intent(this, EditarActivity.class);
        startActivity(intent);
    }

    public void eliminarUsuario(View v){
        progress = ProgressDialog.show(this,"Eliminando cuenta","No cierres la aplicacion", true);


        ref.removeUser(email_user, password_user, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                Log.d("Sucess", "Logout");
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                try{
                    ref.child(authData.getUid()).removeValue();

                }catch (Exception e){
                    e.printStackTrace();
                }

                startActivity(intent);
                progress.dismiss();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Log.d("Error al eliminar", firebaseError.getMessage());

            }
        });

        ref.child(authData.getUid()).removeValue();
        Log.d("Remove", email_user);
        Log.d("PASSWORD DELETE", password_user);
        //TODO: ELIMINAR EL ERROR DE QUE FALLO LA APLICACION



    }


}
