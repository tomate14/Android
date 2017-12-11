package entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Maxi on 29/11/2017.
 */

public class Usuario implements Serializable{
    private int idusuario;
    private String email;
    private String nombre;
    private String apellido;
    private Calendar fecha_nacimiento;
    private String password;
    private String direccion;
    private int idCiudad;
    private int idTipoEstudiante;
    private int idTipoBeca;

    public Usuario(int idusuario, String email, String nombre, String apellido, Calendar fecha_nacimiento, String password, int idCiudad, String direccion, int idTipoEstudiante, int idTipoBeca) {
        this.idusuario = idusuario;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.password = password;
        this.direccion = direccion;
        this.idCiudad = idCiudad;
        this.idTipoEstudiante = idTipoEstudiante;
        this.idTipoBeca = idTipoBeca;
    }

    public Usuario(int idusuario,String email){
        this.idusuario = idusuario;
        this.email = email;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Calendar getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Calendar fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public int getIdTipoEstudiante() {
        return idTipoEstudiante;
    }

    public void setIdTipoEstudiante(int idTipoEstudiante) {
        this.idTipoEstudiante = idTipoEstudiante;
    }

    public int getIdTipoBeca() {
        return idTipoBeca;
    }

    public void setIdTipoBeca(int idTipoBeca) {
        this.idTipoBeca = idTipoBeca;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idusuario='" + idusuario +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fecha_nacimiento='" + fecha_nacimiento +
                ", password='" + password + '\'' +
                ", direccion='" + direccion + '\'' +
                ", tipoEstudiante='" + idTipoEstudiante + '\'' +
                ",tipoOrientacion='" + idTipoBeca + '\'' +
                '}';
    }
}
