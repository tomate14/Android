package entity;

import java.util.Date;

/**
 * Created by Maxi on 9/11/2017.
 */

public class Anuncio {

    private String titulo;
    private String entidad;
    private Date fecha;

    public Anuncio(String titulo, String entidad, Date fecha) {
        this.titulo = titulo;
        this.entidad = entidad;
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
