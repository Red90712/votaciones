package model;

public class Voto {
    private int id;
    private int idVotante;
    private int idCandidato;

    public Voto() {}

    public Voto(int id, int idVotante, int idCandidato) {
        this.id = id;
        this.idVotante = idVotante;
        this.idCandidato = idCandidato;
    }
    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVotante() {
        return idVotante;
    }

    public void setIdVotante(int idVotante) {
        this.idVotante = idVotante;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }
}
