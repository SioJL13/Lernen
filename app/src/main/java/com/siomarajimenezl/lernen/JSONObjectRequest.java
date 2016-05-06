package com.siomarajimenezl.lernen;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Alejandro on 5/6/16.
 */
public class JSONObjectRequest extends AsyncTask <String, Void, JSONObject> {

    private JSONObjectCallback callback;

    public JSONObjectRequest(JSONObjectCallback callback){
        this.callback = callback;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        URLConnection connection = null;
        BufferedReader br = null;
        JSONObject result = null;

        try{
            URL url = new URL(params[0]);
            connection = (URLConnection) url.openConnection();


            InputStream is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            String line = "";

            while((line = br.readLine()) != null){

                builder.append(line);
            }

            result = new JSONObject(builder.toString());

        }catch (Exception e) {

            e.printStackTrace();
        } finally {

            try{

                if(br != null) br.close();

            }catch(Exception e) {

                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        callback.requestComplete(jsonObject);
    }

    public interface JSONObjectCallback{

        void requestComplete(JSONObject object);
    }
}
