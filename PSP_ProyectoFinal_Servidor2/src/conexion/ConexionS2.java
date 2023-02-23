package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ConexionS2 {

	public static void main(String[] args) {
		Socket socket;

		try {

			// conexion al servidor2

			ServerSocket serverSocket = new ServerSocket(3003);
			while (true) {
				// Cuando se coencta un cliente crea un objeto Socket
				socket = serverSocket.accept();

				DataInputStream in = new DataInputStream(socket.getInputStream());
	            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		           
	            // Inicio el hilo
	            HiperberHilo hilo = new HiperberHilo(socket, in, out);
	            hilo.start();

			}
		} catch (Exception e) {
			

		}

	}

}
