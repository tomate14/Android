package funcionalidad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

import ingenio.myapplication.RegisterActivity;

/**
 * Created by Flia. Ferreira on 24/11/2017.
 */

public class LocalReciever extends BroadcastReceiver {

    RegisterActivity registerActivity;

    public LocalReciever(RegisterActivity ra){
        this.registerActivity = ra;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String operation = intent.getStringExtra(RegisterActivity.OPERACION);
        switch (operation) {
            case "paises":

                try {

                    JSONArray jsonArray = new JSONArray(intent.getStringExtra(RegistroService.RESPONSE));

                    Hashtable<String,Integer> paises = new Hashtable<>();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject json = jsonArray.getJSONObject(i);

                        Integer id = json.getInt("idPais");
                        String pais = json.getString("nombre_pais");

                        paises.put(pais,id);
                    }
                    registerActivity.setPaises(paises);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "provincias":
                try{
                JSONArray jsonArray = new JSONArray(intent.getStringExtra(RegistroService.RESPONSE));

                Hashtable<String,Integer> provincias = new Hashtable<>();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject json = jsonArray.getJSONObject(i);

                    Integer id = json.getInt("idDepartamento");
                    String provincia = json.getString("nombre_departamento");

                    provincias.put(provincia,id);
                }
                registerActivity.setProvincias(provincias);
                } catch (Exception e){
                    e.printStackTrace();
                }
            case "ciudades":
                try{
                    JSONArray jsonArray = new JSONArray(intent.getStringExtra(RegistroService.RESPONSE));

                    Hashtable<String,Integer> ciudades = new Hashtable<>();

                    for (int i = 0; i < jsonArray.length(); i++){

                        JSONObject json = jsonArray.getJSONObject(i);

                        Integer id = json.getInt("idCiudad");
                        String ciudad = json.getString("nombre");

                        ciudades.put(ciudad,id);
                    }
                    registerActivity.setCiudades(ciudades);
                } catch (Exception e){
                    e.printStackTrace();
                }
            case "registro":

                try {
                    String jsonString= intent.getStringExtra(RegistroService.RESPONSE);

                    JSONObject json = new JSONObject(jsonString);
                    String respuesta = json.getString("respuesta");
                    String mensaje = json.getString("mensaje");

                    if(respuesta.equals("success")){
                        registerActivity.notificarRegistro();
                    }
                    else
                    {
                        registerActivity.notificarError();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


        }
    }
}
