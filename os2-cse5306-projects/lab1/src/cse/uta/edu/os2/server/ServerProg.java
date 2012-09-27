package cse.uta.edu.os2.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

import net.sf.extjwnl.JWNL.OS;

import cse.uta.edu.os2.server.gui.ServerWindow;

public class ServerProg {

	private ServerSocket serverSocket=null;
	private Socket socket=null;
	private ServerWindow serverWindow=null;
	private FileDictionary fileDict = new FileDictionary();
/*	private BufferedReader is=null;
	private PrintWriter os=null;
*/
	public ServerProg(ServerWindow window){
		this.serverWindow=window;
/*		try{
			serverSocket = new ServerSocket(1234);
			socket = serverSocket.accept();
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new PrintWriter(socket.getOutputStream(),true);
			System.out.println("Server :Server starting to listen");
		}
		catch(IOException e)
		{
			System.out.println("Server :problem setting server socket to 1234");
			e.printStackTrace();
			System.exit(-1);
		}
*/	}

	public void listenToClients(){
		try{
			serverSocket = new ServerSocket(1234);
			System.out.println("Server :Server starting to listen");
			while(true){
				socket = serverSocket.accept();
				 Thread t  = new Thread( new Runnable() {

					BufferedReader inStream=null;
					PrintWriter outSteam=null;

					public void run(){
						try {
							System.out.println(this.getClass().getName()+" a new thread created for a client");
							inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							outSteam = new PrintWriter(socket.getOutputStream(),true);
							String clntMsg =null;
							while( (clntMsg =recieveMessage(inStream))!=null){
								if(clntMsg!=""){
									System.out.println(this.getClass().getName()+ " " +clntMsg +" received");
									HashSet<String> wordSynonyms = fileDict.getSynonyms(clntMsg);
									String msg ="Client : "+clntMsg+"\n";
									String synonys=null;
									if(wordSynonyms!=null && wordSynonyms.size()>0){
										synonys=wordSynonyms.toString();
										msg=msg+"Server :" + synonys+"\n";
										sendMessage(outSteam,synonys);
										System.out.println(this.getClass().getName()+ " " +synonys+" sent");
									}else{
										msg=msg+"No Synonyms found \n";
										System.out.println(this.getClass().getName()+" No Synonyms found for this word " +clntMsg );
										sendMessage(outSteam,"NA");
									}
									synchronized (this) {
										serverWindow.setTextArea(msg);
									}	
								}
							}
						
						} catch (IOException e) {
							System.out.println(this.getClass().getName()+" Excpetion while creating input/output stream for client");
							e.printStackTrace();
						}
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
	catch(IOException e)
	{
		System.out.println("Server :problem setting server socket to 1234");
		e.printStackTrace();
		System.exit(-1);
	}

		
	}
	
	public void sendMessage(PrintWriter outStream, String msg){
		if(msg!=null){
			outStream.println(msg);
		}
		
	}

	public String recieveMessage(BufferedReader inStream){
		String msg=null;
		try {
			msg =inStream.readLine();
		} catch (IOException e) {
			System.out.println(this.getClass().getName()+" Exception while recieving data from client");
			e.printStackTrace();
		}
		return msg;
		}

	
	public void closeSocket(){
		try {
/*			is.close();
			os.close();
*/			socket.close();
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
	
