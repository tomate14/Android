package ingenio.myapplication;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;


import java.util.ArrayList;

import Adapters.ListViewExtended;
import Adapters.ListViewVerBecas;
import Funcionalidad.PaisLoader;
import Funcionalidad.Servicios;
import entity.Beca;
import entity.Pais;

public class MostrarBecas extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<ArrayList<Pais>> {

    private Context contexto;
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
        int accion = intent.getIntExtra("listview", 0);
        ArrayList<Beca> becas;
        this.contexto = this;

        switch (accion) {
            case MenuPrincipal.ID_VERBECAS:
                //Armar un vector de becas
                setTitle(getString(R.string.activity_verbecas_buscar));
                becas = new Servicios().getBecasAll();
                getLoaderManager().initLoader(0,null,MostrarBecas.this);
                mostrarInfo = new ListViewVerBecas(this, becas);
                break;
            case MenuPrincipal.ID_VERSUGERENCIAS:
                setTitle(getString(R.string.activity_verbecas_sugeridas));
                becas = new Servicios().getBecasSugeridas();
                mostrarInfo = new ListViewVerBecas(this, becas);
                break;
            case MenuPrincipal.ID_VERBECASINTERES:
                setTitle(getString(R.string.activity_verbecas_interes));
                becas = new Servicios().getSubscripciones();
                mostrarInfo = new ListViewVerBecas(this, becas);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.activity_filtro_becas, null));
        AlertDialog ad = builder.create();
        ad.setTitle("Buscar");
        ad.setButton(AlertDialog.BUTTON_POSITIVE, "Buscar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Buscar y cerrar el popup
                    }
                });

        ad.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        ad.show();
        //Intent filtro = new Intent(MostrarBecas.this,FiltroBecasActivity.class);
        //startActivity(filtro);
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
        int accion = intent.getIntExtra("listview", 0);
        MenuItem register = menu.findItem(R.id.action_search);
        if (accion == MenuPrincipal.ID_VERBECAS) {
            register.setVisible(true);
        }

        return true;
    }


    @Override
    public Loader<ArrayList<Pais>> onCreateLoader(int id, Bundle args) {
        return new PaisLoader(contexto);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Pais>> loader, ArrayList<Pais> data) {
        Log.d("","ENTRANDO AL FINISH LOADER"+data.size());
        for(int i = 0; i < data.size(); i++){
            Log.d("RESULTADO= ",data.get(i).toString());
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Pais>> loader) {

    }
}