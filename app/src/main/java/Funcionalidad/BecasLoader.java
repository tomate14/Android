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

import javax.net.ssl.HttpsURLConnection;

import entity.Beca;
import ingenio.myapplication.MenuPrincipal;

/**
 * Created by Maxi on 23/11/2017.
 */

public class BecasLoader extends AsyncTaskLoader<ArrayList<Beca>> {

    private static final String TAG = "ADP_AppListLoader";
    private static final String BASE_URL = "http://ing.exa.unicen.edu.ar/becas";
    private static final boolean DEBUG = true;
    private ArrayList<Beca> mApps;
    private int seleccion_usuario;
    private String params;

    public BecasLoader(Context context, int seleccion_usuario, String params) {
        super(context);
        this.seleccion_usuario = seleccion_usuario;
        this.params            = params;
    }

    @Override
    public ArrayList<Beca> loadInBackground() {

        Uri builtURI = Uri.parse(BASE_URL + params).buildUpon().build();
        InputStream is = null;
        HttpURLConnection conn = null;
        String contentAsString = null;
        ArrayList<Beca> becas = new ArrayList<>();
        try {
            URL requestURL = new URL(builtURI.toString());
            conn = (HttpURLConnection) requestURL.openConnection();

            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                contentAsString = convertIsToString(is);
                Log.d(TAG, contentAsString);
                JSONArray jsonArray = new JSONArray(contentAsString);
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject json = jsonArray.getJSONObject(i);
                    Beca p1 = new Beca(json.getInt("idBeca"),
                            json.getString("nombre"),
                            json.getString("descripcion"),
                            "25487956655",
                            null,
                            json.getString("pagina_web"),
                            null,
                            null,
                            json.getString("idTipoBeca"),
                            json.getString("idTipoEstudiante")
                    );
                    becas.add(p1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(becas.size()>0){
            return becas;
        }
        return null;
    }

    public String convertIsToString(InputStream stream)
            throws IOException, UnsupportedEncodingException {

        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        BufferedReader buffer = new BufferedReader(reader);
        String line = buffer.readLine();
        //line = buffer.readLine();
        return line;
    }

    @Override
    public void deliverResult(ArrayList<Beca> apps) {
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

    private void setPorAccion(HttpURLConnection conn){
        switch (seleccion_usuario){
            case MenuPrincipal.ID_VERBECAS:

                break;
            case MenuPrincipal.ID_VERSUGERENCIAS:

                break;
            case MenuPrincipal.ID_VERBECASINTERES:
        }
    }
}
