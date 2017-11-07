package Funcionalidad;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;

import ingenio.myapplication.R;

/**
 * Created by Maxi on 6/11/2017.
 */

public class ListViewVerBecas extends ListViewExtended{

    public static final int ID_LISTVIEW = 1;

    public ListViewVerBecas(Context contexto) {
        this.contexto = contexto;
        this.header = new Servicios().getHeaderBecas();
        this.subHeader = new Servicios().getSubHeaderBecas();
        this.footer = new Servicios().getFooterBecas();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView txtTitle;
        TextView txtNombre;

        View inflate = View.inflate(contexto, R.layout.infobecas,null);

        txtTitle  = (TextView) inflate.findViewById(R.id.txtTexto);
        txtNombre = (TextView) inflate.findViewById(R.id.txtSubTitulo);
        txtTitle.setText(header[groupPosition]);
        txtNombre.setText(subHeader[groupPosition]);
        return inflate;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView tv = new TextView(contexto);
        TextView txtTexto;
        View inflate = View.inflate(contexto, R.layout.infobecashijo,null);
        txtTexto  = (TextView) inflate.findViewById(R.id.txtTexto);
        txtTexto.setText(footer[groupPosition][childPosition]);
        tv.setTextSize(12);
        return inflate;
    }

    /*@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeArray(this.header);
        dest.writeArray(this.subHeader);
        dest.writeArray(this.footer);
    }*/
}
