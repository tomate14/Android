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

import ingenio.myapplication.MostrarBecas;
import ingenio.myapplication.RegisterActivity;

/**
 * Created by Maxi on 5/12/2017.
 */

public class FiltroService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public static final String RESPONSE_ACTION = "Respuesta del servidor";
    public static final String RESPONSE = "DATA RESPONSE";
    public static final String SERVICE_TYPE = "SERVICE_TYPE";
    final String BASE_URL = "http://ing.exa.unicen.edu.ar/ws/";
    static final String TAG = RegistroService.class.getCanonicalName();

    public FiltroService() {
        super( "FiltroServiceTest");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String operation = intent.getStringExtra(MostrarBecas.OPERACION);
        String ruta = intent.getStringExtra("ruta");
        Uri builtURI = Uri.parse(BASE_URL + ruta).buildUpon().build();
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            URL requestURL = new URL(builtURI.toString());
            conn = (HttpURLConnection) requestURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int responseCode1 = conn.getResponseCode();
            is = conn.getInputStream();
            String contentAsString1 = convertIsToString(is);
            Log.d(TAG, contentAsString1);
            Intent response1 = new Intent(RESPONSE_ACTION);
            response1.putExtra(MostrarBecas.OPERACION, operation);
            response1.putExtra(RESPONSE, contentAsString1);
            LocalBroadcastManager.getInstance(this).sendBroadcast(response1);

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
