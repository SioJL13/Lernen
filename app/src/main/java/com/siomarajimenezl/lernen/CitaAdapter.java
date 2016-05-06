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
 * Created by Alejandro on 5/5/16.
 */
public class CitaAdapter extends BaseAdapter {
    // container we need to adapt
    private JSONObject citas;

    // to attach interface we need a reference to an activity
    Activity activity;

    public CitaAdapter(JSONObject citas, Activity activity){

        this.citas = citas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        if(citas ==null){
            return 0;
        }else{
            return citas.length();
        }
    }

    @Override
    public JSONObject getItem(int position) {
        if(citas ==null){
            return null;
        }else{
            return citas.optJSONObject(String.valueOf(position));
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

            convertView = activity.getLayoutInflater().inflate(R.layout.row2, null);
        }

        TextView tutor = (TextView) convertView.findViewById(R.id.textView);
        TextView curso = (TextView) convertView.findViewById(R.id.textView3);
        TextView hora = (TextView) convertView.findViewById(R.id.textView4);
        TextView lugar = (TextView) convertView.findViewById(R.id.textView5);
        TextView fecha = (TextView) convertView.findViewById(R.id.textView6);


        final JSONObject json_data = getItem(position);

        if(json_data != null){
            try{
                String maestro = json_data.getString("Tutor");
                String materia = json_data.getString("Curso");
                String tiempo = json_data.getString("Hora");
                String locacion = json_data.getString("Lugar");
                String calendario = json_data.getString("Fecha");

                tutor.setText(maestro);
                curso.setText(materia);
                hora.setText(tiempo);
                lugar.setText(locacion);
                fecha.setText(calendario);

            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        return convertView;
    }
}
