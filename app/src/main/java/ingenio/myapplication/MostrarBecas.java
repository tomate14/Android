package ingenio.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;

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
                setTitle(getString(R.string.activity_verbecas_buscar));
                becas = new Servicios().getBecasAll();
                mostrarInfo= new ListViewVerBecas(this,becas);
                break;
            case MenuPrincipal.ID_VERSUGERENCIAS:
                setTitle(getString(R.string.activity_verbecas_sugeridas));
                becas = new Servicios().getBecasSugeridas();
                mostrarInfo = new ListViewVerBecas(this,becas);
                break;
            case MenuPrincipal.ID_VERBECASINTERES:
                setTitle(getString(R.string.activity_verbecas_interes));
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
            case R.id.action_search:
                openSearch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSearch() {
        Intent filtro = new Intent(MostrarBecas.this,FiltroBecasActivity.class);
        startActivity(filtro);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Intent intent = getIntent();
        int accion = intent.getIntExtra("listview",0);
        MenuItem register = menu.findItem(R.id.action_search);
        if (accion == MenuPrincipal.ID_VERBECAS){
            register.setVisible(true);
        }

        return true;
    }
}