package client;

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

        String playerName, playerInput;
        int opcao;

        Communication communication;
        Message message;

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

            System.out.println("Escola uma opção: \n(1) Papel \n(2) Pedra \n(3) Tesoura");
            opcao = scanner.nextInt();

            message = new Message(playerName, opcao);

            Response response = (Response) communication.receive();

            System.out.println("Resposta: " + response);
            communication.send(message);


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
