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

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class EditarActivity extends AppCompatActivity {

    private Firebase ref;
    private AuthData authData;
    EditText nombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://vivid-heat-5652.firebaseio.com/Cliente");

        authData = ref.getAuth();

        nombreUsuario = (EditText)findViewById(R.id.cambiaNom);
    }

    public void regresar(View v){

        if (nombreUsuario.getText().toString() != ""){

            Firebase autRef = ref.child(authData.getUid());
            autRef.child("Nombre").setValue(nombreUsuario.getText().toString());

        }

        Intent intent = new Intent(this, PerfilUserActivity.class);
        startActivity(intent);
    }

}
