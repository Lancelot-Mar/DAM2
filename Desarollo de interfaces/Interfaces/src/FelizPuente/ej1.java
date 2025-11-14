package FelizPuente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ej1 {

	private JFrame frame;
	
	//Importamos el temporizador
	Timer tiempo;
	
	int i = 10;
	
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ej1 window = new ej1();
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
	public ej1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblNewLabel_1.setVisible(true);
				lblNewLabel.setVisible(false);				
				tiempo.start();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(136, 168, 108, 99);
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel = new JLabel("Feliz Puente");
		lblNewLabel.setVisible(false);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBackground(new Color(0, 255, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(322, 63, 230, 56);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("10");
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(393, 197, 89, 39);
		frame.getContentPane().add(lblNewLabel_1);
		
		btnNewButton_1 = new JButton("Salir");
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setVisible(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				System.exit(0);
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_1.setBounds(513, 363, 124, 56);
		frame.getContentPane().add(btnNewButton_1);
		
		tiempo = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				i--;
				lblNewLabel_1.setText(String.valueOf(i));
				if(i == 0) {
					tiempo.stop();
					lblNewLabel.setVisible(true);				
					lblNewLabel_1.setVisible(false);
					btnNewButton.setEnabled(false);
					
					btnNewButton_1.setVisible(true);
					btnNewButton_1.setEnabled(true);

				}
				
			}		
		});
	}
}

