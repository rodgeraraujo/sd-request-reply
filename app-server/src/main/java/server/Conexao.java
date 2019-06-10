package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Conexao extends Thread {
    private DataInputStream entrada;
    private DataOutputStream saida;
    private Socket cliente;

    public Conexao(Socket s) {
        try {
            cliente = s;
            ent = new DataInputStream(cliente.getInputStream());
            sai = new DataOutputStream(cliente.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Erro IO Conexao: " + e.getMessage());
        }
    }

    public void run() {
        try {
            String recebido = ent.readUTF();
            sai.writeUTF(recebido.toUpperCase());
        } catch (EOFException e) {
            System.out.println("Conexao: EOFException " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Conexao: IOException " + e.getMessage());
        } finally {
            try {
                cliente.close();
            } catch (IOException e) {
                System.out.println("Conexao: erro close do socket");
            }
        }
    }
}
