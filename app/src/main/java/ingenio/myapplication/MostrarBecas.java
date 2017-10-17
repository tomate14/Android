package ingenio.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toolbar;

import Funcionalidad.ListViewExtended;

public class MostrarBecas extends AppCompatActivity {

    private ExpandableListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_becas);

        setTitle(getString(R.string.activity_verbecas));

        this.listView = (ExpandableListView) findViewById(R.id.listView);
        this.listView.setAdapter(new ListViewExtended(this));




    }
}