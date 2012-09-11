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
		String line=null;
		try{
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new PrintWriter(socket.getOutputStream());
			
			while((line = is.readLine())!=null){
				try {
					System.out.println("Server : reading data from server "+line);
				} 
				catch (Exception e) {
					System.out.println("Error while reading data from socket");
					e.printStackTrace();
				}
				os.println(line);
				System.out.println("Server : wrote data by server "+line);
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
		server.readWriteSocket();
		server.closeSocket();
	}

}
	
