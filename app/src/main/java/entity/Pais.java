package entity;

/**
 * Created by Maxi on 23/11/2017.
 */

public class Pais {

    private int id;
    private String nombre;
    private String codigo;

    public Pais(int id, String nombre, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
