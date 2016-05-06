package com.siomarajimenezl.lernen;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.fasterxml.jackson.core.JsonParser;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

public class MostarCitasActivity extends BaseActivity implements JSONObjectRequest.JSONObjectCallback {

    //Drawer
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    //Citas
    private ListView listaCita;
    private CitaAdapter citaAdapter;

    //Firebase
    private Firebase ref;
    private AuthData authData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostar_citas);

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load titles from strings.xml

        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);//load icons from strings.xml

        set(navMenuTitles, navMenuIcons);

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://vivid-heat-5652.firebaseio.com/Cliente");

        authData = ref.getAuth();

        changeJSON();

    }

    public void changeJSON(){
        new JSONObjectRequest(this).execute("https://vivid-heat-5652.firebaseio.com/Cliente/" + authData.getUid() + "/Citas.json");
    }


    @Override
    public void requestComplete(JSONObject object) {

        for(int i = 0; i < object.length() - 1; i++){
            try {
                Log.d("CHECK", object.getString(String.valueOf(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
