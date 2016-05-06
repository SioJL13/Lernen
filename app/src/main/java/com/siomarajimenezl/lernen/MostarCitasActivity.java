package com.siomarajimenezl.lernen;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import org.json.JSONArray;

public class MostarCitasActivity extends BaseActivity implements JSONRequest.JSONCallback {

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

        /*this.listaCita = (ListView)findViewById(R.id.listView2);*/


    }

    public void changeJSON(){
        new JSONRequest(this).execute("https://vivid-heat-5652.firebaseio.com/Cliente/" + authData.getUid() + "/Citas.json");
    }

    @Override
    public void requestComplete(JSONArray array) {

        Log.d("Size", String.valueOf(array.length()));
        /*citaAdapter = new CitaAdapter(array,MostarCitasActivity.this);
        this.listaCita.setAdapter(citaAdapter);*/

    }

}
