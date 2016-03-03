package com.siomarajimenezl.lernen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class RegistrarActivity extends AppCompatActivity {

    private Firebase myFB;
    private boolean flag;

    EditText nombreUsuario;
    EditText passwordUsuario;
    EditText emailUsuario;
    Button crearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        Firebase.setAndroidContext(this);
        myFB = new Firebase("https://vivid-heat-5652.firebaseIO.com");

        nombreUsuario = (EditText)findViewById(R.id.inputNombre);
        passwordUsuario = (EditText)findViewById(R.id.inputPassword);
        emailUsuario = (EditText) findViewById(R.id.inputEmail);

        flag = false;
    }

    public boolean validarRegistro(View v){
        boolean valido = true;

        String nombre = nombreUsuario.getText().toString();
        String email = emailUsuario.getText().toString();
        String password = passwordUsuario.getText().toString();

        if(nombre.isEmpty() || nombre.length() < 3){
            nombreUsuario.setError("Debe contener al menos 3 caracteres.");
            valido = false;
        } else{
            nombreUsuario.setError(null);
        }

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailUsuario.setError("Ingresa un correo correcto.");
            valido = false;
        } else{
            emailUsuario.setError(null);
        }

        if(password.isEmpty() || password.length() < 4 || password.length() > 10){
            passwordUsuario.setError("Debe contener entre 4 y 10 caracteres alfanumericos.");
            valido = false;
        } else{
            passwordUsuario.setError(null);
        }

        return valido;
    }

    public void insertarUsuario(View v){

        if(validarRegistro(v)){

            myFB.createUser(emailUsuario.getText().toString(), passwordUsuario.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {

                public void onSuccess(Map<String, Object> result) {
                    Log.i("Register", "Success: " + result.get("uid"));

                    Firebase usersRef = myFB.child("Cliente").child(result.get("uid").toString());

                    Map<String, String> agregar = new HashMap<String, String>();
                    agregar.put("Nombre", nombreUsuario.getText().toString());
                    agregar.put("Email", emailUsuario.getText().toString());
                    agregar.put("Contraseña", passwordUsuario.getText().toString());

                    usersRef.setValue(agregar);

                }

                public void onError(FirebaseError firebaseError) {
                    Log.e("Register", "ERROR!!!" + firebaseError.getDetails());

                }
            });


            myFB.authWithPassword(emailUsuario.getText().toString(), passwordUsuario.getText().toString(), new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {
                    //System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                    Log.i("LOGIN", "IS IN THE DATABASE");
                    flag = true;
                }

                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    Log.e("LOGIN", "ERROR!!!!");
                }
            });

            if (flag){
                Intent intent = new Intent(this,MainActivity.class );
                startActivity(intent);

                Toast.makeText(getApplicationContext(),
                        "Usuario creado",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }
}
