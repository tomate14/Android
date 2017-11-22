package ingenio.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Adapters.ListViewBanner;
import Funcionalidad.Servicios;
import entity.Anuncio;

public class MenuPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView txtNombre;
    private TextView txtEmail;
    //private ListView listView;
    public static final int ID_VERBECAS = 1;
    public static final int ID_VERBECASINTERES = 2;
    public static final int ID_VERSUGERENCIAS = 3;
    public static final int ID_EDITARDATOS = 4;

    private MenuPrincipal.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public static ArrayList<Anuncio> anuncios;

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

        //this.listView = (ListView) findViewById(R.id.listViewBanners);
        //this.listView.setAdapter(new ListViewBanner(new Servicios().getAnuncios(this),this));
        //Informacion del login
        this.anuncios = new Servicios().getAnuncios(this);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

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
                //Intent reglamento = new Intent(MenuPrincipal.this, pruebaactivity.class);
                startActivity(reglamento);
                break;
            }     

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * CARROUSEL DE LOS ANUNCIOS. HAY QUE PONER UN TOPE MAXIMO DE ANUNCIOS, PONELE LOS PRIMEROS 5
     * PARA DEJAR FIJO EL TAMAÑO DE LAS IMAGENES QUE SE VAN A GENERAR
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        public PlaceholderFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_pruebaactivity, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            ImageView image = (ImageView) rootView.findViewById(R.id.imageViewBanner);
            int aux = getArguments().getInt(ARG_SECTION_NUMBER);
            image.setImageBitmap(MenuPrincipal.anuncios.get(getArguments().getInt(ARG_SECTION_NUMBER)).getImagen());
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
                case 4:
                    return "SECTION 5";
                case 5:
                    return "SECTION 6";
            }
            return null;
        }
    }
}
