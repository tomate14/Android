package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

import entity.Anuncio;
import ingenio.myapplication.R;

/**
 * Created by Maxi on 22/11/2017.
 */

public class ListViewBanner extends BaseAdapter {

    private ArrayList<Anuncio> anuncios;
    private Context contexto;

    public ListViewBanner(ArrayList<Anuncio> anuncios, Context contexto) {
        this.anuncios = anuncios;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return anuncios.size();
    }

    @Override
    public Object getItem(int position) {
        return anuncios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Declare Variables


        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.infoanuncio, parent, false);


        ImageView image = (ImageView) itemView.findViewById(R.id.imageBanner);
        image.setImageBitmap(anuncios.get(position).getImagen());

        return itemView;
    }
}
