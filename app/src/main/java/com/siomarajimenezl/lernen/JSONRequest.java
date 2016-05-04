package com.siomarajimenezl.lernen;

/**
 * Created by siomarajimenezl on 5/3/16.
 */


        import android.os.AsyncTask;

        import org.json.JSONArray;

        import java.io.BufferedReader;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.URL;
        import java.net.URLConnection;


public class JSONRequest extends AsyncTask<String, Void, JSONArray> {

    private JSONCallback callback;

    public JSONRequest(JSONCallback callback){
        this.callback = callback;
    }


    @Override
    protected JSONArray doInBackground(String... params) {

        URLConnection connection = null;
        BufferedReader br = null;
        JSONArray result = null;

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

            result = new JSONArray(builder.toString());

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
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
        callback.requestComplete(jsonArray);
    }


    public interface JSONCallback{

        void requestComplete(JSONArray array);
    }
}