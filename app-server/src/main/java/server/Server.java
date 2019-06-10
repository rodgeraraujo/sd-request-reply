package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String args[]) {
        try {
            int porta = 6789; // especifica a porta do serviço

            //recebe argumentos informados na execução por terminal
            if (args.length > 0) porta = Integer.parseInt(args[0]);
            ServerSocket escuta = new ServerSocket(porta);
            System.out.println("Servidor");
            System.out.println("Porta de escuta (listen): " + porta);
            while (true) {
                // accept: bloqueia até que chegue uma requisição de conexao de algum cliente
                Socket cliente = escuta.accept();
                System.out.println("conexao aceita de (remoto): " + cliente.getRemoteSocketAddress());
                // cria nova thread para atender o cliente em questão
                Conexao c = new Conexao(cliente);
            }
        } catch (IOException e) {
            System.out.println("Erro na escuta: " + e.getMessage());
        }
    }
}
