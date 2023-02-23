import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Mercadona extends Thread {

	DataInputStream in;
	DataOutputStream out;
	Socket socket;

	public Mercadona(DataInputStream in, DataOutputStream out, Socket socket) {
		this.in = in;
		this.out = out;
		this.socket = socket;
	}

	public Mercadona() {

	}

	public void run() {
		try {
			String s = in.readUTF();
			HiloCliente.ListaProductos(s);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}