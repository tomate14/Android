package Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import entity.Notificacion;
import ingenio.myapplication.R;

/**
 * Created by Maxi on 10/11/2017.
 */

public class ListViewNotificaciones extends ListViewExtended {
    private ArrayList<Notificacion> anuncios;

    public ListViewNotificaciones(Context contexto, ArrayList<Notificacion> anuncios){
        this.anuncios = anuncios;
        this.contexto = contexto;

    }
    @Override
    public int getGroupCount() {
        return anuncios.size();
    }




    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView txtTitulo;
        TextView txtFecha;
        TextView txtEntidad;

        View inflate = View.inflate(contexto, R.layout.infoanuncio,null);

        txtTitulo  = (TextView) inflate.findViewById(R.id.txtTitulo);
        txtFecha = (TextView) inflate.findViewById(R.id.txtFecha);
        txtEntidad = (TextView) inflate.findViewById(R.id.txtEntidad);
        txtEntidad.setText(anuncios.get(groupPosition).getEntidad());
        txtTitulo.setText(anuncios.get(groupPosition).getTitulo());
        txtFecha.setText(anuncios.get(groupPosition).getFecha().toString());

        return inflate;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView tv = new TextView(contexto);
        TextView txtTexto;
        View inflate = View.inflate(contexto, R.layout.infobecashijo,null);
        txtTexto  = (TextView) inflate.findViewById(R.id.txtTexto);
        txtTexto.setText(anuncios.get(groupPosition).getInformacion());
        tv.setTextSize(12);
        return inflate;
    }

}
