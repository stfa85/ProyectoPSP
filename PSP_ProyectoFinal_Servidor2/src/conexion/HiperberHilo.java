package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiperberHilo extends Thread {
	Socket socket;
	DataInputStream in;
	DataOutputStream out;

	public HiperberHilo(Socket socket, DataInputStream in, DataOutputStream out) {
		this.socket = socket;
		this.in = in;
		this.out = out;
	}

	public HiperberHilo() {

	}

	public void run() {
		String producto1 = "El precio de la maicena 200gr en Hiperber es de 2€";
		try {
			out.writeUTF(producto1);
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
