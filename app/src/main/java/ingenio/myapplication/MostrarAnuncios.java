package ingenio.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;

import adapters.ListViewNotificaciones;
import adapters.ListViewExtended;
import entity.Notificacion;
import funcionalidad.Servicios;

public class MostrarAnuncios extends AppCompatActivity {

    private ListViewExtended mostrarInfo = null;
    private ExpandableListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_anuncios);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Notificacion> anuncios = new Servicios().getAnunciosNotificaciones();
        mostrarInfo= new ListViewNotificaciones(this,anuncios);

        this.listView = (ExpandableListView) findViewById(R.id.listView);
        this.listView.setAdapter(mostrarInfo);
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
}
