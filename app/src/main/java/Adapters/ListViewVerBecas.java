package Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapters.ListViewExtended;
import Funcionalidad.Servicios;
import entity.Beca;
import ingenio.myapplication.R;

/**
 * Created by Maxi on 6/11/2017.
 */

public class ListViewVerBecas extends ListViewExtended {

    public static final int ID_LISTVIEW = 1;

    public ListViewVerBecas(Context contexto, ArrayList<Beca> becas) {
        this.becas = becas;
        this.contexto = contexto;
        //this.header = new Servicios().getHeaderBecas();
        //this.subHeader = new Servicios().getSubHeaderBecas();
        //this.footer = new Servicios().getFooterBecas();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView txtTitle;
        TextView txtNombre;

        View inflate = View.inflate(contexto, R.layout.infobecas,null);
       /* inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button aux = (Button)v.findViewById(R.id.btnSubscribirme);

            }
        });*/
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
        View inflate = View.inflate(contexto, R.layout.infobecashijo,null);
        txtTexto  = (TextView) inflate.findViewById(R.id.txtTexto);
        txtTexto.setText(becas.get(groupPosition).getDescripcion());
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
