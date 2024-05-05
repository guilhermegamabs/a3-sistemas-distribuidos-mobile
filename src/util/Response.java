package util;

import java.io.Serializable;

public class Response implements Serializable {
    private String playerName;
    private int vitorias;
    private int empates;
    private int derrotas;

    public Response(String playerName, int vitorias, int empates, int derrotas) {
        setPlayerName(playerName);
        setVitorias(vitorias);
        setEmpates(empates);
        setDerrotas(derrotas);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    @Override
    public String toString() {
        return "\nJogador: " + getPlayerName() + "\nVitorias: " + getVitorias() + "\nEmpates: " + getEmpates() + "\nDerrotas: " + getDerrotas();
    }
}
