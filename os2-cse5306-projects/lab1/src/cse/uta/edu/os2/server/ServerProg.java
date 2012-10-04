package cse.uta.edu.os2.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashSet;

import cse.uta.edu.os2.server.gui.ServerWindow;

/**
 * This class is responsible for creating the server socet and waits the 
 * clients sockets and for each client it creates a thread 
 *  
 * @author lakshmanas
 *
 */
public class ServerProg {

	private ServerSocket serverSocket=null;
	private Socket socket=null;
	private ServerWindow serverWindow=null;
	private FileDictionary fileDict = new FileDictionary();

	public ServerProg(ServerWindow window){
		this.serverWindow=window;
	}

	/**
	 * this method creates the server socket and waits the client to accept the connection
	 * for each client it creates a thread , which will process each client request
	 */
	public void listenToClients(){
		boolean listen=true;
		try{
			//creates a server socket on port 1234
			serverSocket = new ServerSocket(1234);
			System.out.println("Server :Server starting to listen");
			while(listen){
				//waits for the client to accept the connection
				socket = serverSocket.accept();
				//for each client accepted request it creates a thread 
				 Thread t  = new Thread( new Runnable() {
					//input & output stream for each client 
					BufferedReader inStream=null;
					PrintWriter outSteam=null;
					//thread run method with for each client process it's request
					public void run(){
						try {
							System.out.println(this.getClass().getName()+" a new thread created for a client");
							inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							outSteam = new PrintWriter(socket.getOutputStream(),true);
							String clntMsg =null;
							//reads the client message
							while( (clntMsg =recieveMessage(inStream))!=null){
								if(clntMsg!=""){
									System.out.println(this.getClass().getName()+ " " +clntMsg +" received");
									// searches the dictionary for synonym list
									HashSet<String> wordSynonyms = fileDict.getSynonyms(clntMsg);
									String msg ="Client : "+clntMsg+"\n";
									String synonys=null;
									//checks the synonyms list for size 
									if(wordSynonyms!=null && wordSynonyms.size()>0){
										synonys=wordSynonyms.toString();
										msg=msg+"Server :" + synonys+"\n";
										//sends the synonym list to the client
										sendMessage(outSteam,synonys);
										System.out.println(this.getClass().getName()+ " " +synonys+" sent");
									}else{
										//if it's empty displays the message
										msg=msg+"No Synonyms found \n";
										System.out.println(this.getClass().getName()+" No Synonyms found for this word " +clntMsg );
										sendMessage(outSteam,"NA");
									}
									synchronized (this) {
										serverWindow.setTextArea(msg);
										serverWindow.refresh();
									}	
								}
							}
						
						}
						catch(SocketException e){
							System.out.println("Sever shutdown !!! ");
					
						}
						catch (IOException e) {
							System.out.println(this.getClass().getName()+" Excpetion while creating input/output stream for client");
							e.printStackTrace();
						}
						// on each thread closing it's closing the client stream 
						finally{
							try {
								inStream.close();
							} catch (IOException e) {
								System.out.println(this.getClass().getName()+" Exception while closing the the input/output stream");
								e.printStackTrace();
							}
							outSteam.close();
						}
						
					}
				});
				 t.start();
			}
	}
	catch(SocketException e){
		System.out.println("Sever shutdown !!! ");
		listen=false;
	}
	catch(IOException e)
	{
		System.out.println("Server :problem setting server socket to 1234");
		e.printStackTrace();
		listen=false;
	}

		
	}
	/**
	 * this method sends the message to the client 
	 * @param outStream
	 * @param msg
	 */
	public void sendMessage(PrintWriter outStream, String msg){
		if(msg!=null){
			outStream.println(msg);
		}
		
	}

	/** 
	 * this method reads the message from the client and gives it to the thread for 
	 * processing
	 * @param inStream
	 * @return
	 */
	public String recieveMessage(BufferedReader inStream){
		String msg=null;
		try {
			//reads the client output stream for a message
			msg =inStream.readLine();
		}
		catch(SocketException e){
			System.out.println("Client Connection reset, clinent disconnected");
		}
		catch (IOException e) {
			System.out.println(this.getClass().getName()+" Exception while recieving data from client");
			e.printStackTrace();
		}
		return msg;
		}

	/**
	 * this method closes the server socket connection and cleans up
	 */
	public void closeSocket(){
		try {
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("Error while closing the socket ");
			e.printStackTrace();
		}
	}
	
/*	public void readWriteSocket(){
		String line=null,text="list of Synonyms";
		try{
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
	
*/
	public static void main(String args[]){
	}

}
	
