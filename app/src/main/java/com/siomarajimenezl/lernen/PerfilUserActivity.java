package com.siomarajimenezl.lernen;

import android.content.Context;
import android.content.Intent;
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

public class PerfilUserActivity extends AppCompatActivity {

    private Firebase ref;
    private AuthData authData;
    TextView nombreUsuario, correoUsuario;
    EditText materias;

    private final String FILE = "propiedadesUser.xml";
    Properties properties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_user);

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://vivid-heat-5652.firebaseio.com/Cliente");

        authData = ref.getAuth();
        Firebase autRef = ref.child(authData.getUid());

        nombreUsuario = (TextView)findViewById(R.id.textUsuario);
        correoUsuario = (TextView)findViewById(R.id.textEmail);
        materias = (EditText)findViewById(R.id.inputMaterias);

        File file = new File(getFilesDir(),FILE);
        properties = new Properties();


        autRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                nombreUsuario.setText(snapshot.child("Nombre").getValue().toString());
                correoUsuario.setText(snapshot.child("Email").getValue().toString());
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
        Intent intent = new Intent(this, LoginActivity.class);
        ref.child(authData.getUid()).removeValue();
        //TODO: ELIMINAR EL ERROR DE QUE FALLO LA APLICACION
        startActivity(intent);


    }

    /*public void salvarPropertyDevice(){
        try{

            FileOutputStream fos = openFileOutput(FILE, Context.MODE_PRIVATE);
            properties.storeToXML(fos, null);
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToProperty(View v){
        properties.setProperty("field", materias.getText().toString());
        
    }*/
}
