package funcionalidad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

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

                break;
        }

    }
}
