package funcionalidad;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

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
        try {

            URL requestURL = new URL(builtURI.toString());
            conn = (HttpURLConnection) requestURL.openConnection();

            switch (operation) {
                case "registro":
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    JSONObject registro = new JSONObject();
                    registro.put("nombre",intent.getStringExtra("nombre"));
                    registro.put("apellido",intent.getStringExtra("apellido"));
                    registro.put("direccion",intent.getStringExtra("direccion"));
                    registro.put("email",intent.getStringExtra("email"));
                    registro.put("password",intent.getStringExtra("password"));
                    registro.put("ciudad",intent.getIntExtra("ciudad",-1));
                    registro.put("tipo",intent.getIntExtra("tipo",-1));
                    registro.put("orientacion",intent.getIntExtra("orientacion",-1));
                    java.util.Calendar cal = java.util.Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                    cal.setTime(sdf.parse(intent.getStringExtra("fecha")));
                    registro.put("fecha_nacimiento",cal.getTimeInMillis());

                    Log.d(TAG,registro.toString(1));
                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(registro.toString());
                    writer.flush();
                    writer.close();
                    os.close();

                    conn.connect();

                    int responseCode = conn.getResponseCode();
                    Log.d(TAG,Integer.toString(responseCode));
                    is = conn.getInputStream();
                    String contentAsString = convertIsToString(is);
                    Log.d(TAG, contentAsString);
                    Intent response = new Intent(RESPONSE_ACTION);
                    response.putExtra(RegisterActivity.OPERACION, operation);
                    response.putExtra(RESPONSE, contentAsString);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(response);
                    break;

                case "edicion" :
                    break;

                default:
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();

                    int responseCode1 = conn.getResponseCode();
                    is = conn.getInputStream();
                    String contentAsString1 = convertIsToString(is);
                    Log.d(TAG, contentAsString1);
                    Intent response1 = new Intent(RESPONSE_ACTION);
                    response1.putExtra(OPERACION, operation);
                    response1.putExtra(RESPONSE, contentAsString1);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(response1);
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
}
