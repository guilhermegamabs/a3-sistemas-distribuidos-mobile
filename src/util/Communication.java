package util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Communication {
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Communication(Socket socket) {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println("ERROR ON CREATING THE COMMUNICATION CONSTRUCTOR");
            System.out.println(e.getMessage());
        }
    }

    public void send(Object obj) {
        try {
            out.writeObject(obj);
            out.flush();
            out.reset();
        } catch (Exception e) {
            System.out.println("ERROR ON SENDING OBJECT");
            System.out.println(e.getMessage());
        }
    }

    public Object receive() {
        try {
            return in.readObject();
        } catch (Exception e) {
            System.out.println("ERROR ON RECEIVING OBJECT");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void close() {
        try {
            in.close();
            out.close();
        } catch (Exception e) {
            System.out.println("ERROR ON CLOSING THE COMMUNICATION CONSTRUCTOR");
            System.out.println(e.getMessage());
        }
    }
}
