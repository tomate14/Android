package entity;

import java.util.Date;

/**
 * Created by Maxi on 9/11/2017.
 */

public class Notificacion {

    private String titulo;
    private String entidad;
    private Date fecha;
    private String informacion;

    public Notificacion(String titulo, String entidad, Date fecha) {
        this.titulo = titulo;
        this.entidad = entidad;
        this.fecha = fecha;
        this.informacion = null;
    }
    public Notificacion(String titulo, String entidad, Date fecha, String informacion) {
        this.titulo = titulo;
        this.entidad = entidad;
        this.fecha = fecha;
        this.informacion = informacion;
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

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }
}
