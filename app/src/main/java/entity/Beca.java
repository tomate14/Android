package entity;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by LosDefe on 7/11/2017.
 */

public class Beca {
    private String nombre;
    private String descripcion;
    private String telefono;
    private Bitmap banner;
    private String pagina;
    private Date fecha_fin_inscripcion;
    private Date fecha_inicio_inscripcion;
    private String tipoBeca;
    private String tipoEstudiante;
    private boolean subscripta;
    private int id;

    public Beca(int id,String nombre, String descripcion, String telefono, Bitmap banner, String pagina, Date fecha_fin_inscripcion, Date fecha_inicio_inscripcion, String tipoBeca, String tipoEstudiante, boolean subscripta) {
        this.id                       = id;
        this.nombre                   = nombre;
        this.descripcion              = descripcion;
        this.telefono                 = telefono;
        this.banner                   = banner;
        this.pagina                   = pagina;
        this.fecha_fin_inscripcion    = fecha_fin_inscripcion;
        this.fecha_inicio_inscripcion = fecha_inicio_inscripcion;
        this.tipoBeca                 = tipoBeca;
        this.tipoEstudiante           = tipoEstudiante;
        this.subscripta               = subscripta;
    }
    public Beca(int id,String nombre, String descripcion, String telefono, Bitmap banner, String pagina, Date fecha_fin_inscripcion, Date fecha_inicio_inscripcion, String tipoBeca, String tipoEstudiante) {
        this.id                       = id;
        this.nombre                   = nombre;
        this.descripcion              = descripcion;
        this.telefono                 = telefono;
        this.banner                   = banner;
        this.pagina                   = pagina;
        this.fecha_fin_inscripcion    = fecha_fin_inscripcion;
        this.fecha_inicio_inscripcion = fecha_inicio_inscripcion;
        this.tipoBeca                 = tipoBeca;
        this.tipoEstudiante           = tipoEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Bitmap getBanner() {
        return banner;
    }

    public void setBanner(Bitmap banner) {
        this.banner = banner;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public Date getFecha_fin_inscripcion() {
        return fecha_fin_inscripcion;
    }

    public void setFecha_fin_inscripcion(Date fecha_fin_inscripcion) {
        this.fecha_fin_inscripcion = fecha_fin_inscripcion;
    }

    public Date getFecha_inicio_inscripcion() {
        return fecha_inicio_inscripcion;
    }

    public void setFecha_inicio_inscripcion(Date fecha_inicio_inscripcion) {
        this.fecha_inicio_inscripcion = fecha_inicio_inscripcion;
    }

    public String getTipoBeca() {
        return tipoBeca;
    }

    public void setTipoBeca(String tipoBeca) {
        this.tipoBeca = tipoBeca;
    }

    public String getTipoEstudiante() {
        return tipoEstudiante;
    }

    public void setTipoEstudiante(String tipoEstudiante) {
        this.tipoEstudiante = tipoEstudiante;
    }

    @Override
    public boolean equals(Object object){
        if (object != null && object instanceof Beca){
            return (this.getNombre().equals(((Beca) object).getNombre()));
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSubscripta() {
        return subscripta;
    }

    public void setSubscripta(boolean subscripta) {
        this.subscripta = subscripta;
    }

    @Override
    public String toString() {
        return "Beca{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", banner=" + banner +
                ", pagina='" + pagina + '\'' +
                ", fecha_fin_inscripcion=" + fecha_fin_inscripcion +
                ", fecha_inicio_inscripcion=" + fecha_inicio_inscripcion +
                ", tipoBeca='" + tipoBeca + '\'' +
                ", tipoEstudiante='" + tipoEstudiante + '\'' +
                ", subscripta=" + subscripta +
                ", id=" + id +
                '}';
    }
}
