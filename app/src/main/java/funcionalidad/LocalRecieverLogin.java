package funcionalidad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

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



    }
}
