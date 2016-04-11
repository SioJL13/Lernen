package com.siomarajimenezl.lernen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
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
//TODO: Eliminar bug, si tienen el mismo mail.
public class RegistrarActivity extends AppCompatActivity {

    private Firebase myFB;

    TextInputLayout nombreWrapper, passwordWrapper, emailWrapper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        Firebase.setAndroidContext(this);
        myFB = new Firebase("https://vivid-heat-5652.firebaseIO.com");

        nombreWrapper = (TextInputLayout)findViewById(R.id.nombreRegWrapper);
        emailWrapper = (TextInputLayout) findViewById(R.id.correoRegWrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.passwordRegWrapper);

        nombreWrapper.setHint("Nombre");
        emailWrapper.setHint("Correo electronico");
        passwordWrapper.setHint("Password");

    }

    public boolean validarRegistro(View v){
        boolean valido = true;

        String nombre = nombreWrapper.getEditText().getText().toString();
        String email = emailWrapper.getEditText().getText().toString();
        String password = passwordWrapper.getEditText().getText().toString();

        if(nombre.isEmpty() || nombre.length() < 3){
            nombreWrapper.setError("Debe contener al menos 3 caracteres.");
            valido = false;
        } else{
            nombreWrapper.setErrorEnabled(false);
        }

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailWrapper.setError("Ingresa un correo correcto.");
            valido = false;
        } else{
            emailWrapper.setErrorEnabled(false);
        }

        if(password.isEmpty() || password.length() < 4 || password.length() > 10){
            passwordWrapper.setError("Debe contener entre 4 y 10 caracteres alfanumericos.");
            valido = false;
        } else{
            passwordWrapper.setErrorEnabled(false);
        }

        return valido;
    }

    public void insertarUsuario(View v){

        if(validarRegistro(v)){

            myFB.createUser(emailWrapper.getEditText().getText().toString(), passwordWrapper.getEditText().getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {


                @Override
                public void onSuccess(Map<String, Object> result) {
                    System.out.println("Successfully created user account with uid: " + result.get("uid"));

                    myFB.authWithPassword(emailWrapper.getEditText().getText().toString(),
                                          passwordWrapper.getEditText().getText().toString(), new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            HashMap<String, Object> authMap = new HashMap<String, Object>();
                            authMap.put("uid", authData.getUid());
                            authMap.put("Nombre", nombreWrapper.getEditText().getText().toString());
                            authMap.put("Email", emailWrapper.getEditText().getText().toString());
                            authMap.put("Password", passwordWrapper.getEditText().getText().toString());
                            authMap.put("Telephone", "");
                            authMap.put("Degree", "");
                            authMap.put("Bio", "");

                            Firebase userRef = myFB.child("Cliente").child(authData.getUid());

                            userRef.setValue(authMap);

                            System.out.println("User created");
                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            System.out.println("Authentication Error authenticating newly created user. This could be an issue. ");
                            System.out.println(firebaseError.getMessage());

                        }
                    });
                }

                @Override
                public void onError(FirebaseError firebaseError) {
                    System.out.println("On Error authenticating newly created user. This could be an issue. ");
                    System.out.println(firebaseError.getMessage());

                }
            });

            System.out.println("End Creating User");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }

    }

    public void cambiarLogin(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
