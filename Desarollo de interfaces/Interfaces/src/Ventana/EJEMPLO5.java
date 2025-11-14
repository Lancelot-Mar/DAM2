package Ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EJEMPLO5 {

	private JFrame frame;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EJEMPLO5 window = new EJEMPLO5();
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
	public EJEMPLO5() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 679, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 663, 460);
		frame.getContentPane().add(layeredPane);
		
		panel = new JPanel();
		layeredPane.setLayer(panel, 2);
		panel.setBounds(0, 0, 663, 459);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Panel Rojo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_2.setVisible(false);

				panel.setVisible(false);
				panel_1.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(534, 425, 119, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Panel Verde");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				panel_1.setVisible(false);

				panel.setVisible(false);
				panel_2.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(405, 425, 119, 23);
		panel.add(btnNewButton_1);
		
		panel_1 = new JPanel();
		layeredPane.setLayer(panel_1, 1);
		panel_1.setBackground(new Color(255, 0, 0));
		panel_1.setBounds(0, 0, 663, 459);
		layeredPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton_1_1_1 = new JButton("Panel Blanco");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_2.setVisible(false);
				
				panel_1.setVisible(false);
				panel.setVisible(true);
				
			}
		});
		btnNewButton_1_1_1.setBounds(535, 425, 118, 23);
		panel_1.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_2_1 = new JButton("Panel Verde");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel.setVisible(false);

				panel_1.setVisible(false);
				panel_2.setVisible(true);
				
			}
		});
		btnNewButton_2_1.setBounds(404, 425, 118, 23);
		panel_1.add(btnNewButton_2_1);
		
		panel_2 = new JPanel();
		layeredPane.setLayer(panel_2, 0);
		panel_2.setBackground(new Color(128, 255, 128));
		panel_2.setBounds(0, 0, 662, 461);
		layeredPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton_1_1 = new JButton("Panel Blanco");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_1.setVisible(false);

				panel_2.setVisible(false);
				panel.setVisible(true);
				
			}
		});
		btnNewButton_1_1.setBounds(406, 427, 118, 23);
		panel_2.add(btnNewButton_1_1);
		
		JButton btnNewButton_2 = new JButton("Panel Rojo");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel.setVisible(false);
				
				panel_2.setVisible(false);
				panel_1.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(534, 427, 118, 23);
		panel_2.add(btnNewButton_2);
	}
}
