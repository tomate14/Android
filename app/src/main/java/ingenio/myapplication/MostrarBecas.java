package ingenio.myapplication;


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Vector;

import Adapters.ListViewExtended;
import Adapters.ListViewSubscripciones;
import Adapters.ListViewVerBecas;
import Funcionalidad.Servicios;
import entity.Beca;

public class MostrarBecas extends AppCompatActivity {

    private ExpandableListView listView;
    private ListViewExtended mostrarInfo = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_becas);
        setTitle(getString(R.string.activity_verbecas));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*********************************************
          CREAR ADAPTADOR PARA MOSTRAR LA INFORMACION
         *********************************************/
        Intent intent = getIntent();
        int accion = intent.getIntExtra("listview",0);
        ArrayList<Beca> becas;
        switch (accion){
            case MenuPrincipal.ID_VERBECAS:
                //Armar un vector de becas
                becas = new Servicios().getBecasAll();
                mostrarInfo= new ListViewVerBecas(this,becas);
                break;
            case MenuPrincipal.ID_VERSUGERENCIAS:
                becas = new Servicios().getBecasSugeridas();
                mostrarInfo = new ListViewVerBecas(this,becas);
                break;
            case MenuPrincipal.ID_VERBECASINTERES:
                becas = new Servicios().getSubscripciones();
                mostrarInfo = new ListViewSubscripciones(this,becas);
        }

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