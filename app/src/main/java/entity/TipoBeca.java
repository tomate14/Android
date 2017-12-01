package entity;

/**
 * Created by Maxi on 30/11/2017.
 */

public class TipoBeca {
    private int id;
    private String nombre;

    public TipoBeca(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "TipoEstudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
