package com.siomarajimenezl.lernen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class EditarActivity extends AppCompatActivity {

    private Firebase ref;
    private AuthData authData;
    EditText nombreUsuario, correoUsuario, telUsuario, degreeUsuario, bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://vivid-heat-5652.firebaseio.com/Cliente");

        authData = ref.getAuth();
        Firebase autRef = ref.child(authData.getUid());

        nombreUsuario = (EditText)findViewById(R.id.cambiaNom);
        correoUsuario = (EditText)findViewById(R.id.editText);
        telUsuario = (EditText)findViewById(R.id.editText2);
        degreeUsuario = (EditText)findViewById(R.id.editText3);
        bio = (EditText)findViewById(R.id.editText4);

        autRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                nombreUsuario.setText(snapshot.child("Nombre").getValue().toString());
                correoUsuario.setText(snapshot.child("Email").getValue().toString());
                telUsuario.setText(snapshot.child("Telephone").getValue().toString());
                degreeUsuario.setText(snapshot.child("Degree").getValue().toString());
                bio.setText(snapshot.child("Bio").getValue().toString());

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    public void regresar(View v){

        if (nombreUsuario.getText().toString() != ""){

            Firebase autRef = ref.child(authData.getUid());
            autRef.child("Nombre").setValue(nombreUsuario.getText().toString());
            autRef.child("Email").setValue(correoUsuario.getText().toString());
            autRef.child("Telephone").setValue(telUsuario.getText().toString());
            autRef.child("Degree").setValue(degreeUsuario.getText().toString());
            autRef.child("Bio").setValue(bio.getText().toString());

        }

        Intent intent = new Intent(this, PerfilUserActivity.class);
        startActivity(intent);
    }

}
