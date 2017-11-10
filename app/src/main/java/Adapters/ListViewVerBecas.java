package Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

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
    public int getGroupCount() {
        return becas.size();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView txtTitle;
        TextView txtTipo;
        TextView txtEstudiante;

        View inflate = View.inflate(contexto, R.layout.infobecas,null);
       /* inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button aux = (Button)v.findViewById(R.id.btnSubscribirme);

            }
        });*/
        txtTitle  = (TextView) inflate.findViewById(R.id.txtTitle);
        txtTipo = (TextView) inflate.findViewById(R.id.txttipo);
        txtEstudiante = (TextView) inflate.findViewById(R.id.txtEstudiante);
        txtTitle.setText(becas.get(groupPosition).getNombre());
        txtTipo.setText(becas.get(groupPosition).getTipoBeca());
        txtEstudiante.setText(becas.get(groupPosition).getTipoEstudiante());
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
