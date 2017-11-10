package ingenio.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;

import Adapters.ListViewAnuncios;
import Adapters.ListViewExtended;
import Adapters.ListViewVerBecas;
import entity.Anuncio;
import Funcionalidad.Servicios;

public class MostrarAnuncios extends AppCompatActivity {

    private ListViewExtended mostrarInfo = null;
    private ExpandableListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_anuncios);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Anuncio> anuncios = new Servicios().getAnunciosNotificaciones();
        mostrarInfo= new ListViewAnuncios(this,anuncios);

        this.listView = (ExpandableListView) findViewById(R.id.listView);
        //this.listView.setAdapter();
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
