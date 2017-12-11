package funcionalidad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import entity.Anuncio;
import entity.Notificacion;
import ingenio.myapplication.MostrarAnuncios;

/**
 * Created by Maxi on 11/12/2017.
 */

public class LocalRecieverNotificaciones extends BroadcastReceiver {
    private MostrarAnuncios mostrarAnunciosActivity;
    public static final String OPERACION = "OPERATION_SERVICE";

    public LocalRecieverNotificaciones(MostrarAnuncios mostrarAnunciosActivity) {
        this.mostrarAnunciosActivity = mostrarAnunciosActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String operation = intent.getStringExtra(OPERACION);
        switch (operation){
            case "vernotificaciones":
                try {
                    JSONArray jsonArray = new JSONArray(intent.getStringExtra(RegistroService.RESPONSE));
                    ArrayList<Notificacion> notificaciones = new ArrayList<>();
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        Date fecha = new Date(json.getLong("fecha"));
                        String jsonBanner = json.getString("banner");

                        Notificacion notificacion = new Notificacion(
                                json.getInt("idAnuncio"),
                                json.getString("informacion"),
                                json.getString("nombreEntidad"),
                                json.getString("titulo"),
                                fecha,
                                json.getString("web"),
                                json.getInt("idBeca"),
                                json.getString("telefono"),
                                getBitmapFromString(jsonBanner)
                        );
                        notificaciones.add(notificacion);
                    }
                    mostrarAnunciosActivity.setNotificaciones(notificaciones);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
        }
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
