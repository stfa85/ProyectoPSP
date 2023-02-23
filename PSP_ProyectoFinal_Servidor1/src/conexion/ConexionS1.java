package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ConexionS1 {
	
	public static void main(String[] args) throws IOException {
		Socket socket;

		try {

			// conexion al servidor2

			ServerSocket serverSocket = new ServerSocket(3002);
			while (true) {
				// Cuando se coencta un cliente crea un objeto Socket
				socket = serverSocket.accept();

				DataInputStream in = new DataInputStream(socket.getInputStream());
	            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		           
	            // Inicio el hilo
	            MercadonaHilo hilo = new MercadonaHilo(socket, in, out);
	            hilo.start();

			}
		} catch (Exception e) {
			

		}

	}

}
