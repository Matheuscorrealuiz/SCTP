import com.sun.nio.sctp.*;
import java.io.*;
import java.net.*;

import java.io.BufferedReader;

public class Cliente {

    public static void main(String[] args) throws Exception {
        Socket socketClient = new Socket("127.0.0.1",4444);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String msg = "";

        SctpChannel sc = SctpChannel.open(socketClient.getRemoteSocketAddress(), 1, 1);

        ObjectOutputStream outputStream = new ObjectOutputStream(socketClient.getOutputStream());

        while (!msg.equalsIgnoreCase("Sair")) {
            System.out.println("Conectando ao servidor");

            System.out.println("Digite a mensagem: ");

            msg = buffer.readLine();

            outputStream.writeObject(msg);

        }
        System.out.println("Fechando conexão com o cliente");
        sc.close();
    }
}