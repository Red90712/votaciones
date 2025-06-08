package model;

public class Candidato {

    private int id;
    private String nombre;
    private int idPartido;

    public Candidato(){}

    public Candidato(int id, String nombre, int idPartido) {
        this.id = id;
        this.nombre = nombre;
        this.idPartido = idPartido;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdPartido() {
        return this.idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }




    
}
