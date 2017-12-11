package ingenio.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import funcionalidad.LocalRecieverAnuncio;
import funcionalidad.LocalRecieverFiltro;
import funcionalidad.RegistroService;
import funcionalidad.Servicios;
import entity.Anuncio;
import entity.Usuario;

public class MenuPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //private ListView listView;
    public static final int ID_VERBECAS        = 1;
    public static final int ID_VERBECASINTERES = 2;
    public static final int ID_VERSUGERENCIAS  = 3;
    public static final int ID_EDITARDATOS     = 4;

    public static final String OPERACION_VERBECAS            = "/becas";
    public static final String OPERACION_VERBECASINTERES     = "/becasinteresadas";
    public static final String OPERACION_VERBECASSUGERENCIAS = "/becassugeridas";
    public static final String OPERACION_VERANUNCIOS = "anuncios";

    public static final String OPERACION = "OPERATION_SERVICE";

    private MenuPrincipal.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public static ArrayList<Anuncio> anuncios;
    public static Usuario user = null;
    private static Context contexto;


    private LocalRecieverAnuncio reciever = new LocalRecieverAnuncio(this);
    DisplayMetrics displayMetrics = new DisplayMetrics();


    private TextView txtNombre;
    private TextView txtEmail;
    private static int height_cellPhone;
    private static int width_cellPhone;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        this.contexto = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View datosUsuario = navigationView.getHeaderView(0);

        //Informacion del login
        Intent intUsuario =  getIntent();
        //user = (Usuario) intUsuario.getSerializableExtra("usuario");
        user = new Usuario(3,"maxiroselli@gmail.com","Maximiliano","Roselli",new Date(),"tucolaloca","tu hermana",1);

        this.txtNombre = (TextView) datosUsuario.findViewById(R.id.txtNombreNav);
        this.txtEmail = (TextView) datosUsuario.findViewById(R.id.txtEmailNav);

        txtNombre.setText(user.getNombre());
        txtEmail.setText(user.getEmail());

        LocalBroadcastManager.getInstance(this).registerReceiver(reciever, new IntentFilter(RegistroService.RESPONSE_ACTION));
        final Intent mServiceIntent = new Intent(MenuPrincipal.this, RegistroService.class);

        mServiceIntent.putExtra(OPERACION, "veranuncios");
        mServiceIntent.putExtra("ruta", OPERACION_VERANUNCIOS);
        startService(mServiceIntent);


        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.height_cellPhone = displayMetrics.heightPixels;
        this.width_cellPhone = displayMetrics.widthPixels;

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
                filtro.putExtra("operacion",OPERACION_VERBECAS);
                startActivity(filtro);
                break;
            case R.id.nav_becasSugeridas:
                Intent mostrar = new Intent(MenuPrincipal.this,MostrarBecas.class);
                mostrar.putExtra("listview", ID_VERSUGERENCIAS);
                mostrar.putExtra("operacion",OPERACION_VERBECASSUGERENCIAS);
                startActivity(mostrar);
                break;
            case R.id.nav_editarDatos:
                Intent editarDatos = new Intent(MenuPrincipal.this,RegisterActivity.class);
                editarDatos.putExtra("Accion_Datos",ID_EDITARDATOS);
                startActivity(editarDatos);
                break;
            case R.id.nav_verPerfil:
                Intent verPerfil = new Intent(MenuPrincipal.this,PerfilActivity.class);
                startActivity(verPerfil);
                break;
            case R.id.nav_misBecas:
                Intent mostrarse = new Intent(MenuPrincipal.this,MostrarBecas.class);
                mostrarse.putExtra("listview",ID_VERBECASINTERES);
                mostrarse.putExtra("operacion",OPERACION_VERBECASINTERES);
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
            int posicion = getArguments().getInt(ARG_SECTION_NUMBER);


            // renderizar la imagen
            ImageView image = (ImageView) rootView.findViewById(R.id.imageViewBanner);
            image.setImageBitmap(MenuPrincipal.anuncios.get(posicion).getBanner());
            //image.setImageDrawable(renderizarImg(MenuPrincipal.anuncios.get(posicion).getBanner()));

            SimpleDateFormat mdyFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            TextView fecha = (TextView) rootView.findViewById(R.id.txtMostrarFecha);
            fecha.setText(mdyFormat.format(anuncios.get(posicion).getFecha()));

            TextView titulo = (TextView) rootView.findViewById(R.id.txtTituloBanner);
            titulo.setText(anuncios.get(posicion).getInformacion());

            return rootView;
        }

        private Drawable renderizarImg(Bitmap imagen) {
            // cargamos la imagen de origen
            Bitmap BitmapOrg = imagen;

            int width = BitmapOrg.getWidth();
            int height = BitmapOrg.getHeight();

            // calculamos el escalado de la imagen destino
            float scaleWidth = (float) (((float) width_cellPhone) / (width+0.2));
            float scaleHeight = (float) (((float) height_cellPhone) / (height+0.2));

            // para poder manipular la imagen
            // debemos crear una matriz

            Matrix matrix = new Matrix();
            // resize the Bitmap
            matrix.postScale(scaleWidth, scaleHeight);

            // volvemos a crear la imagen con los nuevos valores
            Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,width, height, matrix, true);

            // si queremos poder mostrar nuestra imagen tenemos que crear un
            // objeto drawable y así asignarlo a un botón, imageview...
            return new BitmapDrawable(resizedBitmap);
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
            return anuncios.size();
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
    public void setAnuncios(ArrayList<Anuncio> anuncios) {
        this.anuncios = anuncios;
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }
}
