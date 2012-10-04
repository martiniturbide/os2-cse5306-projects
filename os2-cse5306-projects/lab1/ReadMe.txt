Project Description : 
		The client process will have a GUI interface and will
	allow the user to select a word in a block of text and have the system send a query to a
	server to look up the word in a thesaurus file and return a list of alternative words from
	the server. Your client process will connect to the server over a socket connection and
	send the word that the user has selected in an input text box. The server will return a
	string that will contain alternative words that the user might use instead of the input
	word. These alternative words will be separated by commas or semicolons. The string
	returned for the word “abandon” might look like this: “drops, dumps, ditches, discards;
	deserts, leaves; ends, gives up; looses, empties, vacates, resigns, quits”. The client will
	display the chosen words to the user and allow him or her to choose one of the options.
	The client will replace the initial word in the text box with the selected alternative and
	allow the user to select another word.


Packet structure :
------------------ 
	This project contains src folder, ReadMe.txt and Dictionary.txt
	src folder has all the java source files
	Dictionary.txt is where the words are stored for synonym look up
	 

Implementation : As part of this project the following features have been implemented and for extra credit multi-threading feature is added
---------------
	1. Client & Server process communication vis sockets
	2. Client GUI to allow user the enter the word and search for the synonyms and display the synonym list
	3. Client GUI to allow user to open file/ enter text in the main text panel and select the text and on left click
		fetch synonym list from server and display at the left panel
	4. Client GUI to clear and open a new text window at the center
	5. Client GUI to user to select a word from the synonym list in the left panel and replace the selected text in the main text area
	6. Server GUI to display the word received from client and synonym fetched from dictionary for that word
	7. Server GUI to clear the log contents 
	8. Server GUI to stop the server 
	9. Multi-threaded server program to handle each client request by a separate thread
	10. Dictionary implementation using files, file will have list of similar words in each line, so request for synonyms for a 
		word will look for that word in the file and will fetch all the words in that line, which is basically synonyms for that word
 


Package Structure :
-------------------
	src folder package is organized as follows 
	src-> 
		- cse.uta.edu.os2.cli : contains the client side program which connects to server socket
		- cse.uta.edu.os2.cli.gui : contains the code to create and display the client side GUI
		- cse.uta.edu.os2.server : contains the server side program which connects to client socket and look up for the file dictionary
		- cse.uta.edu.os2.server.gui : contains the code to create and display the server side GUI
	
	

Running the Application:
------------------------ 
	To run this application first you need to run the server program first 
							and then run the client program below are the step by step instruction to run
	1. open the cse.uta.edu.os2.server.gui.ServerWindow , right click run as Java application
	2. open the cse.uta.edu.os2.client.gui.ClientWindow , right click run as Java application
	3. now you have two windows opened one is server and other is client 
	4. Go to the client window, enter any keyword in the search box at the top of the menu and 
	    press enter, this will fetch the synonym list and displays in the left panel 
	5. Another way to run is by typing any text in the client window text area at the main window, double clicking on the 
		entered text will highlight the text, now if you left click on the text window , this will make a request 
		to the server and displays the synonyms list for that word in the left panel.
	6. To replace the word in the client text window, select the word which needs to replaced using mouse and then 
		double click on any word in the word list from left panel, this will replace the selected word.
	
	7. For Extra credit i have implemented the server as multi-threaded program so for each client request a new thread will be created
		and which will handle the  client request/response
	
	8. To test the extra credit run the ClientWindow program multiple times so each time you run it will create a new window and 
		establishes a connection to the server. repeat the step 4 or 5 to test each client
		
		 