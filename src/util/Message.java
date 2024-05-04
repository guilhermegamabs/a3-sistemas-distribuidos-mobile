package util;

import java.io.Serializable;

public class Message implements Serializable {
    private String playerName;
    private int opcao;

    public Message(String playerName, int opcao) {
        this.playerName = playerName;
        this.opcao = opcao;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPoints() {
        return opcao;
    }
}
