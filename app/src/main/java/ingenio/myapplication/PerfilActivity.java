package ingenio.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

        nombre.setText(nombre.getText() + " " + MenuPrincipal.user.getNombre() );
        apellido.setText(apellido.getText() + " " + MenuPrincipal.user.getApellido());
        SimpleDateFormat fechaformat = new SimpleDateFormat("dd/MM/yyyy");
        fecha.setText(fecha.getText() + " " +  fechaformat.format(MenuPrincipal.user.getFecha_nacimiento().getTime()) );
        direccion.setText(direccion.getText() + " " + MenuPrincipal.user.getDireccion());
        email.setText(email.getText() + " " + MenuPrincipal.user.getEmail());

        Button editar = (Button) findViewById(R.id.btnEditPerfil);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PerfilActivity.this,RegisterActivity.class);
                intent.putExtra("Accion_Datos", MenuPrincipal.ID_EDITARDATOS);
                startActivity(intent);
            }
        });
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
