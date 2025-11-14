package FelizPuente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import javax.swing.JProgressBar;

public class ej2 {

	private JFrame frame;
	
	//Importamos el temporizador
	Timer tiempo;
	
	int i = 10;
	int j = 0;

	
	private JLabel lblNewLabel;
	private JButton btnNewButton_1;
	private JProgressBar progressBar;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ej2 window = new ej2();
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
	public ej2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 715, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				progressBar.setVisible(true);
				tiempo.start();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(128, 144, 107, 73);
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(0, 255, 0));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(460, 150, 75, 62);
		frame.getContentPane().add(lblNewLabel);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(120, 263, 450, 28);
		frame.getContentPane().add(progressBar);
		
		btnNewButton_1 = new JButton("Salir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				System.exit(0);
				
			}
		});
		btnNewButton_1.setVisible(false);
		btnNewButton_1.setBounds(594, 403, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		lblNewLabel_1 = new JLabel("Cargando");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(70, 302, 98, 45);
		frame.getContentPane().add(lblNewLabel_1);
		
		tiempo = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				i--;
				j++;
				lblNewLabel.setText(String.valueOf(i));
				lblNewLabel_1.setLocation((j*45)+70, 302);
				if(i == 0 || j == 100) {
					tiempo.stop();
					j = 0;
					i = 10;
					lblNewLabel_1.setVisible(true);
					btnNewButton.setEnabled(false);
					btnNewButton_1.setVisible(true);
					btnNewButton_1.setEnabled(true);
					lblNewLabel_1.setText("Conseguido");

				}
				progressBar.setValue(j*10);
				
			}		
		});
		
		progressBar.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				
				if(progressBar.getValue()==100) {
					String msg = "ENHORABUENA";
					JOptionPane.showConfirmDialog(null, msg,"AL RECREO",1);
				}
				
			}
			
		});
		
		
	}
}
