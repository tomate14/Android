package entity;

import android.widget.ImageView;

import java.util.Vector;

/**
 * Created by Maxi on 22/11/2017.
 * Aca adentro van los banners que aparecen en la pantalla principal
 */

public class Anuncio {
    private ImageView imagen;

    public Anuncio(ImageView imagen) {
        this.imagen = imagen;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }
}
