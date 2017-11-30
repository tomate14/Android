package ingenio.myapplication;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity{

    private int accion_a_realizar;
    private Context contexto;
    private EditText editBirthday;
    private TextInputEditText nombre;
    private TextInputEditText apellido;
    private TextInputEditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.contexto = this;
        Button btnConfirmar = (Button)findViewById(R.id.btnConfirmar);
        editBirthday = (EditText) findViewById(R.id.editBirthday);
        nombre = (TextInputEditText) findViewById(R.id.nombreRegister);
        apellido = (TextInputEditText) findViewById(R.id.apellidoRegister);
        email = (TextInputEditText) findViewById(R.id.emailRegister);

        Intent intent = getIntent();
        this.accion_a_realizar = intent.getIntExtra("AccionDatos",0);
        switch (this.accion_a_realizar){
            case LoginActivity.ID_REGISTER:
                break;
            case MenuPrincipal.ID_EDITARDATOS:
                //disableElements();
                break;
            case LoginActivity.ID_REGISTERGOOGLE:
            {
                nombre.setText(intent.getStringExtra("nombre"));
                apellido.setText(intent.getStringExtra("apellido"));
                email.setText(intent.getStringExtra("email"));
                break;
            }

        }

        btnConfirmar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (accion_a_realizar){
                    case LoginActivity.ID_REGISTER:
                        //enviar servicio de registrar usuario
                        break;
                    case MenuPrincipal.ID_EDITARDATOS:
                        //Enviar servicio de editar datos
                        break;

                }

                //ENVIAR SERVICIO DE REGISTRO
            }
        });

        editBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              DatePickerDialog dialog = new DatePickerDialog(contexto,new DatePickerDialog.OnDateSetListener() {
                  public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                      updateDisplay(selectedyear,selectedmonth,selectedday);
                  }
              },1990,01,01);
              dialog.show();
            }
        });


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


}
