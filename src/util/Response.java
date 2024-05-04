package util;

import java.io.Serializable;

public class Response implements Serializable {
    private String playerName;
    private String scoreboard;

    public Response(String playerName, String scoreboard) {
        this.playerName = playerName;
        this.scoreboard = scoreboard;
    }

    @Override
    public String toString() {
        return playerName + " win! " + scoreboard;
    }
}
