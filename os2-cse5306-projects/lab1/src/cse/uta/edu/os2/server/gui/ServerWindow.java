package cse.uta.edu.os2.server.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

import cse.uta.edu.os2.server.FileDictionary;
import cse.uta.edu.os2.server.ServerProg;

/**
 * this class responsible for displaying server GUI events the listener
 * @author lakshmana s(1000789751)
 *
 */
public class ServerWindow {

	private JFrame frame;
	private ServerProg server = new ServerProg(this);
	private FileDictionary fileDict = new FileDictionary();
	private JTextArea textArea = new JTextArea();
	private JButton btnStop = new JButton("Stop");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				try {
					//creates a server GUI object and calls the display
					ServerWindow window = new ServerWindow();
					window.frame.setVisible(true);
					//calls the server socket program to lister for clients
					window.listenServer();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}

	/**
	 * Create the application and initializes components.
	 */
	public ServerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame and creates the visual 
	 * components and aligns it and displays.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 557, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel menuPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, menuPanel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, menuPanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, menuPanel, 543, SpringLayout.WEST, frame.getContentPane());
		menuPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		frame.getContentPane().add(menuPanel);
		SpringLayout sl_menuPanel = new SpringLayout();
		menuPanel.setLayout(sl_menuPanel);
		
		
		sl_menuPanel.putConstraint(SpringLayout.WEST, btnStop, 87, SpringLayout.WEST, menuPanel);
		sl_menuPanel.putConstraint(SpringLayout.SOUTH, btnStop, -6, SpringLayout.SOUTH, menuPanel);
		menuPanel.add(btnStop);
		
		//this button when presses clears the server log.
		JButton btnClear = new JButton("Clear");
		
		sl_menuPanel.putConstraint(SpringLayout.NORTH, btnClear, 0, SpringLayout.NORTH, btnStop);
		sl_menuPanel.putConstraint(SpringLayout.EAST, btnClear, -6, SpringLayout.WEST, btnStop);
		menuPanel.add(btnClear);
		
		JPanel textPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.NORTH, textPanel, 49, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textPanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textPanel, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textPanel, -10, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, menuPanel, 6, SpringLayout.NORTH, textPanel);
		textPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(textPanel);
		SpringLayout sl_textPanel = new SpringLayout();
		sl_textPanel.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, textPanel);
		sl_textPanel.putConstraint(SpringLayout.EAST, textArea, -12, SpringLayout.EAST, textPanel);
		textPanel.setLayout(sl_textPanel);
		
		sl_textPanel.putConstraint(SpringLayout.NORTH, scrollPane, 12, SpringLayout.NORTH, textPanel);
		sl_textPanel.putConstraint(SpringLayout.WEST, scrollPane, 18, SpringLayout.WEST, textPanel);
		sl_textPanel.putConstraint(SpringLayout.SOUTH, scrollPane, -8, SpringLayout.SOUTH, textPanel);
		textPanel.add(scrollPane);
		
		//adds the action listener clear button, so when presses it clears
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
			}
		});
		
		//adds the action listener to stop button, when presses it stops the server and exits the application
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				server.closeSocket();
				System.exit(0);
			}
		});

	}

	/** 
	 * this method makes a call to server socket program to listen for clients
	 */
	public void  listenServer(){
		server.listenToClients();
	}

	public void refresh(){
		frame.validate();
	}
	
	/**
	 * this method sets the client requested word to server GUI window
	 * @param msg
	 */
	public void setTextArea(String msg){
		textArea.append(msg);
	}
}
