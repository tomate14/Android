package adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.opengl.EGLExt;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
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

        }
        txtTexto = (TextView) convertView.findViewById(R.id.txtTexto);
        txtTexto.setText(becas.get(groupPosition).getDescripcion());
        tv.setTextSize(12);

        return convertView;
    }

    /*@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeArray(this.header);
        dest.writeArray(this.subHeader);
        dest.writeArray(this.footer);
    }*/
}
