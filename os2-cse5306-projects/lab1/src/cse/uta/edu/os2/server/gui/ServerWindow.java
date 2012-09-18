package cse.uta.edu.os2.server.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

public class ServerWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerWindow window = new ServerWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
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
		
		JButton btnStop = new JButton("Stop");
		sl_menuPanel.putConstraint(SpringLayout.WEST, btnStop, 87, SpringLayout.WEST, menuPanel);
		sl_menuPanel.putConstraint(SpringLayout.SOUTH, btnStop, -6, SpringLayout.SOUTH, menuPanel);
		menuPanel.add(btnStop);
		
		JButton btnClear = new JButton("Clear");
		sl_menuPanel.putConstraint(SpringLayout.NORTH, btnClear, 0, SpringLayout.NORTH, btnStop);
		sl_menuPanel.putConstraint(SpringLayout.EAST, btnClear, -6, SpringLayout.WEST, btnStop);
		menuPanel.add(btnClear);
		
		JPanel textPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, textPanel, 49, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textPanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textPanel, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textPanel, -10, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, menuPanel, 6, SpringLayout.NORTH, textPanel);
		textPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(textPanel);
		SpringLayout sl_textPanel = new SpringLayout();
		textPanel.setLayout(sl_textPanel);
		
		JTextArea textArea = new JTextArea();
		sl_textPanel.putConstraint(SpringLayout.NORTH, textArea, 12, SpringLayout.NORTH, textPanel);
		sl_textPanel.putConstraint(SpringLayout.WEST, textArea, 18, SpringLayout.WEST, textPanel);
		sl_textPanel.putConstraint(SpringLayout.SOUTH, textArea, -8, SpringLayout.SOUTH, textPanel);
		sl_textPanel.putConstraint(SpringLayout.EAST, textArea, -137, SpringLayout.EAST, textPanel);
		textPanel.add(textArea);
	}
}