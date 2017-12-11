package ingenio.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView nombre = (TextView) findViewById(R.id.nombrePerfilText);
        TextView apellido = (TextView) findViewById(R.id.apellidoPerfilText);
        TextView direccion = (TextView) findViewById(R.id.direccionPerfilText);
        TextView fecha = (TextView) findViewById(R.id.fechaPerfilText);
        TextView pais = (TextView) findViewById(R.id.paisPerfilText);
        TextView provincia = (TextView) findViewById(R.id.provinciaPerfilText);
        TextView ciudad = (TextView) findViewById(R.id.ciudadPerfilText);
        TextView email = (TextView) findViewById(R.id.emailPerfilText);
        TextView tipoEstudiante = (TextView) findViewById(R.id.tipoEstPerfilText);
        TextView orientacion = (TextView) findViewById(R.id.orientacionPerfilText);

        nombre.setText(nombre.getText() + " " + MenuPrincipal.usuario.getNombre() );
        apellido.setText(apellido.getText() + " " + MenuPrincipal.usuario.getApellido());
        SimpleDateFormat fechaformat = new SimpleDateFormat("dd/MM/yyyy");
        fecha.setText(fecha.getText() + " " +  fechaformat.format(MenuPrincipal.usuario.getFecha_nacimiento().getTime()) );
        direccion.setText(direccion.getText() + " " + MenuPrincipal.usuario.getDireccion());
        email.setText(direccion.getText() + " " + MenuPrincipal.usuario.getDireccion());
    }

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
