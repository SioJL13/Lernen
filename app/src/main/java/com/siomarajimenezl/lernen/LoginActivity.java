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

import java.util.regex.Pattern;
//TODO: Configurar el onclick para que valide la informacion!
public class LoginActivity extends AppCompatActivity {
    private Firebase ref;
    Button loginButton;
    ProgressDialog progress;

    EditText inputEmail, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextInputLayout emailWrapper = (TextInputLayout)findViewById(R.id.usuarioWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout)findViewById(R.id.passwordWrapper);

        emailWrapper.setHint("Email");
        passwordWrapper.setHint("Password");


        Firebase.setAndroidContext(this);
        ref = new Firebase("https://vivid-heat-5652.firebaseIO.com");

        inputEmail = (EditText)findViewById(R.id.inputEmail);
        inputPassword = (EditText)findViewById(R.id.inputPassword);
        loginButton = (Button) findViewById(R.id.botonLogin);


    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public boolean validatePassword(String password) {
        return password.length() > 5;
    }




    public void cambiarMainActivity(View v){

        progress = ProgressDialog.show(this, "Autenticando datos","Iniciando sesion",true);


        ref.authWithPassword(inputEmail.getText().toString(), inputPassword.getText().toString(), new Firebase.AuthResultHandler() {


            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                Intent intent = new Intent(getApplicationContext(),MainActivity.class).putExtra("UID", authData.getUid());
                startActivity(intent);
                progress.dismiss();

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.e("LOGIN", "ERROR!!!!" + firebaseError.getMessage());
            }
        });


    }

    public void cambiarRegistrarActivity(View v){
        Intent intent = new Intent(this, RegistrarActivity.class);
        startActivity(intent);
    }


}
