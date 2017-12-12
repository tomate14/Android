package adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
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

        View inflate = View.inflate(contexto, R.layout.infonotificacion,null);

        SimpleDateFormat mdyFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");

        txtEntidad = (TextView) inflate.findViewById(R.id.txtEntidad);
        txtEntidad.setText(anuncios.get(groupPosition).getNombreEntidad());

        txtTitulo  = (TextView) inflate.findViewById(R.id.txtTitulo);
        txtTitulo.setText(anuncios.get(groupPosition).getTitulo());

        txtFecha = (TextView) inflate.findViewById(R.id.txtFecha);
        txtFecha.setText(mdyFormat.format(anuncios.get(groupPosition).getFecha()));

        return inflate;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView tv = new TextView(contexto);
        TextView txtTexto;
        final int posicion = groupPosition;

        if (convertView == null) {
            convertView = View.inflate(contexto, R.layout.infobecashijo, null);


            final Button button = (Button) convertView.findViewById(R.id.btnTelefono);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:"+becas.get(posicion).getTelefono()));
                    contexto.startActivity(intent);
                }
            });

            final Button button2 = (Button) convertView.findViewById(R.id.btnInfo);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openChrome(anuncios.get(posicion).getWeb());
                }
            });


        }

        View inflate = View.inflate(contexto, R.layout.infobecashijo,null);

        TextView textView4;
        textView4 = (TextView) inflate.findViewById(R.id.textView4);
        textView4.setVisibility(View.GONE);

        TextView txtFechaFin;
        txtFechaFin = (TextView) inflate.findViewById(R.id.txtFechaFin);
        txtFechaFin.setVisibility(View.GONE);

        TextView textView3;
        textView3 = (TextView) inflate.findViewById(R.id.textView3);
        textView3.setVisibility(View.GONE);

        TextView txtFechaIni;
        txtFechaIni = (TextView) inflate.findViewById(R.id.txtFechaIni);
        txtFechaIni.setVisibility(View.GONE);

        txtTexto  = (TextView) inflate.findViewById(R.id.txtTexto);
        txtTexto.setText(anuncios.get(groupPosition).getInformacion());
        tv.setTextSize(12);
        return inflate;
    }

    void openChrome(String url) {
        Uri uri = Uri.parse("googlechrome://navigate?url=" + url);
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        if (i.resolveActivity(contexto.getPackageManager()) == null) {
            i.setData(Uri.parse(url));
        }
        contexto.startActivity(i);
    }

}