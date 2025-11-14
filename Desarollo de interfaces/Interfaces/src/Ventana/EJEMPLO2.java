package Ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class EJEMPLO2 {

	private JFrame frame;
	private JTextField textField;
	private int cont = 0;
	private JPanel panel_1;
	private JPanel panel_2;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EJEMPLO2 window = new EJEMPLO2();
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
	public EJEMPLO2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(1680/2-225, 1050/2-150, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		frame.getContentPane().add(panel, "name_12477514324200");
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("CLICK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cont++;
				textField.setText(Integer.toString(cont));
				
			}
		});
		btnNewButton.setBounds(98, 111, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Numero de clicks");
		lblNewLabel.setBounds(242, 51, 89, 85);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(242, 135, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Next");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel.setVisible(false);
				panel_1.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(349, 227, 75, 23);
		panel.add(btnNewButton_1);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 255, 64));
		frame.getContentPane().add(panel_1, "name_12477537203100");
		panel_1.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_1.setVisible(false);
				panel.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(10, 227, 79, 23);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Next");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_1.setVisible(false);
				panel_2.setVisible(true);
			}
		});
		btnNewButton_2_1.setBounds(345, 227, 79, 23);
		panel_1.add(btnNewButton_2_1);
		
		panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, "name_12733044493200");
		panel_2.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_2.setVisible(false);
				panel_1.setVisible(true);
				
			}
		});
		btnNewButton_3.setBounds(10, 227, 77, 23);
		panel_2.add(btnNewButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("Fin");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 60));
		lblNewLabel_1.setBounds(180, 69, 96, 100);
		panel_2.add(lblNewLabel_1);
	}
}
