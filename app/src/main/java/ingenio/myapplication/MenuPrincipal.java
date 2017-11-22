package ingenio.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import Adapters.ListViewBanner;
import Funcionalidad.Servicios;

public class MenuPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView txtNombre;
    private TextView txtEmail;
    private ListView listView;
    public static final int ID_VERBECAS = 1;
    public static final int ID_VERBECASINTERES = 2;
    public static final int ID_VERSUGERENCIAS = 3;
    public static final int ID_EDITARDATOS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.listView = (ListView) findViewById(R.id.listViewBanners);
        this.listView.setAdapter(new ListViewBanner(new Servicios().getAnuncios(this),this));
        //Informacion del login
        this.txtNombre = (TextView) findViewById(R.id.txtNombre);
        //this.txtNombre.setText("USUARIO LOGUEADO");
        this.txtEmail = (TextView) findViewById(R.id.txtEmail);
        //this.txtEmail.setText("usuario@gmail.com");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
                super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_buscarBecas:
                Intent filtro = new Intent(MenuPrincipal.this,MostrarBecas.class);
                filtro.putExtra("listview", ID_VERBECAS);
                startActivity(filtro);
                break;
            case R.id.nav_becasSugeridas:
                Intent mostrar = new Intent(MenuPrincipal.this,MostrarBecas.class);
                mostrar.putExtra("listview", ID_VERSUGERENCIAS);
                startActivity(mostrar);
                break;
            case R.id.nav_editarDatos:
                Intent editarDatos = new Intent(MenuPrincipal.this,RegisterActivity.class);
                editarDatos.putExtra("AccionDatos",ID_EDITARDATOS);
                startActivity(editarDatos);
                break;
            case R.id.nav_verPerfil:
                Intent verPerfil = new Intent(MenuPrincipal.this,PerfilActivity.class);
                startActivity(verPerfil);
                break;
            case R.id.nav_misBecas:
                Intent mostrarse = new Intent(MenuPrincipal.this,MostrarBecas.class);
                mostrarse.putExtra("listview",ID_VERBECASINTERES);
                startActivity(mostrarse);
                break;
            case R.id.nav_historialNotificaciones:
                Intent anuncios = new Intent(MenuPrincipal.this,MostrarAnuncios.class);
                startActivity(anuncios);
                break;
            case R.id.nav_contacto:
                Intent contacto = new Intent(MenuPrincipal.this, ContactoActivity.class);
                startActivity(contacto);
                break;
            case R.id.nav_reglamento:
                Intent reglamento = new Intent(MenuPrincipal.this, ReglamentoActivity.class);
                startActivity(reglamento);
                break;
            }     

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
