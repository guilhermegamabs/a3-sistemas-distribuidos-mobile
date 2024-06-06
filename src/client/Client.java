package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Socket socket = null;

        String ipServer = "127.0.0.1";
        int portServer = 12345;

        String playerInput;

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
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Qual é seu nome? ");
            String playerName = scanner.nextLine();
            writer.println(playerName);

            System.out.println("Você vai jogar sozinho? \n(1) - Sim \n(2) - Não");
            int opcaoPlay = scanner.nextInt();
            writer.println(opcaoPlay);
            scanner.nextLine(); // consume the leftover newline

            if (opcaoPlay == 1) {
                int opcao = 0;
                while (opcao != 4) {
                    String choosedOption = reader.readLine();
                    System.out.println();
                    System.out.println(choosedOption);
                    Thread.sleep(100);
                    int choosedAnswer = scanner.nextInt();
                    writer.println(choosedAnswer);
                    scanner.nextLine();

                    if (choosedAnswer == 4) {
                        System.out.println();
                        System.out.println("Jogo Encerrado!");
                        break; // Sai do loop ao escolher sair
                    } else {
                        System.out.println();
                        String jogada =  reader.readLine();
                        System.out.println(jogada);
                        String stats = reader.readLine();
                        System.out.println(stats); // Display the game stats
                    }
                }
            } else {
                String waitOpo = reader.readLine();
                System.out.println(waitOpo); // Esperando Oponente se conectar!

                System.out.println();
                String foundOpo = reader.readLine(); // Oponente Encontrado!
                System.out.println(foundOpo);

                int opcao2 = 0;
                while (opcao2 != 4) {
                    String chooseOption = reader.readLine();
                    System.out.println();
                    System.out.println(chooseOption); // Escolha uma opção: (1) Papel (2) Pedra (3) Tesoura (4) Sair
                    Thread.sleep(100); // Add a small delay to ensure the message is printed

                    int choosedOption = scanner.nextInt(); // Salva a opção
                    writer.println(choosedOption);
                    scanner.nextLine(); // consume the leftover newline

                    if (choosedOption == 4) {
                        System.out.println();
                        String msgAcabou = reader.readLine();
                        System.out.println(msgAcabou);
                        break; // Sai do loop ao escolher sair
                    } else {
                        System.out.println();
                        String jogada = reader.readLine();
                        System.out.println(jogada);
                        String stats = reader.readLine();
                        System.out.println(stats); // Display the game stats
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
            if (socket != null) {
                socket.close();
            }
        }
    }
}
