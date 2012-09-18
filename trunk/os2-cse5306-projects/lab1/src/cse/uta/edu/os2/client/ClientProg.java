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
			socket = new Socket("127.0.0.1",1234);
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new PrintWriter(socket.getOutputStream(),true);
		}
		catch (UnknownHostException e) {
			System.out.println("client : unknown host ");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("client : Error connecting to server socket");
			e.printStackTrace();
			System.exit(-1);
		}
		 	
	}
	
	public void readWriteScoket(){
		String line=null;
		String text="Hi, there, How u doing today ?";
		System.out.println("client :");
		BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
		try {
			while((text = sysIn.readLine())!=null){
				os.println(text);
				line =is.readLine();
				System.out.println("server : "+line);
				System.out.println("client : ");
			}
		} catch (IOException e) {
			System.out.println("client : Reading from socket failed");
			e.printStackTrace();
		}
	}
	
	
	public void sendMessage(String msg){
		if(msg!=null){
			os.println(msg);
		}
		
	}

	
	public String recieveMessage(){
		String msg=null;
		try {
			msg =is.readLine();
		} catch (IOException e) {
			System.out.println(this.getClass().getName()+" Exception while recieving data from server");
			e.printStackTrace();
		}
		return msg;
		}
	
	public void closeSocket(){
		try {
			is.close();
			os.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("client : Error while closing the socket ");
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		ClientProg client = new ClientProg();
		client.sendMessage("augment");
		System.out.println(client.recieveMessage());;
		//client.readWriteScoket();
		client.closeSocket();
		
	}

}
