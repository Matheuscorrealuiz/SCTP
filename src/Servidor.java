import com.sun.nio.sctp.*;
import java.io.*;
import java.net.*;

public class Servidor {
    static ServerSocket reception;

    public static void main(String[] args) throws Exception {
        SctpServerChannel ssc = SctpServerChannel.open();
        reception = new ServerSocket(4444);

        Socket connection = reception.accept();
        ssc.bind(reception.getLocalSocketAddress());

        ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());

        String clientMsg = (String) inputStream.readObject();

        while (!clientMsg.equalsIgnoreCase("sair")) {
            System.out.println("Conectado ao cliente");

            System.out.println("Esperando mensagem...");

            clientMsg = (String) inputStream.readObject();

            System.out.println("Servidor...      Total de bytes recebidos " + clientMsg.getBytes().length);

            System.out.println("Mensagem recebida do cliente: " + clientMsg);

            if (clientMsg.equalsIgnoreCase("Sair")){
                break;
            }
        }
        ssc.close();
        System.out.println("Servidor.....    Fechando conexão");
    }
}