package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        int portServer = 5509;

        Scanner scanner = new Scanner(System.in);

        String inputData;

        System.out.printf("Informe a PORTA do Servidor (PORTA DEFAULT: %s): ", portServer);
        inputData = scanner.nextLine();

        portServer = inputData.length() > 0 ? Integer.parseInt(inputData) : portServer;

        try {
            serverSocket = new ServerSocket(portServer);

            while (true) {
                System.out.println("Aguardando o player...");
                Socket player = serverSocket.accept();
                System.out.println("Conectado com o " + player);

                
            }
        } catch (Exception e) {
            System.out.println("ERROR on Server side " + e.getMessage());
        } finally {
            scanner.close();
        }

    }
}
