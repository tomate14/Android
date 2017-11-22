package entity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.Vector;

/**
 * Created by Maxi on 22/11/2017.
 * Aca adentro van los banners que aparecen en la pantalla principal
 */

public class Anuncio {
    private Bitmap imagen;

    public Anuncio(Bitmap imagen) {
        this.imagen = imagen;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
}
