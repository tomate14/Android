package Funcionalidad;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import ingenio.myapplication.R;

/**
 * Created by Maxi on 5/9/2017.
 */

public class ListViewExtended extends BaseExpandableListAdapter{
    private Context contexto;

    //Se usa un arreglo padre y un arreglo hijo para cargar cosas adentro
    //Arreglo padre viene con codigo de barra y nombre
    //Arreglo hijo viene con los supermercados y el valor

    private String[]padre = {"Beca TICs","FONCyT"};
    private String[]subpadre = {"Tipo: Ingenieria \nEstudiante: Universitario" , "Tipo: Ingenieria Informatica \nEstudiante: Universitario"};
    private String[][]hijos = {
            {"El objetivo es convocar a las instituciones universitarias de gestión pública radicadas en el país, a la presentación de propuestas para la adjudicación de cupos de becas a estudiantes para la finalización de estudios de grado en carreras relacionadas con el sector TIC."},
            {"La Agencia Nacional de Promoción Científica y Tecnológica (ANPCyT) apoya, a través del Fondo para la Investigación Científica y Tecnológica (FonCyT), proyectos de investigación cuya finalidad sea la generación de nuevos conocimientos científicos y tecnológicos."}
    };

    public ListViewExtended(Context contexto) {
        this.contexto = contexto;
    }

    @Override
    public int getGroupCount() {
        return padre.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return hijos[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView txtTitle;
        TextView txtNombre;

        View inflate = View.inflate(contexto, R.layout.infobecas,null);

        txtTitle  = (TextView) inflate.findViewById(R.id.txtTexto);
        txtNombre = (TextView) inflate.findViewById(R.id.txtSubTitulo);
        txtTitle.setText(padre[groupPosition]);
        txtNombre.setText(subpadre[groupPosition]);
        return inflate;
        //TextView tv = new TextView(contexto);
        // tv.setText(this.padre[groupPosition]);
        //tv.setTextSize(24);
        // return tv;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView tv = new TextView(contexto);
        TextView txtTexto;
        View inflate = View.inflate(contexto, R.layout.infobecashijo,null);
        txtTexto  = (TextView) inflate.findViewById(R.id.txtTexto);
        txtTexto.setText(hijos[groupPosition][childPosition]);
        tv.setTextSize(12);
        return inflate;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}