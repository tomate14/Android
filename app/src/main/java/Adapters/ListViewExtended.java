package Adapters;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import ingenio.myapplication.R;

/**
 * Created by Maxi on 5/9/2017.
 */

public abstract class ListViewExtended extends BaseExpandableListAdapter{
    protected Context contexto;

    //Se usa un arreglo header y un arreglo hijo para cargar cosas adentro
    //Arreglo header viene con codigo de barra y nombre
    //Arreglo hijo viene con los supermercados y el valor

    protected String[] header;
    protected String[]subHeader;
    protected String[][] footer;


    @Override
    public int getGroupCount() {
        return header.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return footer[groupPosition].length;
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
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public abstract View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent);

    @Override
    public abstract View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent);
}