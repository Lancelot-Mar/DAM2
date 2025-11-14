package Ventana;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class EJEMPLO4 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EJEMPLO4 window = new EJEMPLO4();
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
	public EJEMPLO4() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Dimension monitor = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) monitor.getHeight();
		int width = (int) monitor.getWidth();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 806, 735);
		//frame.setLocationRelativeTo(null);
		frame.setLocation((width/2-frame.getWidth()/2),(height/2-frame.getHeight()/2));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
