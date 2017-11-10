package Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import Adapters.ListViewExtended;
import Funcionalidad.Servicios;
import entity.Beca;
import ingenio.myapplication.R;

/**
 * Created by Maxi on 6/11/2017.
 */

public class ListViewSubscripciones extends ListViewExtended {

    public ListViewSubscripciones(Context contexto, ArrayList<Beca>becas) {
        this.contexto = contexto;
        this.becas = becas;
        //this.header = new Servicios().getHeaderSubscripciones();
        //this.subHeader = new Servicios().getSubHeaderSubscripciones();
        //this.footer = new Servicios().getFooterSubscripciones();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView txtTitle;
        TextView txtNombre;

        View inflate = View.inflate(contexto, R.layout.infosubscripciones,null);

        txtTitle  = (TextView) inflate.findViewById(R.id.txtTexto);
        txtNombre = (TextView) inflate.findViewById(R.id.txtSubTitulo);
        txtTitle.setText(becas.get(groupPosition).getNombre());
        txtNombre.setText(becas.get(groupPosition).getTipoBeca());
        return inflate;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView tv = new TextView(contexto);
        TextView txtTexto;
        View inflate = View.inflate(contexto, R.layout.infosubscripcioneshijo,null);
        txtTexto  = (TextView) inflate.findViewById(R.id.txtTexto);
        txtTexto.setText(becas.get(groupPosition).getDescripcion());
        tv.setTextSize(12);
        return inflate;
    }

}
