package util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Communication {
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Communication(Socket socket){
        try {
            out = new ObjectOutputStream( socket.getOutputStream() );
            in = new ObjectInputStream( socket.getInputStream() );
        } catch (Exception e) {
            System.out.println("Error on create object for communication.");
            System.out.println(e.getMessage());
        }
    }

    public void send(Object object){
        try {
            out.writeObject(object);
            out.flush();
            out.reset();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Object receive(){
        try {
            return in.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    public void close(){
        try {
            in.close();
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
