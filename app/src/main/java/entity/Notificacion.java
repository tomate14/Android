package entity;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Maxi on 9/11/2017.
 */

public class Notificacion {

    private int    idAnuncio;
    private String informacion;
    private String nombreEntidad;
    private String titulo;
    private Date   fecha;
    private String web;
    private int idBeca;
    private String telefono;
    private Bitmap banner;


    public Notificacion(int idAnuncio, String informacion, String nombreEntidad, String titulo, Date fecha, String web, int idBeca, String telefono, Bitmap banne) {
        this.idAnuncio = idAnuncio;
        this.informacion = informacion;
        this.nombreEntidad = nombreEntidad;
        this.titulo = titulo;
        this.fecha = fecha;
        this.web = web;
        this.idBeca = idBeca;
        this.telefono = telefono;
        this.banner = banne;
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

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public int getIdBeca() {
        return idBeca;
    }

    public void setIdBeca(int idBeca) {
        this.idBeca = idBeca;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Bitmap getBanne() {
        return banner;
    }

    public void setBanne(Bitmap banne) {
        this.banner = banne;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "idAnuncio=" + idAnuncio +
                ", informacion='" + informacion + '\'' +
                ", nombreEntidad='" + nombreEntidad + '\'' +
                ", titulo='" + titulo + '\'' +
                ", fecha=" + fecha +
                ", web='" + web + '\'' +
                ", idBeca='" + idBeca + '\'' +
                ", telefono='" + telefono + '\'' +
                ", banne=" + banner +
                '}';
    }
}
