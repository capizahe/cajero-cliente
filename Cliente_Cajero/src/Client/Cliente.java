package Client;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

import GUI.MainView;
import Proceso.Proceso;

public class Cliente implements Runnable {

	public static final int PORT = 5000;
	public static final String SERVER = "localhost";
	public Proceso proceso;
	public Boolean exit;
	public Boolean en_curso;
	public String saldo; 

	public Cliente(Proceso proceso) {
		this.proceso = proceso;
	}

	public static void main(String[] args) {

	}

	@Override
	public void run() {

		try {
			exit = false;
			System.out.println("Cliente> Inicio");
			while(!exit) {

				Socket socket = new Socket(SERVER,PORT);
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintStream output = new PrintStream(socket.getOutputStream());
				System.out.println("Cliente> Escriba comando");
				String request = null;
				if(this.proceso != null) {
					request = proceso.toString();
					output.println(request);
				}
				String st = input.readLine();
				if( st != null ) {
					StringTokenizer st1 = new StringTokenizer(st,"->");
					st1.nextToken();st1.nextToken();
					System.out.println("Servidor> "+st);
					saldo = st1.nextToken();
					System.out.println("el saldo es "+ saldo);
					en_curso=true;
				}
					exit=true;
					System.out.println("Cliente> Fin del programa");
				socket.close();

			}

		}catch(IOException ex) {
			System.out.println("Cliente>" +ex.getMessage());

		}


	}

}
