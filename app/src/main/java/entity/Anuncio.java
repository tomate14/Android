package entity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.Date;
import java.util.Vector;

/**
 * Created by Maxi on 22/11/2017.
 * Aca adentro van los banners que aparecen en la pantalla principal
 */

public class Anuncio {
    private Bitmap banner;
    private int idAnuncio;
    private String informacion;
    private Date fecha;


    public Anuncio(Bitmap imagen) {
        this.banner = imagen;
    }

    public Anuncio(Bitmap banner, int idAnuncio, String informacion, Date fecha) {
        this.banner = banner;
        this.idAnuncio = idAnuncio;
        this.informacion = informacion;
        this.fecha = fecha;
    }

    public Bitmap getBanner() {
        return banner;
    }

    public void setBanner(Bitmap imagen) {
        this.banner = imagen;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "banner=" + banner +
                ", idAnuncio=" + idAnuncio +
                ", informacion='" + informacion + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
