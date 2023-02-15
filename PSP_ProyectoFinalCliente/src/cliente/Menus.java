package cliente;

import java.util.Scanner;

public class Menus {
	static Scanner sc = new Scanner(System.in);

	public static void IniciarMenu() {

		System.out.println("****************MENU USUARIO****************");
		System.out.println("----------------------------------------------");
		System.out.println("Escribe el nombre del articulo a buscar: ");
		System.out.println("(Solo dados de alta Maizena y Pan de molde) ");
		System.out.println("----------------------------------------------");

		String producto = sc.nextLine();

		// Mi menu
		switch (producto.toLowerCase()) {
		case "maizena":
			consultaMaizena();
			break;
		case "pandemolde":
			consultaPanMolde();
			break;
		}
	}

	public static void consultaMaizena() {
		// Tendría que comprobar si deberia hacer una clase conexion para cada
		// supermercado, o igual tendria que traerme la conexion a esta misma clase
		
		
		/*
		 * System.out.println("Respuesta Mercadona: " + Conexion.Conexion.inMercadona);
		 * 
		 * 
		 * System.out.println("Respuesta Hiperber: " + Conexion.Conexion.inHiperber);
		 */

		// Consultar en Mercadona

		// Consultar en Hiperber

	}

	public static void consultaPanMolde() {

		/*
		 * System.out.println("Respuesta Mercadona: " + Conexion.Conexion.inMercadona);
		 * 
		 * 
		 * System.out.println("Respuesta Hiperber: " + Conexion.Conexion.inHiperber);
		 */

		// Consultar en Mercadona

		// Consultar en Hiperber

	}

}
