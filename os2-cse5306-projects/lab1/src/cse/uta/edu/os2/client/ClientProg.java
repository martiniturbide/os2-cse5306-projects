package cse.uta.edu.os2.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientProg {
	
	private Socket socket=null;
	private BufferedReader is=null;
	private PrintWriter os=null;
	
	public ClientProg()
	{
		try {
			socket = new Socket("localhost",1234);
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new PrintWriter(socket.getOutputStream(),true);
		}
		catch (UnknownHostException e) {
			System.out.println("unknown host ");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error connecting to server socket");
			e.printStackTrace();
			System.exit(-1);
		}
		 	
	}
	
	public void readWriteScoket(){
		
		String text="Hi, there, How u doing today ?";
		System.out.println("Sent to server +"+text);
		os.println(text);
		try {
			String line =is.readLine();
			System.out.println("recieved from server "+line);
		} catch (IOException e) {
			System.out.println("Reading from socket failed");
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		ClientProg client = new ClientProg();
		client.readWriteScoket();
		
	}

}
