package model;

    /**
 * Clase modelo que representa un resumen de votos.
 * Se utiliza para almacenar el total de votos obtenidos por un candidato,
 * agrupados por partido y vereda.
 */

public class ResumenVoto {
    private String partido;
    private String candidato;
    private String vereda;
    private int totalVotos;

//constructor

    public ResumenVoto(String partido, String candidato, String vereda, int totalVotos) {
        this.partido = partido;
        this.candidato = candidato;
        this.vereda = vereda;
        this.totalVotos = totalVotos;
}

    // Getter y setter
    public String getPartido() { return partido; }
    public void setPartido(String partido) { this.partido = partido; }

    public String getCandidato() { return candidato; }
    public void setCandidato(String candidato) { this.candidato = candidato; }

    public String getVereda() { return vereda; }
    public void setVereda(String vereda) { this.vereda = vereda; }

    public int gettotalVotos() { return totalVotos; }
    public void settotalVotos(int totalVotos) { this.totalVotos = totalVotos; }
}
