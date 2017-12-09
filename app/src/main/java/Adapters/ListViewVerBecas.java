package adapters;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.opengl.EGLExt;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entity.Beca;
import ingenio.myapplication.MenuPrincipal;
import ingenio.myapplication.MostrarBecas;
import ingenio.myapplication.R;

/**
 * Created by Maxi on 6/11/2017.
 */

public class ListViewVerBecas extends ListViewExtended {

    public static final int ID_LISTVIEW = 1;
    private Intent servicioSubscripcion;


    public ListViewVerBecas(Context contexto, ArrayList<Beca> becas) {
        this.becas = becas;
        this.contexto = contexto;
    }

    @Override
    public int getGroupCount() {
        return becas.size();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final int posicion = groupPosition;
        if (convertView == null) {
            convertView = View.inflate(contexto, R.layout.infobecas,null);
            final Button button = (Button) convertView.findViewById(R.id.btnbutton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (becas.get(posicion).isSubscripta()){
                        button.setBackgroundResource(android.R.drawable.btn_star_big_off);
                        becas.get(posicion).setSubscripta(false);
                    }else{
                        button.setBackgroundResource(android.R.drawable.btn_star_big_on);
                        becas.get(posicion).setSubscripta(true);
                    }
                }
            });
            if (becas.get(groupPosition).isSubscripta()){
                button.setBackgroundResource(android.R.drawable.btn_star_big_on);
            }else{
                button.setBackgroundResource(android.R.drawable.btn_star_big_off);
            }
            //servicioSubscripcion.putExtra(MostrarBecas.OPERACION,"subscribir");
            //servicioSubscripcion.putExtra("ruta","suscribe");
            //servicioSubscripcion.putExtra("idUsuario", MenuPrincipal.user.getIdusuario());
            //servicioSubscripcion.putExtra("idBeca", becas.get(groupPosition).getId());
            //NO hay que hacer nada con la respuesta. Se envia el servicio y nos manejamos.
            //O habria que actualizar las becas?
            //contexto.startService(servicioSubscripcion);
        }
        TextView txtTitle;
        TextView txtTipo;
        TextView txtEstudiante;

        txtTitle  = (TextView) convertView.findViewById(R.id.txtTitle);
        txtTipo = (TextView) convertView.findViewById(R.id.txttipo);
        txtEstudiante = (TextView) convertView.findViewById(R.id.txtEstudiante);
        txtTitle.setText(becas.get(groupPosition).getNombre());
        txtTipo.setText(becas.get(groupPosition).getTipoBeca().getNombre());
        txtEstudiante.setText(becas.get(groupPosition).getTipoEstudiante().getNombre());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final int posicion = groupPosition;

        TextView tv = new TextView(contexto);
        TextView txtTexto;
        if (convertView == null) {
            convertView = View.inflate(contexto, R.layout.infobecashijo, null);


            final Button button = (Button) convertView.findViewById(R.id.btnTelefono);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(android.content.Intent.ACTION_DIAL,Uri.parse("tel:"+becas.get(posicion).getTelefono()));
                    contexto.startActivity(intent);
                }
            });

            final Button button2 = (Button) convertView.findViewById(R.id.btnInfo);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openChrome(becas.get(groupPosition).getPagina());
                }
            });


        }
        txtTexto = (TextView) convertView.findViewById(R.id.txtTexto);
        txtTexto.setText(becas.get(groupPosition).getDescripcion());
        tv.setTextSize(12);

        ImageView mImg;
        mImg = (ImageView) convertView.findViewById(R.id.imageView2);
        mImg.setImageBitmap(becas.get(groupPosition).getBanner());

        SimpleDateFormat mdyFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");

        TextView fechaIni = (TextView)convertView.findViewById(R.id.txtFechaIni);
        fechaIni.setText(mdyFormat.format(becas.get(groupPosition).getFecha_inicio_inscripcion()));


        TextView fechaFin = (TextView)convertView.findViewById(R.id.txtFechaFin);
        fechaFin.setText(mdyFormat.format(becas.get(groupPosition).getFecha_fin_inscripcion()));



        return convertView;
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
