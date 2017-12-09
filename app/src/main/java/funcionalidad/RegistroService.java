package funcionalidad;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ingenio.myapplication.MostrarBecas;
import ingenio.myapplication.RegisterActivity;

/**
 * Created by Flia. Ferreira on 24/11/2017.
 */

public class RegistroService extends IntentService {

    public static final String RESPONSE_ACTION = "Respuesta del servidor";
    public static final String RESPONSE = "DATA RESPONSE";
    public static final String SERVICE_TYPE = "SERVICE_TYPE";
    private static final String OPERACION = "OPERATION_SERVICE";
    final String BASE_URL = "http://ing.exa.unicen.edu.ar/ws/";
    static final String TAG = RegistroService.class.getCanonicalName();


    public RegistroService() {
        super("RegistroServicesTest");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String operation = intent.getStringExtra(OPERACION);
        String ruta = intent.getStringExtra("ruta");
        Uri builtURI = Uri.parse(BASE_URL + ruta).buildUpon().build();
        InputStream is = null;
        HttpURLConnection conn = null;
        Intent response;
        String contentAsString;
        int responseCode = -1;
        java.util.Calendar cal = java.util.Calendar.getInstance();
        SimpleDateFormat sdf = null;
        try {

            URL requestURL = new URL(builtURI.toString());
            conn = (HttpURLConnection) requestURL.openConnection();
            List<NameValuePair> params = new ArrayList<>();


            switch (operation) {
                case "registro":
                    params.clear();
                    params.add(new BasicNameValuePair("email",intent.getStringExtra("email")));
                    params.add(new BasicNameValuePair("password",intent.getStringExtra("password")));
                    params.add(new BasicNameValuePair("nombre",intent.getStringExtra("nombre")));
                    params.add(new BasicNameValuePair("apellido",intent.getStringExtra("apellido")));
                    params.add(new BasicNameValuePair("ciudad",Integer.toString(intent.getIntExtra("ciudad",-1))));
                    sdf = new SimpleDateFormat("dd/MM/yy");
                    cal.setTime(sdf.parse(intent.getStringExtra("fecha")));
                    params.add(new BasicNameValuePair("fecha_nacimiento",Long.toString(cal.getTimeInMillis())));
                    params.add(new BasicNameValuePair("direccion",intent.getStringExtra("direccion")));

                    Log.d(TAG,params.toString());

                    response = new Intent(RESPONSE_ACTION);
                    response.putExtra(RegisterActivity.OPERACION, operation);
                    response.putExtra(RESPONSE, this.post(BASE_URL + ruta,params));
                    LocalBroadcastManager.getInstance(this).sendBroadcast(response);
                    break;

                case "edicion" :
                    params.clear();
                    params.add(new BasicNameValuePair("email",intent.getStringExtra("email")));
                    params.add(new BasicNameValuePair("password",intent.getStringExtra("password")));
                    params.add(new BasicNameValuePair("passwordNew",intent.getStringExtra("passwordNew")));
                    params.add(new BasicNameValuePair("nombre",intent.getStringExtra("nombre")));
                    params.add(new BasicNameValuePair("apellido",intent.getStringExtra("apellido")));
                    params.add(new BasicNameValuePair("ciudad",Integer.toString(intent.getIntExtra("ciudad",-1))));
                    sdf = new SimpleDateFormat("dd/MM/yy");
                    cal.setTime(sdf.parse(intent.getStringExtra("fecha")));
                    params.add(new BasicNameValuePair("fecha_nacimiento",Long.toString(cal.getTimeInMillis())));
                    params.add(new BasicNameValuePair("direccion",intent.getStringExtra("direccion")));

                    Log.d(TAG,params.toString());

                    response = new Intent(RESPONSE_ACTION);
                    response.putExtra(RegisterActivity.OPERACION, operation);
                    response.putExtra(RESPONSE, this.post(BASE_URL + ruta,params));
                    LocalBroadcastManager.getInstance(this).sendBroadcast(response);
                    break;

                case "login" :
                    params.clear();
                    params.add(new BasicNameValuePair("email",intent.getStringExtra("email")));
                    params.add(new BasicNameValuePair("password",intent.getStringExtra("password")));

                    response = new Intent(RESPONSE_ACTION);
                    response.putExtra(RegisterActivity.OPERACION, operation);
                    response.putExtra(RESPONSE, this.post(BASE_URL + ruta,params));
                    LocalBroadcastManager.getInstance(this).sendBroadcast(response);

                    break;
                case "filtrobecas" :

                    params.clear();
                    params.add(new BasicNameValuePair("idTipobeca",String.valueOf(intent.getIntExtra("idTipobeca",0))));
                    params.add(new BasicNameValuePair("idTipoEstudiante",String.valueOf(intent.getIntExtra("idTipoEstudiante",0))));
                    params.add(new BasicNameValuePair("idPais",String.valueOf(intent.getIntExtra("idPais",0))));
                    params.add(new BasicNameValuePair("ciudad",intent.getStringExtra("ciudad")));

                    Log.d(TAG,params.toString());

                    response = new Intent(RESPONSE_ACTION);
                    response.putExtra(MostrarBecas.OPERACION, operation);
                    response.putExtra(RESPONSE, this.post(BASE_URL + ruta,params));
                    LocalBroadcastManager.getInstance(this).sendBroadcast(response);

                    break;
                case "subscribir" :

                    params.clear();
                    params.add(new BasicNameValuePair("idUsuario",String.valueOf(intent.getIntExtra("idUsuario",0))));
                    params.add(new BasicNameValuePair("idBeca",String.valueOf(intent.getIntExtra("idBeca",0))));

                    Log.d(TAG,params.toString());

                    response = new Intent(RESPONSE_ACTION);
                    response.putExtra(MostrarBecas.OPERACION, operation);
                    response.putExtra(RESPONSE, this.post(BASE_URL + ruta,params));
                    LocalBroadcastManager.getInstance(this).sendBroadcast(response);

                    break;

                default:
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();

                    responseCode = conn.getResponseCode();
                    is = conn.getInputStream();
                    contentAsString = convertIsToString(is);
                    Log.d(TAG, contentAsString);

                    response = new Intent(RESPONSE_ACTION);
                    response.putExtra(OPERACION, operation);
                    response.putExtra(RESPONSE, contentAsString);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(response);
                    break;

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public String convertIsToString(InputStream stream)
            throws IOException, UnsupportedEncodingException {

        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        BufferedReader buffer = new BufferedReader(reader);
        String line = buffer.readLine();
        return line;
    }

    public String post(String posturl, List<NameValuePair> params){

        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(posturl);
                    //AÑADIR PARAMETROS

            httppost.setEntity(new UrlEncodedFormEntity(params));

                  //Finalmente ejecutamos enviando la info al server/
                    HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();/*y obtenemos una respuesta*/
            Log.d(TAG,resp.getEntity().toString() );

            String text = EntityUtils.toString(ent);
            Log.e(TAG,text);
            return text;

        }
        catch(Exception e) { return "error";}

    }
}
