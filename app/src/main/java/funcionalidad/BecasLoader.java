package funcionalidad;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import entity.Beca;
import entity.TipoBeca;
import entity.TipoEstudiante;
import ingenio.myapplication.MenuPrincipal;
import ingenio.myapplication.MostrarBecas;

/**
 * Created by Maxi on 23/11/2017.
 */

public class BecasLoader extends AsyncTaskLoader<ArrayList<Beca>> {

    private static final String TAG = "ADP_AppListLoader";
    private static final String BASE_URL = "http://ing.exa.unicen.edu.ar/ws";
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
                    Date fecha_inicio = new Date(json.getLong("fecha_ini_inscripcion")*1000);
                    Date fecha_fin = new Date(json.getLong("fecha_fin_inscripcion")*1000);
                    String jsonBanner = json.getString("banner");
                    String sus ="";
                    if(MenuPrincipal.ID_VERBECASINTERES == seleccion_usuario)
                        sus="1";
                    else
                        sus=json.getString("estado");
                    Boolean val = false;
                    if(sus.equals("1"))
                        val=true;
                    Beca p1 = new Beca(json.getInt("idBeca"),
                            json.getString("nombre"),
                            json.getString("descripcion"),
                            json.getString("telefono"),
                            getBitmapFromString(jsonBanner),
                            json.getString("pagina_web"),
                            fecha_fin,
                            fecha_inicio,
                            new TipoBeca(json.getInt("idTipoBeca"),json.getString("nombre_beca")),
                            new TipoEstudiante(json.getInt("idTipoEstudiante"),json.getString("nombre_tipo")),
                            val
                    );
                    becas.add(p1);
                    becas.toString();
                }
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(becas.size()>0){
            return becas;
        }
        return becas;
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

    @Nullable
    private Bitmap getBitmapFromString(String jsonString) {
        if(jsonString.equals("")) {
            return null;
        }
        String pureBase64Encoded = jsonString.substring(jsonString.indexOf(",") + 1);
        byte[] decodedString = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
}
