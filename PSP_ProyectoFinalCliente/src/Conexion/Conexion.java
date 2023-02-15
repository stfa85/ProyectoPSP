package Conexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Conexion {
	public static void main(String[] args) throws IOException {
		// Crea un objeto Socket para conectarse al servidor1
		Socket clienteMercadona = new Socket("localhost", 3000);

		// Crea un objeto BufferedReader para leer los datos del servidor
		BufferedReader inMercadona = new BufferedReader(new InputStreamReader(clienteMercadona.getInputStream()));

		// Imprime la linea enviada del servidor
		System.out.println(inMercadona.readLine());

		// Cierra el socket
		clienteMercadona.close();

		// asigno el Socket para conectarse al servidor2
		Socket clienteHiperber = new Socket("localhost", 3001);

		// guardo en objeto BufferedReader los datos del servidor2
		BufferedReader inHiperber = new BufferedReader(new InputStreamReader(clienteHiperber.getInputStream()));

		// Imprime la linea enviada del servidor2
		System.out.println(inHiperber.readLine());

		// Cierra el socket
		clienteHiperber.close();
	}
}
