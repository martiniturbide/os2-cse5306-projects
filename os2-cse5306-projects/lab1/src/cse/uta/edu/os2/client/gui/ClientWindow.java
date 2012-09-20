package cse.uta.edu.os2.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Button;
import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Dimension;
import javax.swing.SpringLayout;

import cse.uta.edu.os2.client.ClientProg;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ClientWindow {

	private JFrame frame;
	private JTextArea textArea = new JTextArea();
	private JButton fileNewButton = new JButton("");
	private JButton fileOpenButton = new JButton("");
	private JTextField srchField = new JTextField();
	private JFileChooser fileChooser = new JFileChooser();
	private ClientProg client = new ClientProg(); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
	 * Create the application.
	 */
	public ClientWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(1024, 800));
		frame.setMaximumSize(new Dimension(1024, 800));
		frame.setBounds(100, 100, 1045, 621);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -738, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, panel, 4, SpringLayout.NORTH, frame.getContentPane());
		frame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		sl_panel.putConstraint(SpringLayout.NORTH, srchField, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, srchField, 0, SpringLayout.SOUTH, fileNewButton);
		sl_panel.putConstraint(SpringLayout.EAST, srchField, -508, SpringLayout.EAST, panel);
		panel.setLayout(sl_panel);

		
		sl_panel.putConstraint(SpringLayout.NORTH, fileNewButton, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, fileNewButton, 21, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, fileNewButton, 0, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, fileNewButton, 55, SpringLayout.WEST, panel);
		panel.add(fileNewButton);
		fileNewButton.setIcon(new ImageIcon(ClientWindow.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		
		
		sl_panel.putConstraint(SpringLayout.NORTH, fileOpenButton, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, fileOpenButton, 17, SpringLayout.EAST, fileNewButton);
		sl_panel.putConstraint(SpringLayout.SOUTH, fileOpenButton, 0, SpringLayout.SOUTH, panel);
		panel.add(fileOpenButton);
		fileOpenButton.setIcon(new ImageIcon(ClientWindow.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		
		JLabel lblSearch = new JLabel("Keyword");
		sl_panel.putConstraint(SpringLayout.WEST, srchField, 6, SpringLayout.EAST, lblSearch);
		sl_panel.putConstraint(SpringLayout.WEST, lblSearch, 42, SpringLayout.EAST, fileOpenButton);
		sl_panel.putConstraint(SpringLayout.EAST, lblSearch, -791, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, lblSearch, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblSearch, 0, SpringLayout.SOUTH, fileNewButton);
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblSearch);
		panel.add(srchField);
		srchField.setMinimumSize(new Dimension(6, 10));
		srchField.setColumns(10);
		
		JPanel textPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, textPanel, 4, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, textPanel, 10, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, textPanel, 728, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, textPanel, 0, SpringLayout.EAST, panel);
		frame.getContentPane().add(textPanel);
		SpringLayout sl_textPanel = new SpringLayout();
		sl_textPanel.putConstraint(SpringLayout.NORTH, textArea, 0, SpringLayout.NORTH, textPanel);
		sl_textPanel.putConstraint(SpringLayout.WEST, textArea, 0, SpringLayout.WEST, textPanel);
		sl_textPanel.putConstraint(SpringLayout.SOUTH, textArea, 714, SpringLayout.NORTH, textPanel);
		sl_textPanel.putConstraint(SpringLayout.EAST, textArea, 1009, SpringLayout.WEST, textPanel);
		textPanel.setLayout(sl_textPanel);
		
		
		springLayout.putConstraint(SpringLayout.WEST, textArea, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textArea, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textArea, 1019, SpringLayout.WEST, frame.getContentPane());
		textPanel.add(textArea);
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		springLayout.putConstraint(SpringLayout.NORTH, textArea, 6, SpringLayout.SOUTH, panel);
		
		JButton srchButton = new JButton("Search");
		sl_panel.putConstraint(SpringLayout.NORTH, srchButton, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, srchButton, 34, SpringLayout.EAST, srchField);
		sl_panel.putConstraint(SpringLayout.SOUTH, srchButton, 20, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, srchButton, -409, SpringLayout.EAST, panel);
		panel.add(srchButton);
		

		fileOpenButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				openFile();
			}
		});
		 
		fileNewButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				clearTextArea();
			}
		});
		
		srchButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				getWordSuggestion();
			}
		});

		textArea.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton()==MouseEvent.BUTTON3){
					String selectedText=null;
					if(textArea.getSelectedText()!=null){
						selectedText=textArea.getSelectedText();
						System.out.println(this.getClass().getName() + "Selected text from text area is "+selectedText);
						srchField.setText(selectedText);
					}
				}
			}
		});
	}

	public void clearTextArea(){
		textArea.setText("");
	}
	

	public void getWordSuggestion(){
		String clntText=null;
		String srvText=null;
		if(srchField.getText()!=null && srchField.getText()!=""){
			clntText = srchField.getText();
			System.out.println(this.getClass().getName() +" Client sent word "+ clntText);
			client.sendMessage(clntText);
			srvText = client.recieveMessage();
			System.out.println(this.getClass().getName() +" Client recieved word "+ srvText);
			if(srvText!=null){
				textArea.setText(srvText);
			}
		}
	}
	
	public void openFile(){
		int retVal =fileChooser.showOpenDialog(fileOpenButton);
		if(retVal==JFileChooser.APPROVE_OPTION){
			File file =fileChooser.getSelectedFile();
			StringBuilder stringBuilder = new StringBuilder();
			String line=null;
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				while((line =reader.readLine())!=null){
					stringBuilder.append(line);
					stringBuilder.append("\n");
				}
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
