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
        Response response = null;
        int opcao = 0;

        try {
            Message messagePlayer1 = null;

            while(opcao != 4) {
                Message messageServer = new Message("Servidor", random.nextInt(3) + 1);
                messagePlayer1 = (Message) communicationPlayer1.receive();
                    if(messagePlayer1.getOpcao() != 4) {
                        System.out.println("Jogador - " + messagePlayer1.getOpcao() + " X " + messageServer.getOpcao() + " - Server");
                    }

                    if (messagePlayer1.getOpcao() == messageServer.getOpcao()) {
                        messagePlayer1.empatou();
                        response = new Response(messagePlayer1.getPlayerName(), messagePlayer1.getVitorias(), messagePlayer1.getEmpates(), messagePlayer1.getDerrotas());
                    } else if (messagePlayer1.getOpcao() == 1 && messageServer.getOpcao() == 2 || messagePlayer1.getOpcao() == 2 && messageServer.getOpcao() == 3 || messagePlayer1.getOpcao() == 3 && messageServer.getOpcao() == 1 ) {
                        messagePlayer1.venceu();
                        response = new Response(messagePlayer1.getPlayerName(), messagePlayer1.getVitorias(), messagePlayer1.getEmpates(), messagePlayer1.getDerrotas());
                    } else if(messagePlayer1.getOpcao() == 2 && messageServer.getOpcao() == 1 || messagePlayer1.getOpcao() == 3 && messageServer.getOpcao() == 2 || messagePlayer1.getOpcao() == 1 && messageServer.getOpcao() == 3 ){
                        messagePlayer1.perdeu();
                        response = new Response(messagePlayer1.getPlayerName(), messagePlayer1.getVitorias(), messagePlayer1.getEmpates(), messagePlayer1.getDerrotas());
                    } else {
                        opcao = 4;
                    }

                communicationPlayer1.send(response);
                }

            System.out.println("Cliente encerrou o jogo!");
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
