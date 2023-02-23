package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MercadonaHilo extends Thread {
	Socket socket;
	DataInputStream in;
	DataOutputStream out;

	public MercadonaHilo(Socket socket, DataInputStream in, DataOutputStream out) {
		this.socket = socket;
		this.in = in;
		this.out = out;
	}

	public MercadonaHilo() {

	}

	public void run() {
		String producto2 = "El precio de la maicena 200gr en Mercadona es de 2.20€";
		try {
			out.writeUTF(producto2);
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
