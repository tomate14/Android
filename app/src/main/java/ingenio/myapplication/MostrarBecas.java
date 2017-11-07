package ingenio.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.widget.ExpandableListView;
import android.widget.Toolbar;

import Funcionalidad.ListViewExtended;
import Funcionalidad.ListViewVerBecas;

public class MostrarBecas extends AppCompatActivity {

    private ExpandableListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_becas);
        Intent intent = getIntent();
        ListViewExtended mostrarInfo = (ListViewExtended) intent.getSerializableExtra("listview");
        setTitle(getString(R.string.activity_verbecas));

        this.listView = (ExpandableListView) findViewById(R.id.listView);
        this.listView.setAdapter(mostrarInfo);




    }
}