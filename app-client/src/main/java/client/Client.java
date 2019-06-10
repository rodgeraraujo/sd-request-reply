package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String args[]) {
        Socket s = null;
        try {
            s = new Socket(args[0], Integer.parseInt(args[1])); //conecta o socket a porta remota
            DataInputStream ent = new DataInputStream(s.getInputStream());
            DataOutputStream sai = new DataOutputStream(s.getOutputStream());
            sai.writeUTF(args[2]);
            // lê buffer de entrada
            String recebido = ent.readUTF();
            System.out.println("Success: recebido do servidor: " + recebido);
        } catch (UnknownHostException e) {
            System.out.println("Error:  servidor desconhecido: " + e.getMessage());
        } catch (EOFException e) {
            System.out.println("Nao ha mais dados de entrada: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("E/S: " + e.getMessage());
        } finally {
            if (s != null)
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("Encerramento do socket falhou: " + e.getMessage());
                }
        }
    }
}
