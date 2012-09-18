package cse.uta.edu.os2.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProg {

	private ServerSocket serverSocket=null;
	private Socket socket=null;
	private BufferedReader is=null;
	private PrintWriter os=null;
	
	public ServerProg(){
		try{
			serverSocket = new ServerSocket(1234);
		}
		catch(IOException e)
		{
			System.out.println("Server :problem setting server socket to 1234");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void listenServerSocket(){
		
		try{
				System.out.println("Server :Server starting to listen");
				socket = serverSocket.accept();
		}
		catch (IOException e) {
			System.out.println("Server :problem listening to server socket");
			e.printStackTrace();
		}
	}
	
	public void readWriteSocket(){
		String line=null,text="list of Synonyms";
		try{
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
			while((line = is.readLine())!=null){
				try {
					System.out.println("Client : "+line);
					System.out.println("Server : ");
					os.println(text);						
			
				} 
				catch (Exception e) {
					System.out.println("Error while reading data from socket");
					e.printStackTrace();
				}
			}
		}
		catch(IOException e){
			System.out.println("Server :Error reading/writing data from socket");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("Server :Error while setting up read/write from/to  socket");
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
			System.out.println(this.getClass().getName()+" Exception while recieving data from client");
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
			System.out.println("Error while closing the socket ");
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		ServerProg server = new ServerProg();
		server.listenServerSocket();
		System.out.println(server.recieveMessage());
		server.sendMessage("Hello");
		//server.readWriteSocket();
		server.closeSocket();
	}

}
	
