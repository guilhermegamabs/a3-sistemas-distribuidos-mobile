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
    
    public PlayMatch(Socket client1, Socket client2) {
        communicationPlayer1 = new Communication(client1);
        communicationPlayer2 = new Communication(client2);
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
        Player player1 = new Player();

        int opcao = 0;

        try {
            while(opcao != 4) {
                Message messageServer = new Message("Servidor", random.nextInt(3) + 1);
                Message messagePlayer1 = (Message) communicationPlayer1.receive();
                    if(messagePlayer1.getOpcao() != 4) {
                        System.out.println(messagePlayer1.getPlayerName() + " - " + messagePlayer1.getOpcao() + " X " + messageServer.getOpcao() + " - Server");
                    }

                    if (messagePlayer1.getOpcao() == messageServer.getOpcao()) {
                        messagePlayer1.empatou();
                        player1.increaseDraws();
                    } else if (messagePlayer1.getOpcao() == 1 && messageServer.getOpcao() == 2 || messagePlayer1.getOpcao() == 2 && messageServer.getOpcao() == 3 || messagePlayer1.getOpcao() == 3 && messageServer.getOpcao() == 1 ) {
                        messagePlayer1.venceu();
                        player1.increaseVictories();
                    } else if(messagePlayer1.getOpcao() == 2 && messageServer.getOpcao() == 1 || messagePlayer1.getOpcao() == 3 && messageServer.getOpcao() == 2 || messagePlayer1.getOpcao() == 1 && messageServer.getOpcao() == 3 ){
                        messagePlayer1.perdeu();
                        player1.increaseDefeats();
                    } else {
                        opcao = 4;
                    }

                    response = new Response(messagePlayer1.getPlayerName(), player1.getVictories(), player1.getDraws(), player1.getDefeats());

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

    private void playDuo(){
    	Response response = null;
    	Response response2 = null;
    	
        Player player1 = new Player();
        Player player2 = new Player();
        
        int opcao = 0;
        
        try {
        	while(opcao != 4) {
                Message messagePlayer1 = (Message) communicationPlayer1.receive();
                Message messagePlayer2 = (Message) communicationPlayer2.receive();
                
                if(messagePlayer1.getOpcao() != 4 && messagePlayer2.getOpcao() != 4) {
                    System.out.println(messagePlayer1.getPlayerName() + " - " + messagePlayer1.getOpcao() + " X " + messagePlayer2.getOpcao() + " - " + messagePlayer2.getPlayerName());
                }
                
                if (messagePlayer1.getOpcao() == messagePlayer2.getOpcao()) {
                    messagePlayer1.empatou();
                    messagePlayer2.empatou();
                    player1.increaseDraws();
                    player2.increaseDraws();
                } else if (messagePlayer1.getOpcao() == 1 && messagePlayer2.getOpcao() == 2 || messagePlayer1.getOpcao() == 2 && messagePlayer2.getOpcao() == 3 || messagePlayer1.getOpcao() == 3 && messagePlayer2.getOpcao() == 1 ) {
                    messagePlayer1.venceu();
                    messagePlayer2.perdeu();
                    player1.increaseVictories();
                    player2.increaseDefeats();
                } else if(messagePlayer1.getOpcao() == 2 && messagePlayer2.getOpcao() == 1 || messagePlayer1.getOpcao() == 3 && messagePlayer2.getOpcao() == 2 || messagePlayer1.getOpcao() == 1 && messagePlayer2.getOpcao() == 3 ){
                    messagePlayer1.perdeu();
                    messagePlayer2.venceu();
                    player1.increaseDefeats();
                    player2.increaseVictories();
                } else {
                    opcao = 4;
                }

                response = new Response(messagePlayer1.getPlayerName(), player1.getVictories(), player1.getDraws(), player1.getDefeats());
                response2 = new Response(messagePlayer2.getPlayerName(), player2.getVictories(), player2.getDraws(), player2.getDefeats());


            communicationPlayer1.send(response);
            communicationPlayer2.send(response2);
       
        	}
        	
        	
            System.out.println("Cliente encerrou o jogo!");
        }catch (Exception e) {
        } finally {
            try {
                communicationPlayer1.close();
                communicationPlayer2.close();
            } catch (Exception e) {
                System.out.println("ERRO ao fechar conexão com o player duo");
                System.out.println(e.getMessage());
            }
        }

    }
}
