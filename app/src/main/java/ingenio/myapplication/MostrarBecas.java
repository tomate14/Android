package ingenio.myapplication;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import adapters.ListViewExtended;
import adapters.ListViewVerBecas;
import entity.TipoBeca;
import entity.TipoEstudiante;
import funcionalidad.BecasLoader;
import entity.Beca;
import funcionalidad.FiltroService;
import funcionalidad.LocalRecieverFiltro;
import funcionalidad.RegistroService;

public class MostrarBecas extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<ArrayList<Beca>> {

    public static final String OPERACION = "OPERATION_SERVICE";
    private static final String TAG = "MOSTRARBECAS_ACTIVITY";

    private ArrayList<Beca> becas;
    private ArrayList<TipoEstudiante> tipoEstudiantes;
    private ArrayList<TipoBeca> tipoBecas;
    private Hashtable<String, Integer> paises;

    private Context contexto;
    private ExpandableListView listView;
    private ListViewExtended mostrarInfo = null;
    private Spinner spinnerPaises;
    private Spinner spinnerTipoBecas;
    private Spinner spinnerTipoEstudiante;
    private int seleccion_usuario;
    private LocalRecieverFiltro reciever = new LocalRecieverFiltro(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_becas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*********************************************
         CREAR ADAPTADOR PARA MOSTRAR LA INFORMACION
         *********************************************/
        Intent intent = getIntent();
        this.seleccion_usuario     = intent.getIntExtra("listview", 0);
        this.contexto = this;
        

        this.listView = (ExpandableListView) findViewById(R.id.listView);
        getLoaderManager().initLoader(0,null,MostrarBecas.this);

    }

    private String generarAccion(int accion) {
        String params = "";
        switch (accion) {
            case MenuPrincipal.ID_VERBECAS:
                setTitle(getString(R.string.activity_verbecas_buscar));
                params = MenuPrincipal.OPERACION_VERBECAS;
                break;
            case MenuPrincipal.ID_VERSUGERENCIAS:
                setTitle(getString(R.string.activity_verbecas_sugeridas));
                params = MenuPrincipal.OPERACION_VERBECASSUGERENCIAS+"/"+String.valueOf(MenuPrincipal.user.getIdTipoEstudiante());
                break;
            case MenuPrincipal.ID_VERBECASINTERES:
                setTitle(getString(R.string.activity_verbecas_interes));
                params = MenuPrincipal.OPERACION_VERBECASINTERES+"/"+String.valueOf(MenuPrincipal.user.getIdusuario());
                break;
        }
        return params;
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
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_filtro_becas, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        this.spinnerTipoBecas      = (Spinner) view.findViewById(R.id.tipoBecaFiltro);
        this.spinnerTipoEstudiante = (Spinner) view.findViewById(R.id.tipoEstFiltro);
        this.spinnerPaises = (Spinner) view.findViewById(R.id.paisFiltro);
        final EditText ciudad = (EditText)  view.findViewById(R.id.ciudadFiltro);


        AlertDialog ad = builder.create();
        ad.setTitle("Buscar");

        LocalBroadcastManager.getInstance(this).registerReceiver(reciever, new IntentFilter(RegistroService.RESPONSE_ACTION));
        final Intent mServiceIntent = new Intent(MostrarBecas.this, RegistroService.class);

        mServiceIntent.putExtra(OPERACION, "tiposbecas");
        mServiceIntent.putExtra("ruta", "tiposbecas");
        startService(mServiceIntent);

        mServiceIntent.putExtra(OPERACION, "tiposestudiantes");
        mServiceIntent.putExtra("ruta", "tiposestudiantes");
        startService(mServiceIntent);

        mServiceIntent.putExtra(OPERACION, "paises");
        mServiceIntent.putExtra("ruta", "paises");
        startService(mServiceIntent);


        ad.setButton(AlertDialog.BUTTON_POSITIVE, "Buscar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mServiceIntent.putExtra(OPERACION,"filtrobecas");
                        mServiceIntent.putExtra("ruta","becas");
                        mServiceIntent.putExtra("idTipobeca", getIdTipoBeca(spinnerTipoBecas.getSelectedItem()));
                        mServiceIntent.putExtra("idTipoEstudiante", getIdTipoEstudiante(spinnerTipoEstudiante.getSelectedItem()));
                        mServiceIntent.putExtra("idPais", paises.get(spinnerPaises.getSelectedItem()));
                        mServiceIntent.putExtra("ciudad",ciudad.getText());
                        startService(mServiceIntent);
                        //mServiceIntent.putExtra("nombre_entidad", password.getText().toString());

                    }
                });

        ad.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        ad.show();

    }

    private int getIdTipoBeca(Object selectedItem) {
        for(TipoBeca tipo : this.tipoBecas){
            if(tipo.getNombre().equals((String)selectedItem)){
                return tipo.getId();
            }
        }
        return 0;
    }
    private int getIdTipoEstudiante(Object selectedItem) {
        for(TipoEstudiante tipo : this.tipoEstudiantes){
            if(tipo.getNombre().equals((String)selectedItem)){
                return tipo.getId();
            }
        }
        return 0;
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
    public Loader<ArrayList<Beca>> onCreateLoader(int id, Bundle args) {
        String params = generarAccion(seleccion_usuario);
        return new BecasLoader(contexto,this.seleccion_usuario,params);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Beca>> loader, ArrayList<Beca> data) {
        Log.d("","ENTRANDO AL FINISH LOADER"+data.size());
        for(int i = 0; i < data.size(); i++){
            Log.d("RESULTADO= ",data.get(i).toString());
        }
        this.becas = data;
        setAdapterBecas();

    }

    private void setAdapterBecas() {
        this.listView = (ExpandableListView) findViewById(R.id.listView);
        mostrarInfo = new ListViewVerBecas(this, becas);
        this.listView.setAdapter(mostrarInfo);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Beca>> loader) {

    }


    public void setTipoEstudiantes(ArrayList<TipoEstudiante> tipoEstudiantes) {
        this.tipoEstudiantes = tipoEstudiantes;
        TipoEstudiante vacio = new TipoEstudiante(-50,"---");
        this.tipoEstudiantes.add(vacio);
        ArrayList<String> tiposEstudiantes = new ArrayList<>();
        for(TipoEstudiante tipo : this.tipoEstudiantes){
            tiposEstudiantes.add(tipo.getNombre());
        }
        Collections.sort(tiposEstudiantes);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tiposEstudiantes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoEstudiante.setAdapter(arrayAdapter);
    }

    public void setTipoBecas(ArrayList<TipoBeca> tipoBecas) {
        this.tipoBecas = tipoBecas;
        TipoBeca vacio = new TipoBeca(-50,"---");
        this.tipoBecas.add(vacio);
        ArrayList<String> tiposBecas = new ArrayList<>();
        for(TipoBeca tipo : this.tipoBecas){
            tiposBecas.add(tipo.getNombre());
            Log.d(TAG,tipo.getNombre());
        }
        Collections.sort(tiposBecas);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tiposBecas);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoBecas.setAdapter(arrayAdapter);
    }

    public void setPaises(Hashtable<String, Integer> paises) {
        this.paises = paises;
        //TipoBeca vacio = new TipoBeca(-50,"---");
        this.paises.put("---",-50);
        ArrayList<String> list = new ArrayList<>();
        list.addAll(paises.keySet());
        Collections.sort(list);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPaises.setAdapter(arrayAdapter);
    }

    public void setBecas(ArrayList<Beca> becas) {
        this.becas = becas;
        setAdapterBecas();
    }
}