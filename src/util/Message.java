package util;

import java.io.Serializable;

public class Message implements Serializable {
    private String playerName;
    private int opcao;
    private int vitorias;
    private int empates;
    private int derrotas;

    public Message(String playerName, int opcao) {
        this.playerName = playerName;
        this.opcao = opcao;
        this.vitorias = 0;
        this.empates = 0;
        this.derrotas = 0;
    }

    public int getOpcao() {
        return opcao;
    }

    public void venceu() {
        this.vitorias++;
    }

    public void empatou() {
        this.empates++;
    }

    public void perdeu() {
        this.derrotas++;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public String getPlayerName() {
        return playerName;
    }
}
