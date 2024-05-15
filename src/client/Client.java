package client;

import util.Choice;
import util.Communication;
import util.Message;
import util.Response;

import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Socket socket = null;

        String ipServer = "127.0.0.1";
        int portServer = 5509;

        int opcao = 0;
        
        String playerName, playerInput;

        boolean loop = true;

        Communication communication;
        Message message;
        Choice choice;

        try {
            System.out.printf("Informe o IP do Servidor (IP DEFAULT: %s): ", ipServer);
            playerInput = scanner.nextLine();

            if(playerInput.length() > 0) {
                ipServer = playerInput;
            }

            System.out.printf("Informe a PORTA do Servidor (PORTA DEFAULT: %s): ", portServer);
            playerInput = scanner.nextLine();

            if (playerInput.length() > 0) {
                portServer = Integer.parseInt(playerInput);
            }

            socket = new Socket(ipServer, portServer);
            communication = new Communication(socket);

            System.out.println("Qual seu nome? ");
            playerName = scanner.nextLine();
            

            while(opcao != 4) {
                System.out.println("\nEscola uma opção: \n(1) Papel \n(2) Pedra \n(3) Tesoura \n(4) Sair");
                System.out.println();
                opcao = scanner.nextInt();

                message = new Message(playerName, opcao);
                communication.send(message);

                Response response = (Response) communication.receive();

                System.out.println("\nResposta: " + response);
            }

            System.out.println("Jogo Encerrado!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
                scanner.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
