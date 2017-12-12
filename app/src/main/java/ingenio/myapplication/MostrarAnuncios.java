package ingenio.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;

import adapters.ListViewNotificaciones;
import adapters.ListViewExtended;
import entity.Notificacion;
import funcionalidad.LocalRecieverNotificaciones;
import funcionalidad.RegistroService;
import funcionalidad.Servicios;

public class MostrarAnuncios extends AppCompatActivity {

    private ListViewExtended mostrarInfo = null;
    private ExpandableListView listView;
    public static final String OPERACION = "OPERATION_SERVICE";
    public static final String OPERACION_VERNOTIFICACIONES = "notificaciones";

    private LocalRecieverNotificaciones reciever = new LocalRecieverNotificaciones(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_anuncios);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LocalBroadcastManager.getInstance(this).registerReceiver(reciever, new IntentFilter(RegistroService.RESPONSE_ACTION));
        final Intent mServiceIntent = new Intent(MostrarAnuncios.this, RegistroService.class);

        this.listView = (ExpandableListView) findViewById(R.id.listView);

        mServiceIntent.putExtra(OPERACION, "vernotificaciones");
        mServiceIntent.putExtra("ruta", OPERACION_VERNOTIFICACIONES+"/"+MenuPrincipal.user.getIdusuario());
        startService(mServiceIntent);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setNotificaciones(ArrayList<Notificacion> notificaciones) {
        ArrayList<Notificacion> notificacions = notificaciones;
        mostrarInfo= new ListViewNotificaciones(this,notificacions);
        this.listView.setAdapter(mostrarInfo);
    }
}