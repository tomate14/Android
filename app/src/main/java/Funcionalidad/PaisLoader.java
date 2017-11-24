package Funcionalidad;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import entity.Pais;

/**
 * Created by Maxi on 23/11/2017.
 */

public class PaisLoader extends AsyncTaskLoader<ArrayList<Pais>> {

    private static final String TAG = "ADP_AppListLoader";
    private static final boolean DEBUG = true;
    //f/inal PackageManager mPm;
    private ArrayList<Pais> mApps;

    public PaisLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<Pais> loadInBackground() {
        Uri builtURI = Uri.parse("http://ing.exa.unicen.edu.ar/paises").buildUpon().build();
        InputStream is = null;
        HttpURLConnection conn = null;
        String contentAsString = null;
        ArrayList<Pais> paises = new ArrayList<>();
        try {
            URL requestURL = new URL(builtURI.toString());

            conn = (HttpURLConnection) requestURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int responseCode = conn.getResponseCode();

            is = conn.getInputStream();
            contentAsString = convertIsToString(is);
            Log.d(TAG, contentAsString);

            JSONArray jsonArray = new JSONArray(contentAsString);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject json = jsonArray.getJSONObject(i);
                Pais p1 = new Pais(json.getInt("idPais"),json.getString("nombre_pais"),json.getString("codigo"));
                paises.add(p1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(paises.size()>0){
            return paises;
        }
        return null;
    }

    public String convertIsToString(InputStream stream)
            throws IOException, UnsupportedEncodingException {


        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        BufferedReader buffer = new BufferedReader(reader);
        String line = buffer.readLine();
        line = buffer.readLine();
        return line;
    }

    @Override
    public void deliverResult(ArrayList<Pais> apps) {
        if (isStarted()) {
            super.deliverResult(apps);
        }
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

}
