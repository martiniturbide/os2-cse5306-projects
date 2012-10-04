package cse.uta.edu.os2.client.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import cse.uta.edu.os2.client.ClientProg;

/**
 * this class involves the Client GUI methods and variables
 * running this method will display the client GUI
 * @author lakshmana s(1000789751)
 *
 */
public class ClientWindow {

	private JFrame frame;
	private JEditorPane textArea = new JEditorPane();
	private JButton fileNewButton = new JButton("");
	private JButton fileOpenButton = new JButton("");
	private JButton srchButton = new JButton("Search");
	private JTextField srchField = new JTextField();
	private JFileChooser fileChooser = new JFileChooser();
	private ClientProg client = new ClientProg();
	private DefaultListModel listModel = new DefaultListModel();
	private JList list = new JList(listModel);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//creatin a thread to run the client GUI
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//client object is created and made visible 
					ClientWindow window = new ClientWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					System.out.println(this.getClass().getName() +" Exception while starting the applicaton ");
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application and initialize the client GUI components
	 */
	public ClientWindow() {
		initialize();
	}

	/**
	 * This method creates and Initializes the contents of the frame.
	 */
	private void initialize() {
		// a frame is created for the client
		frame = new JFrame();
		frame.setResizable(false);
		frame.setMinimumSize(new Dimension(1024, 650));
		frame.setMaximumSize(new Dimension(1024, 600));
		frame.setBounds(100, 30, 1018, 723);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		// a panel is created inside the frame
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 15, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -655, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -17, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		sl_panel.putConstraint(SpringLayout.WEST, srchButton, 15, SpringLayout.EAST, srchField);
		sl_panel.putConstraint(SpringLayout.EAST, srchField, -433, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, srchButton, -327, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, srchField, 0, SpringLayout.NORTH, fileNewButton);
		sl_panel.putConstraint(SpringLayout.SOUTH, srchField, 0, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, srchButton, 0, SpringLayout.NORTH, fileNewButton);
		sl_panel.putConstraint(SpringLayout.SOUTH, srchButton, 0, SpringLayout.SOUTH, fileOpenButton);
		sl_panel.putConstraint(SpringLayout.NORTH, fileNewButton, 0, SpringLayout.NORTH, fileOpenButton);
		sl_panel.putConstraint(SpringLayout.SOUTH, fileNewButton, 0, SpringLayout.SOUTH, fileOpenButton);
		sl_panel.putConstraint(SpringLayout.WEST, fileOpenButton, 17, SpringLayout.EAST, fileNewButton);
		sl_panel.putConstraint(SpringLayout.EAST, fileNewButton, 71, SpringLayout.WEST, panel);
		panel.setLayout(sl_panel);
		sl_panel.putConstraint(SpringLayout.WEST, fileNewButton, 21, SpringLayout.WEST, panel);
		panel.add(fileNewButton);
		fileNewButton.setIcon(new ImageIcon(ClientWindow.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		
		
		sl_panel.putConstraint(SpringLayout.NORTH, fileOpenButton, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, fileOpenButton, 0, SpringLayout.SOUTH, panel);
		panel.add(fileOpenButton);
		fileOpenButton.setIcon(new ImageIcon(ClientWindow.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		
		JLabel lblSearch = new JLabel("Keyword");
		sl_panel.putConstraint(SpringLayout.WEST, srchField, 18, SpringLayout.EAST, lblSearch);
		sl_panel.putConstraint(SpringLayout.EAST, lblSearch, -735, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblSearch, 42, SpringLayout.EAST, fileOpenButton);
		sl_panel.putConstraint(SpringLayout.NORTH, lblSearch, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblSearch, 0, SpringLayout.SOUTH, panel);
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblSearch);
		panel.add(srchField);
		srchField.setMinimumSize(new Dimension(6, 10));
		srchField.setColumns(10);
		
		JPanel textPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, textPanel, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, textPanel, -22, SpringLayout.EAST, frame.getContentPane());
		textPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		JScrollPane scrollText = new JScrollPane(textArea);
		frame.getContentPane().add(textPanel);
		SpringLayout sl_textPanel = new SpringLayout();
		sl_textPanel.putConstraint(SpringLayout.NORTH, scrollText, 0, SpringLayout.NORTH, textPanel);
		sl_textPanel.putConstraint(SpringLayout.WEST, scrollText, 0, SpringLayout.WEST, textPanel);
		sl_textPanel.putConstraint(SpringLayout.SOUTH, scrollText, 0, SpringLayout.SOUTH, textPanel);
		sl_textPanel.putConstraint(SpringLayout.EAST, scrollText, 727, SpringLayout.WEST, textPanel);
		textPanel.setLayout(sl_textPanel);
		
		
		springLayout.putConstraint(SpringLayout.WEST, scrollText, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollText, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollText, 1019, SpringLayout.WEST, frame.getContentPane());
		//textPanel.add(textArea);
		
		
		scrollText.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		springLayout.putConstraint(SpringLayout.NORTH, scrollText, 6, SpringLayout.SOUTH, panel);
		
		textPanel.add(scrollText);
		sl_textPanel.putConstraint(SpringLayout.NORTH, list, 10, SpringLayout.NORTH, textArea);
		sl_textPanel.putConstraint(SpringLayout.EAST, list, 39, SpringLayout.WEST, textArea);
		
		JPanel listPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, textPanel, 6, SpringLayout.EAST, listPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, textPanel, 0, SpringLayout.SOUTH, listPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, listPanel, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, listPanel, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, listPanel, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.EAST, listPanel, -761, SpringLayout.EAST, frame.getContentPane());
		listPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		sl_textPanel.putConstraint(SpringLayout.NORTH, listPanel, 0, SpringLayout.NORTH, scrollText);
		sl_textPanel.putConstraint(SpringLayout.WEST, listPanel, -29, SpringLayout.EAST, list);
		sl_textPanel.putConstraint(SpringLayout.EAST, listPanel, -6, SpringLayout.WEST, scrollText);
		frame.getContentPane().add(listPanel);
		list.setBorder(null);
		JScrollPane scrollList = new JScrollPane(list);
		scrollList.setBounds(new Rectangle(1, 1, 1, 1));
		scrollList.setBorder(new LineBorder(new Color(0, 0, 0)));
		sl_textPanel.putConstraint(SpringLayout.SOUTH, listPanel, 0, SpringLayout.SOUTH, scrollText);
		SpringLayout sl_listPanel = new SpringLayout();
		sl_listPanel.putConstraint(SpringLayout.NORTH, scrollList, 0, SpringLayout.NORTH, listPanel);
		sl_listPanel.putConstraint(SpringLayout.WEST, scrollList, 0, SpringLayout.WEST, listPanel);
		sl_listPanel.putConstraint(SpringLayout.SOUTH, scrollList, 639, SpringLayout.NORTH, listPanel);
		sl_listPanel.putConstraint(SpringLayout.EAST, scrollList, 244, SpringLayout.WEST, listPanel);
		listPanel.setLayout(sl_listPanel);
		
		sl_textPanel.putConstraint(SpringLayout.WEST, list, 0, SpringLayout.WEST, textPanel);
		listPanel.add(scrollList);
		sl_textPanel.putConstraint(SpringLayout.SOUTH, list, 714, SpringLayout.NORTH, textPanel);
		
		// adding mouse event listener to the synonym list, so whenever an item is selected, it triggers the 
		// following event.
		list.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				
			}
			public void mousePressed(MouseEvent e) {
				
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			/**
			 * whenever an item is selected, the item is replaced with the selected content from the text panel
			 */
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1){
					//on double clicking the mouse
					if(e.getClickCount()==2){
						int index = list.locationToIndex(e.getPoint());
						String item =(String)listModel.getElementAt(index);
						System.out.println("selected synonym is : "+item);
						//if item is not null replace the selection
						if(textArea.getSelectedText()!=null)
							textArea.replaceSelection(item);
					}
				}
				
			}
		});
		panel.add(srchButton);
		
		// addin action listener to the file open button to open a file
		fileOpenButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				openFile();
			}
		});
		 
		//adding action listener to clear the content
		fileNewButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				clearTextArea();
			}
		});
		
		/**
		 * addin action listener to the search button, so when entered it 
		 * sends out the search text to the server and displays the received message in the list.
		 */
		srchButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				if(srchField.getText()!=null && srchField.getText()!=""){
					String srchText = srchField.getText();
					// get suggested words from the server
					String suggestedWords =getWordSuggestion(srchText);
					
					if(suggestedWords!=null && suggestedWords!="NA"){
						String words[] = suggestedWords.split(",");
						//remove all existing item in the list
						listModel.removeAllElements();
						//populate the items in the list.
						if(words!=null && words.length>0){
							for(String word: words){
								word=word.replaceFirst("\\[", " ");
								word=word.replaceFirst("\\]", " ");
								//adding item to the synonym list
								listModel.addElement(word);
							}
						}
					}
				}
			}
		});

		
		//adding mouse listener to the text area, whenever a text is selected event is triggered
		textArea.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
			}
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
			}
			
			public void mouseEntered(MouseEvent e) {
			}
			/**
			 * on right click the selected text will be sent to the search text and sent to the server
			 * the received message will be displayed in the list
			 */
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON3){
					String selectedText=null;
					if(textArea.getSelectedText()!=null){
						selectedText=textArea.getSelectedText();
						System.out.println(this.getClass().getName() + "Selected text from text area is "+selectedText);
						srchField.setText(selectedText);
						String suggestedWords =getWordSuggestion(selectedText);
						// suggested words are the list from the server
						if(suggestedWords!=null && suggestedWords!="NA"){
							String words[] = suggestedWords.split(",");
							//list is emptied and populated with the new words
							listModel.removeAllElements();
							if(words!=null && words.length>0){
								for(String word: words){
									word=word.replaceFirst("\\[", " ");
									word=word.replaceFirst("\\]", " ");
									//adding a word to the list
									listModel.addElement(word);
								}
							}
						}

					}
				}
			}
		});
		
	}
	
	/**
	 * clears the content of the client text area
	 */
	public void clearTextArea(){
		textArea.setText("");
	}
	
	/**
	 * this method takes input word from user and sends it to the user to fetch the 
	 * synonym list for the user word
	 * @param word
	 * @return
	 */
	public String getWordSuggestion(String word){
		String clntText=null;
		String srvText=null;
		if(srchField.getText()!=null && srchField.getText()!=""){
			//get the client search word
			clntText = srchField.getText();
			System.out.println(this.getClass().getName() +" Client sent word "+ clntText);
			//sends the word to the server
			client.sendMessage(clntText);
			//receives synonym from the server
			srvText = client.recieveMessage();
			System.out.println(this.getClass().getName() +" Client recieved word "+ srvText);
		}
		return srvText;
	}
	
	/**
	 * this method opens a file for viewing 
	 */
	public void openFile(){
		int retVal =fileChooser.showOpenDialog(fileOpenButton);
		if(retVal==JFileChooser.APPROVE_OPTION){
			//opens the file chooser
			File file =fileChooser.getSelectedFile();
			StringBuilder stringBuilder = new StringBuilder();
			String line=null;
			try {
				//open the buffer reader and reads the file 
				BufferedReader reader = new BufferedReader(new FileReader(file));
				while((line =reader.readLine())!=null){
					stringBuilder.append(line);
					stringBuilder.append("\n");
				}
				//sets the content of the file to text area
				textArea.setText(stringBuilder.toString());
			} 
			catch (FileNotFoundException e) {
				System.out.println(this.getClass().getName() +" Exception while opening the selected file :"+file.getAbsoluteFile());
				e.printStackTrace();
			}
			catch (IOException e) {
				System.out.println(this.getClass().getName() +" Exception while reading the selected file :"+file.getAbsoluteFile());
				e.printStackTrace();
			}
		}
	}
}
