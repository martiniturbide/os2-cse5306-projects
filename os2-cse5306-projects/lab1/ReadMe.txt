This project contains src folder, ReadMe.txt and Dictionary.txt
src folder has all the java source files
Dictionary.txt is where the words are stored for synonym look up 

src folder package is organized as follow 
src 
	- cse.uta.edu.os2.cli : contains the client side program which connects to server socket
	- cse.uta.edu.os2.cli.gui : contains the code to create and display the client side GUI
	- cse.uta.edu.os2.server : contains the server side program which connects to client socket and look up for the file dictionary
	- cse.uta.edu.os2.server.gui : contains the code to create and display the server side GUI
	
	

Running the Application: 
	To run this application first you need to run the server program first 
							and then run the client program below are the step by step instruction to run
	1. open the cse.uta.edu.os2.server.gui.ServerWindow , right click run as Java application
	2. open the cse.uta.edu.os2.client.gui.ClientWindow , right click run as Java application
	3. now you have two windows opened one is server and other is client 
	4. Go to the client window, enter any keyword in the search box at the top of the menu and 
	    press enter, this will fetch the synonym list and displays at the left 
	5. Another way to is enter any text in the client window text area at the center, double clicking on the 
		entered text will highlight the text, now if you right click on the text window will make request and
		displays the synonyms list for that word in the left side.
	6. To replace the word in the client text window, select the word needs to replaced using mouse and then 
		double click on any word in the left side word list, this will replace the selected word.
	
	
