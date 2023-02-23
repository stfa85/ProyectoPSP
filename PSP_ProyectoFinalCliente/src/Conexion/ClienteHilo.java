package Conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteHilo extends Thread {
	private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    
    public ClienteHilo(DataInputStream in, DataOutputStream out, Socket socket) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {

        Scanner sc = new Scanner(System.in);

        String mensaje;
        String opcion;
        boolean salir = false;

        while (!salir) {

            try {
                System.out.println("1 -- Login");
                System.out.println("2 -- Productos");
                System.out.println("3 -- Salir");
                
                opcion = sc.nextLine();
                out.writeUTF(opcion);
                
                switch (opcion) {
                    case "1":
                        
                        mensaje = in.readUTF();
                        System.out.println(mensaje);
                        break;
                    case "2":
                    	//bucle for hasta terminar el array
                    	int s = in.readInt();
                    	for(int i=0; i<s; i++) {
                    		System.out.println(in.readUTF());
                    	}
                        break;
                    default:
                        salir = true;
                        break;

                }
            } catch (Exception e) {
            	System.out.println("Error en hiloCliente ");
				e.printStackTrace();
				salir = false;
            }

        }

    }
    
}
