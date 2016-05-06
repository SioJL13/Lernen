package com.siomarajimenezl.lernen;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

//TODO: CAMBIAR TODAS LAS IMAGES

public class MainActivity extends BaseActivity implements JSONRequest.JSONCallback,AdapterView.OnItemClickListener {

    private ListView listaMain;
    MyAdapter myAdapter;

    //Drawer
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load titles from strings.xml

        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);//load icons from strings.xml

        set(navMenuTitles,navMenuIcons);
        changeJSON();

        this.listaMain = (ListView)findViewById(R.id.listViewMain);
        this.listaMain.setOnItemClickListener(this);


    }

    public void changeJSON(){
        new JSONRequest(this).execute("https://raw.githubusercontent.com/SioJL13/Lernen/master/tutors.json");
    }

    @Override
    public void requestComplete(JSONArray array) {
        myAdapter = new MyAdapter(array,MainActivity.this);
        this.listaMain.setAdapter(myAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        JSONObject jsonObject = (JSONObject)myAdapter.getItem(position);

        Intent i = new Intent(getApplicationContext(), PerfilTutoresActivity.class);
        String pos_json = jsonObject.toString();
        i.putExtra("pos_json",pos_json);

        startActivity(i);
    }
}
