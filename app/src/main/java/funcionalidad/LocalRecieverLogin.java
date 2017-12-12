package funcionalidad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

import entity.Usuario;
import ingenio.myapplication.LoginActivity;
import ingenio.myapplication.RegisterActivity;

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

            if(intent.getStringExtra(RegisterActivity.OPERACION).equals("login")) {
                JSONObject json = new JSONObject(jsonString);
                String respuesta = json.getString("status");
                if (respuesta.equals("200")) {
                    int usuario = Integer.valueOf(json.getString("idUsuario"));
                    String email = json.getString("email");
                    String password = json.getString("password");
                    String nombre = json.getString("nombre");
                    String apellido = json.getString("apellido");
                    int ciudad = Integer.valueOf(json.getString("idCiudad"));
                    String direccion = json.getString("direccion");
                    Calendar fecha = java.util.Calendar.getInstance();

                    fecha.setTime(new Date((json.getLong("fecha_nacimiento")*1000))); // el servidor me un long con digitos faltantes por lo que necesito correr el lumero 3 lugares a la izquierda.
                    int tipoEstudiante = -1;
                    if(!json.getString("idTipoEstudiante").equals("null"))
                        tipoEstudiante = Integer.valueOf(json.getString("idTipoEstudiante"));
                    int orientacion = -1;
                    if(!json.getString("idTipoBeca").equals("null"))
                        orientacion = Integer.valueOf(json.getString("idTipoBeca"));
                    loginActivity.openMenu(new Usuario(usuario, email, nombre, apellido, fecha, password, ciudad, direccion, tipoEstudiante, orientacion));
                } else {
                    String mensaje = json.getString("mensaje");
                    loginActivity.loginError(mensaje);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
