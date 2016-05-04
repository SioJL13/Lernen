package com.siomarajimenezl.lernen;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by Alejandro on 2/19/16.
 */
public class MyAdapter extends BaseAdapter {

    // container we need to adapt
    private JSONArray tutors;

    // to attach interface we need a reference to an activity
    Activity activity;

    public MyAdapter(JSONArray tutors, Activity activity){

        this.tutors = tutors;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        if(tutors==null){
            return 0;
        }else{
            return tutors.length();
        }
    }

    @Override
    public JSONObject getItem(int position) {
        if(tutors==null){
            return null;
        }else{
            return tutors.optJSONObject(position);
        }
    }

    @Override
    public long getItemId(int position) {
        JSONObject object = getItem(position);
        return object.optLong("id");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = activity.getLayoutInflater().inflate(R.layout.row, null);
        }

        TextView nombre = (TextView) convertView.findViewById(R.id.NombreTutor);
        TextView materia = (TextView) convertView.findViewById(R.id.Curso);
        TextView escolaridad = (TextView) convertView.findViewById(R.id.Escolaridad);
        ImageView imagen = (ImageView) convertView.findViewById(R.id.imageView);


        final JSONObject json_data = getItem(position);
        if(json_data != null){
            try{
                String name = json_data.getString("nombre");
                String curso = json_data.getString("materia");
                String escuela = json_data.getString("escolaridad");
                String foto = json_data.getString("foto");

                nombre.setText(name);
                materia.setText(curso);
                escolaridad.setText(escuela);
                Picasso.with(convertView.getContext())
                        .load(foto)
                        .into(imagen);

            }catch (JSONException e){
                e.printStackTrace();
            }
        }



        return convertView;
    }

}
