import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConexionPpal {
	public static File f = new File("usuarios.txt");

	public static void main(String[] args) {

		try {

			if (!f.exists()) {
				f.createNewFile();
			}
			HashMap<String, String> hashMap = new HashMap<>();
			guardarHashEncriptado(hashMap);

			ServerSocket serverSocket = new ServerSocket(3000);

			System.out.println("Servidor iniciado");

			while (true) {
				// Cuando se coencta un cliente crea un objeto Socket
				Socket socket = serverSocket.accept();

				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				ArrayList<String> listaProductos = new ArrayList<String>();

				String opcion = in.readUTF();
				String usuario;
				String pass;
				
				switch (opcion) {
				case "1":
					System.out.println("Esperando usuario: ");
					usuario = in.readUTF();
					if (hashMap.containsKey(usuario)) {
						out.writeUTF("Usuario ya existe ");
						socket.close();
					} else {
						out.writeUTF("Contraseña: ");
						pass = in.readUTF();
						hashMap.put(usuario, pass);
						out.writeUTF("Usuario y contraseña registrados");
						// Inicio el hilo
						HiloCliente hilo = new HiloCliente(socket, in, out, usuario, listaProductos);
						hilo.start();
						
						escribirTXTEncriptado(hashMap);
					}
					break;
				case "2":
					usuario = in.readUTF();
					pass = in.readUTF();
					if (hashMap.containsKey(usuario) && hashMap.get(usuario).equals(pass)) {
						out.writeUTF("Usuario y contraseña correctos ");
						// Inicio el hilo
						HiloCliente hilo = new HiloCliente(socket, in, out, usuario, listaProductos);
						hilo.start();

						System.out.println("Creada la conexion con el cliente " + usuario);
					} else {
						out.writeUTF("Usuario o contraseña incorrectos ");
						socket.close();
					}
					break;
				default:
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Error en conexionServidor ");
			e.printStackTrace();
		}
	}

	private static void escribirTXTEncriptado(HashMap<String, String> hashMap) {
	    try {
	        FileWriter writer = new FileWriter(f);
	        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
	            String key = entry.getKey();
	            String value = entry.getValue();
	            String valueEncriptado = encriptar(value);
	            writer.write(key + ":" + valueEncriptado + "\n"); // escribir en el archivo
	        }
	        writer.close(); // cerrar el archivo
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private static void guardarHashEncriptado(HashMap<String, String> hashMap) {
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(f));
	        String line = reader.readLine();

	        while (line != null) {
	            String[] parts = line.split(":");
	            String key = parts[0].trim();
	            String value = parts[1].trim();
	            String valueEncriptado = encriptar(value);
	            hashMap.put(key, valueEncriptado);
	            line = reader.readLine();
	        }
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private static String encriptar(String texto) {
	    String resultado = "";
	    for (int i = 0; i < texto.length(); i++) {
	        char caracter = texto.charAt(i);
	        if (Character.isUpperCase(caracter)) {
	            resultado += (char) (((int) caracter + 4 - 65) % 26 + 65);
	        } else {
	            resultado += (char) (((int) caracter + 4 - 97) % 26 + 97);
	        }
	    }
	    return resultado;
	}
}
