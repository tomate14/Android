package funcionalidad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import entity.Anuncio;
import entity.TipoEstudiante;
import ingenio.myapplication.MenuPrincipal;

/**
 * Created by Maxi on 8/12/2017.
 */

public class LocalRecieverAnuncio  extends BroadcastReceiver {
    private MenuPrincipal menuPrincipalActivity;
    public static final String OPERACION = "OPERATION_SERVICE";

    public LocalRecieverAnuncio(MenuPrincipal menuPrincipal) {
        this.menuPrincipalActivity = menuPrincipal;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String operation = intent.getStringExtra(OPERACION);
        switch (operation){
            case "veranuncios":
                try {
                    JSONArray jsonArray = new JSONArray(intent.getStringExtra(RegistroService.RESPONSE));
                    ArrayList<Anuncio> anuncios = new ArrayList<>();
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        String jsonBanner = json.getString("banner");
                        Date fecha = new Date(json.getLong("fecha")*1000);

                        Anuncio anuncio = new Anuncio(
                                getBitmapFromString(jsonBanner),
                                json.getInt("idAnuncio"),
                                json.getString("informacion"),
                                fecha
                        );
                        anuncios.add(anuncio);
                    }
                    menuPrincipalActivity.setAnuncios(anuncios);
                    } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
        }

    }

    private Bitmap getBitmapFromString(String jsonString) {
        String pureBase64Encoded = jsonString.substring(jsonString.indexOf(",")  + 1);
        byte[] decodedString = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
}
