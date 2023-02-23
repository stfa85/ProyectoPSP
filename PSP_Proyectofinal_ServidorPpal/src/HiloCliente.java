import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.util.ArrayList;


public class HiloCliente extends Thread {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private DataInputStream inHiperber;
	private DataOutputStream outHiperber;
	private DataInputStream inMercadona;
	private DataOutputStream outMercadona;
	private String nombreCliente;
	private static ArrayList<String> listaProductos = new ArrayList<String>();

	public HiloCliente(Socket socket, DataInputStream in, DataOutputStream out, String nombreCliente, ArrayList<String> listaProductos) {
		this.socket = socket;
		this.in = in;
		this.out = out;
		this.nombreCliente = nombreCliente;
	}

	@Override
	public void run() {

		String opcion;
		File f = new File("articulos.txt");
		boolean salir = false;
		while (!salir) {

			try {
				opcion = in.readUTF();
				switch (opcion) {
				case "1":
					// Recibo el usuario
					System.out.println("Usuario: " + nombreCliente);
					// Mando el mensaje de confirmacion al cliente
					out.writeUTF("Acceso aprobado ");
					break;

				case "2":
					conectarMercadona();
					conectarHiperber();
					out.writeInt(listaProductos.size());
					for(String s: listaProductos) {
					out.writeUTF(s);
					}

					break;

				default:
					salir = true;
					break;
				}

			} catch (Exception e) {
				System.out.println("Error en el servidor cliente " + nombreCliente + " desconexion inesperada.");
				salir = true;
			}

		}

		try {
			// Cierro el socket
			socket.close();
		} catch (Exception e) {
			System.out.println("Error cerrando el socket ");
			e.printStackTrace();
		}

		System.out.println("Conexion cerrada con el cliente " + nombreCliente);

	}

	private void conectarHiperber() {
		try {
		    		    
		    Socket socketHiperber = new Socket("localhost", 3003);
		    
		    inHiperber = new DataInputStream(socketHiperber.getInputStream());
		    outHiperber = new DataOutputStream(socketHiperber.getOutputStream());
		    
		    
		    // ejecutamos el hilo
		    Hiperber hilo = new Hiperber(inHiperber, outHiperber, socketHiperber);
		    hilo.start();
		    hilo.join();
		    
		} catch (Exception e) {
			System.out.println("Error en cliente ");
			e.printStackTrace();
			
		}
	}
	public static synchronized void ListaProductos(String s) {
		listaProductos.add(s);
	}
	
	private void conectarMercadona() {
		try {
		    		    
		    Socket socketMercadona = new Socket("localhost", 3002);
		    
		    inMercadona = new DataInputStream(socketMercadona.getInputStream());
		    outMercadona = new DataOutputStream(socketMercadona.getOutputStream());
		    
		    
		    // ejecutamos el hilo
		    Mercadona hilo = new Mercadona(inMercadona, outMercadona, socketMercadona);
		    hilo.start();
		    hilo.join();
		    
		} catch (Exception e) {
			System.out.println("Error en cliente ");
			e.printStackTrace();
			
		}
	}
	

}