package com.siomarajimenezl.lernen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.annotations.NotNull;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private Firebase ref;
    final int ERROR_PASSWORD = -16;
    Button loginButton;
    ProgressDialog progress;
    TextInputLayout emailWrapper, passwordWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailWrapper = (TextInputLayout)findViewById(R.id.usuarioWrapper);
        passwordWrapper = (TextInputLayout)findViewById(R.id.passwordWrapper);

        emailWrapper.setHint("Email");
        passwordWrapper.setHint("Password");


        Firebase.setAndroidContext(this);
        ref = new Firebase("https://vivid-heat-5652.firebaseIO.com");

        loginButton = (Button) findViewById(R.id.botonLogin);

    }

    public void cambiarMainActivity(View v){
        progress = ProgressDialog.show(this, "Autenticando datos", "Iniciando sesion", true);
        String email = emailWrapper.getEditText().getText().toString();
        String password = passwordWrapper.getEditText().getText().toString();
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailWrapper.setError("Ingresa un correo valido");
            progress.dismiss();
        }else if(password.isEmpty() || password.length()<4){
            passwordWrapper.setError("Ingresa un password valido");
            progress.dismiss();
        }else{
            emailWrapper.setErrorEnabled(false);
            passwordWrapper.setErrorEnabled(false);
            Log.d("Pass", password);
            Log.d("Email",email);
        }

        ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {

            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                Intent intent = new Intent(getApplicationContext(),MainActivity.class).putExtra("UID", authData.getUid());
                startActivity(intent);
                progress.dismiss();

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                progress.dismiss();
                if(firebaseError.getCode() == ERROR_PASSWORD){
                    passwordWrapper.setError("Password incorrecto");
                    Log.e("LOGIN", "ERROR!!!!" + firebaseError.getMessage());
                }else{
                    emailWrapper.setError("El correo no existe");
                    Log.e("LOGIN", "ERROR!!!!" + firebaseError.getMessage());
                }

            }
        });

    }

    public void cambiarRegistrarActivity(View v){
        Intent intent = new Intent(this, RegistrarActivity.class);
        startActivity(intent);
    }


}
