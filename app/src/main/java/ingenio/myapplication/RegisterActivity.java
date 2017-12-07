package ingenio.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import entity.TipoBeca;
import entity.TipoEstudiante;
import funcionalidad.LocalReciever;
import funcionalidad.RegistroService;

public class RegisterActivity extends AppCompatActivity {

    public static final String OPERACION = "OPERATION_SERVICE";
    private int accion_a_realizar;
    private Context contexto;
    //private EditText editBirthday;
    private LocalReciever reciever = new LocalReciever(this);

    /*estructuras auxiliares*/
    private Hashtable<String, Integer> paises;
    private Hashtable<String, Integer> provincias;
    private Hashtable<String, Integer> ciudades;
    private ArrayList<TipoEstudiante> tipoEstudiantes;
    private ArrayList<TipoBeca> tipoBecas;

    /*widgets*/
    private EditText nombre;
    private EditText apellido;
    private EditText direccion;
    private EditText email;
    private EditText password;
    private EditText passwordNew;
    private EditText passwordVal;
    private EditText editBirthday;
    private Spinner spinnerTipoEstudiante;;
    private Spinner spinnerTipoBeca;
    private Spinner spinnerPais;
    private Spinner spinnerProvincia;
    private Spinner spinnerCiudad;
    private Button btnConfirmar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nombre = (EditText) findViewById(R.id.nombreRegister);
        apellido = (EditText) findViewById(R.id.apellidoRegister);
        direccion = (EditText) findViewById(R.id.direccionRegister);
        email = (EditText) findViewById(R.id.emailRegister);
        password = (EditText) findViewById(R.id.passwordRegister);
        passwordNew = (EditText) findViewById(R.id.passwordNewEdit);
        passwordVal = (EditText) findViewById(R.id.passwordValRegister);
        editBirthday = (EditText) findViewById(R.id.editBirthday);
        spinnerTipoEstudiante = (Spinner) findViewById(R.id.tipoRegister);
        spinnerTipoBeca = (Spinner) findViewById(R.id.orientacionRegister);
        spinnerPais = (Spinner) findViewById(R.id.paisRegister);
        spinnerProvincia = (Spinner) findViewById(R.id.provinciaRegister);
        spinnerCiudad = (Spinner) findViewById(R.id.ciudadRegister);
        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);


        LocalBroadcastManager.getInstance(this).registerReceiver(reciever, new IntentFilter(RegistroService.RESPONSE_ACTION));
        final Intent mServiceIntent = new Intent(RegisterActivity.this, RegistroService.class);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.contexto = this;
        Button btnConfirmar = (Button)findViewById(R.id.btnConfirmar);
        editBirthday = (EditText) findViewById(R.id.editBirthday);

        //falta implementar <---------------
        Intent intent = getIntent();
        this.accion_a_realizar = intent.getIntExtra("Accion_Datos", 0);
        Log.d("registerActivity", Integer.toString(this.accion_a_realizar));
        switch (this.accion_a_realizar) {
            case LoginActivity.ID_REGISTER:
                passwordNew.setVisibility(View.GONE);
                break;
            case MenuPrincipal.ID_EDITARDATOS:
                email.setVisibility(View.GONE);
                break;
            case LoginActivity.ID_REGISTERGOOGLE:
            {
                nombre.setText(intent.getStringExtra("nombre"));
                apellido.setText(intent.getStringExtra("apellido"));
                email.setText(intent.getStringExtra("email"));
                break;
            }

        }

        /* Definicion y carga del Spinner Pais */
        mServiceIntent.putExtra(OPERACION, "paises");
        mServiceIntent.putExtra("ruta", "paises");
        startService(mServiceIntent);

        mServiceIntent.putExtra(OPERACION, "tiposestudiantes");
        mServiceIntent.putExtra("ruta", "tiposestudiantes");
        startService(mServiceIntent);

        mServiceIntent.putExtra(OPERACION, "tiposbecas");
        mServiceIntent.putExtra("ruta", "tiposbecas");
        startService(mServiceIntent);

        spinnerPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mServiceIntent.putExtra(OPERACION, "provincias");
                mServiceIntent.putExtra("ruta", "provincias/" + paises.get(spinnerPais.getSelectedItem()));
                startService(mServiceIntent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        /* Definicion y carga del Spinner Provincias */

        spinnerProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mServiceIntent.putExtra(OPERACION, "ciudades");
                mServiceIntent.putExtra("ruta", "ciudades/" + provincias.get(spinnerProvincia.getSelectedItem()));
                startService(mServiceIntent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        /*pop up de fecha de nacimiento */

        editBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(contexto, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        updateDisplay(selectedyear, selectedmonth, selectedday);
                    }
                }, 1990, 01, 01);
                dialog.show();
            }
        });



        /*Definicion del boton Confirmar */

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (accion_a_realizar) {
                    case LoginActivity.ID_REGISTER:
                        if (registroVerificado()) {
                            mServiceIntent.putExtra("email", email.getText().toString());
                            mServiceIntent.putExtra("ruta", "registro");
                            mServiceIntent.putExtra(OPERACION, "registro");
                            mServiceIntent.putExtra("nombre", nombre.getText().toString());
                            mServiceIntent.putExtra("apellido", apellido.getText().toString());
                            mServiceIntent.putExtra("direccion", direccion.getText().toString());
                            mServiceIntent.putExtra("fecha", editBirthday.getText().toString());
                            Log.d("password", password.getText().toString()+ "     " + RegisterActivity.md5(password.getText().toString()));
                            mServiceIntent.putExtra("password", RegisterActivity.md5(password.getText().toString()));
                            mServiceIntent.putExtra("ciudad", ciudades.get(spinnerCiudad.getSelectedItem()));
                            mServiceIntent.putExtra("tipo", 1); // esto esta hardcode
                            mServiceIntent.putExtra("orientacion", 1); // esto esta hardcode
                            startService(mServiceIntent);
                        }
                        break;
                    case MenuPrincipal.ID_EDITARDATOS:
                        if (edicionVerificada()) { // falta chequeo de tipo y orientacion <-------------------------
                            mServiceIntent.putExtra("passwordNew", passwordNew.getText().toString());
                            mServiceIntent.putExtra("ruta", "modUsuario");
                            mServiceIntent.putExtra(OPERACION, "edicion");
                            mServiceIntent.putExtra("nombre", nombre.getText().toString());
                            mServiceIntent.putExtra("apellido", apellido.getText().toString());
                            mServiceIntent.putExtra("direccion", direccion.getText().toString());
                            mServiceIntent.putExtra("fecha", editBirthday.getText().toString());
                            mServiceIntent.putExtra("password", md5(password.getText().toString()));
                            mServiceIntent.putExtra("ciudad", ciudades.get(spinnerCiudad.getSelectedItem()));
                            mServiceIntent.putExtra("tipo", 1); // esto esta hardcode
                            mServiceIntent.putExtra("orientacion", 1); // esto esta hardcode
                            startService(mServiceIntent);
                        }
                        break;
                }
            }
        });

    }




    private boolean registroVerificado() {
        int errores = 0;
        String mensajeError = new String();

        if (nombre.getText().toString().equals("")) {
            mensajeError = mensajeError + "* Campo Nombre requerido" + "\n";
            errores++;
        }

        if (apellido.getText().toString().equals("")) {
            mensajeError = mensajeError + "* Campo Apellido requerido" + "\n";
            errores++;
        }

        if (direccion.getText().toString().equals("")) {
            mensajeError = mensajeError + "* Campo Direccion requerido" + "\n";
            errores++;
        }

        if (email.getText().toString().equals("")) {
            mensajeError = mensajeError + "* Campo E-email requerido" + "\n";
            errores++;
        }

        if (password.getText().toString().equals("")) {
            mensajeError = mensajeError + "* Campo Contraseña requerido" + "\n";
            errores++;
        }
        if (password.getText().toString().length() < 4) {
            mensajeError = mensajeError + "* Contraseña muy corta" + "\n";
            errores++;
        }

        if (editBirthday.getText().toString().equals("")) {
            mensajeError = mensajeError + "* Campo Fecha de nacimiento requerido" + "\n";
            errores++;
        }
        if (!password.getText().toString().equals(passwordVal.getText().toString())) {
            mensajeError = mensajeError + "* No coinciden las contraseñas" + "\n";
            errores++;
        }
        if (errores > 0) {
            AlertDialog.Builder chequeo = new AlertDialog.Builder(this);
            chequeo.setTitle("Ups! Reintente");
            chequeo.setCancelable(true);
            chequeo.setMessage(mensajeError);
            chequeo.show();
            return false;
        }
        return true;
    }

    private boolean edicionVerificada() {
        int ignores = 0;
        int errores = 0;
        String mensajeError = new String();

        if (nombre.getText().toString().equals("")) {
            ignores++;
        }

        if (apellido.getText().toString().equals("")) {
            ignores++;
        }

        if (direccion.getText().toString().equals("")) {
            ignores++;
        }

        if (password.getText().toString().equals("")) {
            ignores++;
        }

        if (passwordNew.getText().toString().equals("")) {
            ignores++;
        }

        if (password.getText().toString().equals("") && !passwordNew.getText().toString().equals("")) {
            mensajeError = mensajeError + "* Campo Contraseña Actual requerido para el cambio de contraseña" + "\n";
            errores++;
        }

        if (editBirthday.getText().toString().equals("")) {
            ignores++;
        }


        if (!passwordNew.getText().toString().equals(passwordVal.getText().toString())) {
            mensajeError = mensajeError + "* No coinciden las contraseñas" + "\n";
            errores++;
        }

        if (ignores == 6) {
            mensajeError = mensajeError + "* No se han modificado datos" + "\n";
            errores++;
        }

        if (errores > 0) {
            AlertDialog.Builder chequeo = new AlertDialog.Builder(this);
            chequeo.setTitle("Ups! Reintente");
            chequeo.setCancelable(true);
            chequeo.setMessage(mensajeError);
            chequeo.show();
            return false;
        }
        return true;
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

    private void updateDisplay(int year, int month, int day) {
        editBirthday.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).append(" "));
    }

    public void setPaises(Hashtable<String, Integer> paises) {
        this.paises = paises;
        ArrayList<String> list = new ArrayList<>();
        list.addAll(paises.keySet());
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPais.setAdapter(arrayAdapter);
    }

    public void setProvincias(Hashtable<String, Integer> provincias) {
        this.provincias = provincias;
        ArrayList<String> list = new ArrayList<>();
        list.addAll(provincias.keySet());
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvincia.setAdapter(arrayAdapter);
    }

    public void setCiudades(Hashtable<String, Integer> ciudades) {
        this.ciudades = ciudades;
        ArrayList<String> list = new ArrayList<>();
        list.addAll(ciudades.keySet());
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCiudad.setAdapter(arrayAdapter);
    }

    public void setTipoEstudiantes(ArrayList<TipoEstudiante> tipos){
        this.tipoEstudiantes = tipos;
        ArrayList<String> tiposEstudiantes = new ArrayList<>();
        for(TipoEstudiante tipo : this.tipoEstudiantes){
            tiposEstudiantes.add(tipo.getNombre());
        }
        Collections.sort(tiposEstudiantes);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tiposEstudiantes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoEstudiante.setAdapter(arrayAdapter);
    }

    private int getIdTipoEstudiante(Object selectedItem) {
        for(TipoEstudiante tipo : this.tipoEstudiantes){
            if(tipo.getNombre().equals((String)selectedItem)){
                return tipo.getId();
            }
        }
        return 0;
    }

    public void setTipoBecas(ArrayList<TipoBeca> tipoBecas) {
        this.tipoBecas = tipoBecas;
        ArrayList<String> tiposBecas = new ArrayList<>();
        for(TipoBeca tipo : this.tipoBecas){
            tiposBecas.add(tipo.getNombre());
        }
        Collections.sort(tiposBecas);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tiposBecas);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoBeca.setAdapter(arrayAdapter);
    }

    private int getIdTipoBeca(Object selectedItem) {
        for(TipoBeca tipo : this.tipoBecas){
            if(tipo.getNombre().equals((String)selectedItem)){
                return tipo.getId();
            }
        }
        return 0;
    }
    public void notificarRegistro(String mensaje) {
        AlertDialog.Builder chequeo = new AlertDialog.Builder(this);
        chequeo.setTitle("Registro completo");
        chequeo.setCancelable(true);
        chequeo.setMessage(mensaje);
        chequeo.show();
        chequeo.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                finish();
            }
        });
    }

    public void notificarError(String mensaje) {
        AlertDialog.Builder chequeo = new AlertDialog.Builder(this);
        chequeo.setTitle("Registro incorrecto");
        chequeo.setCancelable(true);
        chequeo.setMessage(mensaje);
        chequeo.show();
    }

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}