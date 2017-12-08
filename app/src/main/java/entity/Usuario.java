package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Maxi on 29/11/2017.
 */

public class Usuario implements Serializable{
    private int idusuario;
    private String email;
    private String nombre;
    private String apellido;
    private Date fecha_nacimiento;
    private String password;
    private String direccion;
    private int idTipoEstudiante;

    public Usuario(int idusuario, String email, String nombre, String apellido, Date fecha_nacimiento, String password, String direccion, int idTipoEstudiante) {
        this.idusuario = idusuario;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.password = password;
        this.direccion = direccion;
        this.idTipoEstudiante = idTipoEstudiante;
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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
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

    public int getIdTipoEstudiante() {
        return idTipoEstudiante;
    }

    public void setIdTipoEstudiante(int idTipoEstudiante) {
        this.idTipoEstudiante = idTipoEstudiante;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idusuario=" + idusuario +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", password='" + password + '\'' +
                ", direccion='" + direccion + '\'' +
                ", idTipoEstudiante=" + idTipoEstudiante +
                '}';
    }
}
