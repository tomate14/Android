package funcionalidad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;

import entity.TipoBeca;
import entity.TipoEstudiante;
import ingenio.myapplication.MostrarBecas;
import ingenio.myapplication.RegisterActivity;

/**
 * Created by Maxi on 5/12/2017.
 */

public class LocalRecieverFiltro extends BroadcastReceiver {
    MostrarBecas mostrarBecasActivity;
    public static final String OPERACION = "OPERATION_SERVICE";

    public LocalRecieverFiltro(MostrarBecas mostrarBecasActivity) {
        this.mostrarBecasActivity = mostrarBecasActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String operation = intent.getStringExtra(OPERACION);
        switch (operation) {
            case "tiposestudiantes":
                try {
                    JSONArray jsonArray = new JSONArray(intent.getStringExtra(RegistroService.RESPONSE));
                    ArrayList<TipoEstudiante> tipoEstudiantes = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++){

                        JSONObject json = jsonArray.getJSONObject(i);

                        Integer id = json.getInt("idTipoEstudiante");
                        String nombre = json.getString("nombre_tipo");
                        TipoEstudiante tipo = new TipoEstudiante(id,nombre);

                        tipoEstudiantes.add(tipo);
                    }
                    mostrarBecasActivity.setTipoEstudiantes(tipoEstudiantes);
                    //mostrarBecasActivity.ShowSearch();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "tiposbecas":
                try {
                    JSONArray jsonArray = new JSONArray(intent.getStringExtra(RegistroService.RESPONSE));
                    ArrayList<TipoBeca> tiposbecas = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++){

                        JSONObject json = jsonArray.getJSONObject(i);

                        Integer id = json.getInt("idTipoBeca");
                        String nombre = json.getString("nombre_beca");
                        TipoBeca tipo = new TipoBeca(id,nombre);

                        tiposbecas.add(tipo);
                    }
                    mostrarBecasActivity.setTipoBecas(tiposbecas);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
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
                    mostrarBecasActivity.setPaises(paises);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}
