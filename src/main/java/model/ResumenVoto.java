package model;

public class ResumenVoto {
    private String partido;
    private String candidato;
    private int vereda;
    private int cantidadVotos;

    
    public ResumenVoto(String partido, String candidato, int vereda, int cantidadVotos) {
        this.partido = partido;
        this.candidato = candidato;
        this.vereda = vereda;
        this.cantidadVotos = cantidadVotos;
    }

    
    public String getPartido() { return partido; }
    public void setPartido(String partido) { this.partido = partido; }

    public String getCandidato() { return candidato; }
    public void setCandidato(String candidato) { this.candidato = candidato; }

    public int getVereda() { return vereda; }
    public void setVereda(int vereda) { this.vereda = vereda; }

    public int getCantidadVotos() { return cantidadVotos; }
    public void setCantidadVotos(int cantidadVotos) { this.cantidadVotos = cantidadVotos; }
}
