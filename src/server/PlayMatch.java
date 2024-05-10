package server;

import util.Communication;
import util.Message;
import util.Response;

import java.net.Socket;
import java.util.Random;

public class PlayMatch extends Thread {
    private Communication communicationPlayer1, communicationPlayer2;

    public PlayMatch(Socket client1) {
        communicationPlayer1 = new Communication(client1);
        communicationPlayer2 = null;
    }

    @Override
    public void run() {
        if (communicationPlayer2 == null) {
            playSingle();
        } else {
            playDuo();
        }
    }

    private void playSingle() {
        Random random = new Random();
        Response response;

        try {
            Message messagePlayer1 = (Message) communicationPlayer1.receive();

            while (messagePlayer1.getOpcao() != 4) {
                Message messageServer = new Message("Servidor", random.nextInt(3) + 1);

                if (messagePlayer1.getOpcao() == messageServer.getOpcao()) {
                    messagePlayer1.empatou();
                    response = new Response(messagePlayer1.getPlayerName(), messagePlayer1.getVitorias(), messagePlayer1.getEmpates(), messagePlayer1.getDerrotas());
                } else if (messagePlayer1.getOpcao() == 1 && messageServer.getOpcao() == 2) {
                    messagePlayer1.venceu();
                    response = new Response(messagePlayer1.getPlayerName(), messagePlayer1.getVitorias(), messagePlayer1.getEmpates(), messagePlayer1.getDerrotas());
                } else {
                    messagePlayer1.perdeu();
                    response = new Response(messagePlayer1.getPlayerName(), messagePlayer1.getVitorias(), messagePlayer1.getEmpates(), messagePlayer1.getDerrotas());
                }

                communicationPlayer1.send(response);
            }

        } catch (Exception e) {
        } finally {
            try {
                communicationPlayer1.close();
            } catch (Exception e) {
                System.out.println("ERRO ao fechar conexão com o player single");
                System.out.println(e.getMessage());
            }
        }
    }

    private void playDuo(){}
}
