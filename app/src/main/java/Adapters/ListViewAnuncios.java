package Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import entity.Anuncio;
import ingenio.myapplication.R;


/**
 * Created by Maxi on 9/11/2017.
 */

public class ListViewAnuncios extends ListViewExtended {
    private ArrayList<Anuncio> anuncios;
    public ListViewAnuncios(Context contexto, ArrayList<Anuncio> anuncios) {
        this.contexto = contexto;
        this.anuncios = anuncios;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView txtTitulo;
        TextView txtFecha;
        TextView txtEntidad;

        View inflate = View.inflate(contexto, R.layout.infoanuncio,null);

        txtTitulo  = (TextView) inflate.findViewById(R.id.txtTexto);
        txtFecha = (TextView) inflate.findViewById(R.id.txtFecha);
        txtEntidad = (TextView) inflate.findViewById(R.id.txtFecha);
        txtEntidad.setText(anuncios.get(groupPosition).getEntidad());
        txtTitulo.setText(anuncios.get(groupPosition).getTitulo());
        txtFecha.setText(anuncios.get(groupPosition).getFecha().toString());

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
