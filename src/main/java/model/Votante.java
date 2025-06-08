package model;

public class Votante {
    private int id;
    private String nombre;
    private int idVereda;

    public Votante() {}

    public Votante(int id, String nombre, int idVereda) {
        this.id = id;
        this.nombre = nombre;
        this.idVereda = idVereda;
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

    public int getIdVereda() {
        return idVereda;
    }

    public void setIdVereda(int idVereda) {
        this.idVereda = idVereda;
    }
}
