package ingenio.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FiltroBecasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_becas);


        Button btnBuscar = (Button) findViewById(R.id.btnBuscarFiltro);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mostrar = new Intent(FiltroBecasActivity.this,MostrarBecas.class);
                mostrar.putExtra("listview", MenuPrincipal.ID_VERBECAS);
                startActivity(mostrar);
            }
        });
    }
}
