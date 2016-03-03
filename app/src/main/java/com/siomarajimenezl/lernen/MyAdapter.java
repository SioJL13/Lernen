package com.siomarajimenezl.lernen;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alejandro on 2/19/16.
 */
public class MyAdapter extends BaseAdapter {

    // container we need to adapt
    ArrayList<Tutors> tutors;

    // to attach interface we need a reference to an activity
    Activity activity;

    public MyAdapter(ArrayList<Tutors> tutors, Activity activity){

        this.tutors = tutors;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return tutors.size();
    }

    @Override
    public Object getItem(int position) {
        return tutors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){

            convertView = activity.getLayoutInflater().inflate(R.layout.row, null);
        }

        TextView nombre = (TextView) convertView.findViewById(R.id.NombreTutor);
        TextView curso = (TextView) convertView.findViewById(R.id.Curso);
        TextView escolaridad = (TextView) convertView.findViewById(R.id.Escolaridad);
        //ImageView imagen = (ImageView) convertView.findViewById(R.id.imageView);


        nombre.setText(tutors.get(position).getNombre());
        curso.setText(tutors.get(position).getCourse());
        escolaridad.setText(tutors.get(position).getDegree());

        return convertView;
    }

}
