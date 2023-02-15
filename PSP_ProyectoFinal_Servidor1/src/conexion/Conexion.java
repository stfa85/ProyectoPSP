package conexion;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion {
	public static void main(String[] args) throws IOException {
		//conexion al servidor1
        
        ServerSocket serverSocket = new ServerSocket(3000);

        // Cuando se coencta un cliente crea un objeto Socket
        Socket servidor = serverSocket.accept();

        // Crea un objeto PrintWriter para enviar datos al cliente
        PrintWriter out = new PrintWriter(servidor.getOutputStream(), true);

        // Probamos a enviar un mensaje
        out.println("Conexion con Mercadona establecida!");

        // Cierra el socket y el ServerSocket
        servidor.close();
        serverSocket.close();
    }
}
