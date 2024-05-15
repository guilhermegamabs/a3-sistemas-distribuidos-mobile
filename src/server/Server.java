package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import util.Choice;
import util.Communication;
import util.Message;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        int portServer = 5509;

        int opcao = 0;
        int opcaoPlay;
        boolean playAlone;

        Scanner scanner = new Scanner(System.in);

        String inputData;

        System.out.printf("Informe a PORTA do Servidor (PORTA DEFAULT: %s): ", portServer);
        inputData = scanner.nextLine();

        portServer = inputData.length() > 0 ? Integer.parseInt(inputData) : portServer;

        try {
            serverSocket = new ServerSocket(portServer);
            System.out.println("Você vai jogar sozinho? \n(1) - Sim \n(2) - Não");
            opcaoPlay = scanner.nextInt();
            
            if(opcaoPlay == 1) {
            	playAlone = true;
            } else if(opcaoPlay == 2) {
            	playAlone = false;
            } else {
            	System.out.println("Opção Inválida! Jogará contra Bot!");
            	playAlone = true;
            }
            
            while (true) {
       
                
                if(playAlone == true) {
                	System.out.println("\nAguardando cliente...");
                    Socket player = serverSocket.accept();
                    System.out.println("Conectado com " + player);
                    
                	PlayMatch playMatch = new PlayMatch(player);
                	System.out.println("ESTOU NO PLAYSINGLE");
                    playMatch.start();
                } else {
                	System.out.println("\nAguardando cliente...");
                    Socket player = serverSocket.accept();
                    System.out.println("Conectado com " + player);
                    
                	System.out.println("Aguradando Oponente!");
                    Socket player2 = serverSocket.accept();
                    System.out.println("Conectado com " + player2);
                    
                	PlayMatch playMatch = new PlayMatch(player, player2);
                    playMatch.start();
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR on Server side " + e.getMessage());
        } finally {
        	scanner.close();
        }
    }
}
