package Conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ConexionCliente {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {

		try {

			Socket socket = new Socket("localhost", 3000);

			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			System.out.println("1 -- Registro");
			System.out.println("2 -- Login");
			String opcion = sc.nextLine();
			out.writeUTF(opcion);

			switch (opcion) {
			case "1":
				System.out.println("Usuario: ");

				// Recoje el nombre y se lo manda al servidor
				
				String usuario = sc.nextLine();
				
				out.writeUTF(usuario);
				
				// mensaje de respuesta sysout
				String mensaje = in.readUTF();
				System.out.println(mensaje);
				
				if (mensaje.equals("Usuario ya existe ")){
					ConexionCliente.main(args);
				}
				//System.out.println("Contraseña: ");

				String pass = sc.nextLine();
				out.writeUTF(pass);
				String registro = in.readUTF();
				System.out.println(registro);
				if (registro.equals("Usuario y contraseña registrados")) {
					// ejecutamos el hilo
					ClienteHilo hilo = new ClienteHilo(in, out, socket);
					hilo.start();
					hilo.join();
				} else {
					ConexionCliente.main(args);
				}
				break;
			case "2":
				System.out.println("Usuario: ");

				// Escribe el nombre y se lo manda al servidor
				usuario = sc.nextLine();
				out.writeUTF(usuario);

				System.out.println("Contraseña: ");

				pass = sc.nextLine();
				out.writeUTF(pass);
				String login = in.readUTF();
				System.out.println(login);
				if (login.equals("Usuario y contraseña correctos ")) {
					// ejecutamos el hilo
					ClienteHilo hilo = new ClienteHilo(in, out, socket);
					hilo.start();
					hilo.join();
				} else {
					ConexionCliente.main(args);
				}
				break;
			default:
				ConexionCliente.main(args);
				break;

			}
		} catch (Exception e) {
			System.out.println("Error en cliente ");
			e.printStackTrace();
		}

	}
}
