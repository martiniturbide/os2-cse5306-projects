Author : Lakshmana Srinivasa (1000789751)

Project Description : 
---------------------
		The client process will have a GUI interface and will
	allow the user to select a misspelled word in a search field and have the system send a query to a
	client to look up the suggested word from a webservice and return a list of suggested words from
	the server. Client process will make a call to the webservice and send the word that 
	the user has selected in an input text box. The web service will return a list of
	sugested word that the user might use instead of the input word. 


Software : Used JDK 6 and saaj-impl-1.3.4.jar to implement this project.   
----------   i have provided the required jar in the lib folder

			
Packet structure :
------------------ 
	This project contains src folder, lib folder &  SpeelCheck_ReadMe.txt 
	src folder package is organized as follows 
	
	src-> 
		- cse.uta.edu.os2.client.SpellCheckClient : contains the client side program which calls the web service
		- cse.uta.edu.os2.client.gui.SpellChecClientWindow : contains the code to create and display the client side GUI
	
	lib -> saaj-impl-1.3.4.jar
	
	 

Implementation : As part of this project the following features have been implemented and for extra credit multi-threading feature is added
---------------
	1. Client GUI to allow user the enter the misspelled word and gets the suggested list of words 
	2. Client GUI allows user to select a word from the suggested list in the left panel and replace the text in the search box
	3. Client GUI displays the request and response soap XML at the center Text area for each request.
	

Extra credit-- Processing the response XML and fetching the list of suggested word and allow user to replace the entered 
------------	Misspelled word 
	

Package Structure :
-------------------
	
	
	
	

Running the Application:
------------------------ 
	To run this application first you need to run the server program first 
							and then run the client program below are the step by step instruction to run
	1. open the cse.uta.edu.os2.client.gui.SpellChecClientWindow , right click run as Java application
	2. Go to the client window, enter any keyword in the search box at the top of the menu and 
	    click search button or press enter, this will fetch the list of suggested word and displays in the left panel 
	3. To replace the word in the search text box, then double click on any word in the word list from left panel, 
		this will replace the search word

	
	
		
		 