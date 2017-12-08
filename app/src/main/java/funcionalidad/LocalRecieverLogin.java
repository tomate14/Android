package funcionalidad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import org.json.JSONException;
import org.json.JSONObject;

import entity.Usuario;
import ingenio.myapplication.LoginActivity;

/**
 * Created by Flia. Ferreira on 05/12/2017.
 */

public class LocalRecieverLogin extends BroadcastReceiver{

    private LoginActivity loginActivity;

    public LocalRecieverLogin(LoginActivity loginActivity){
        this.loginActivity = loginActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String jsonString= intent.getStringExtra(RegistroService.RESPONSE);

        try {
            JSONObject json = new JSONObject(jsonString);
            String respuesta = json.getString("status");
            String usuario = json.getString("idUsuario");
            String email = json.getString("email");
            if(respuesta.equals("200")){
                loginActivity.openMenu(new Usuario(Integer.valueOf(usuario),email));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
