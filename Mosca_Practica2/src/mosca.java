import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class mosca {

	private JFrame frame;
	static ImageIcon imagen;
	static Image img;
	Random random = new Random();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mosca window = new mosca();
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
	public mosca() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 677, 666);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel etiquetaMosca = new JLabel("");
		etiquetaMosca.setBounds(273, 251, 55, 63);
		
		imagen = new ImageIcon(mosca.class.getResource("/9421891.png"));
		img = imagen.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
		
		etiquetaMosca.setIcon(new ImageIcon(img));
		frame.getContentPane().add(etiquetaMosca);
				
		
		frame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				int xM = e.getX();
				int yM = e.getY();
				
				int labely = etiquetaMosca.getY();
				int labelx = etiquetaMosca.getX();
			
				int distanX = Math.abs(xM-labelx);
				int distanY = Math.abs(yM-labely);
				int limite = 75;
				
				if(distanX < limite || distanY < limite) {
					int y = random.nextInt(101) - 50;
					int x = random.nextInt(101) - 50;
					
					int nuevoX = Math.max(0, Math.min(labelx + x, frame.getWidth()-2*50));
					int nuevoY = Math.max(0, Math.min(labely + y, frame.getHeight()-2*50));

					etiquetaMosca.setLocation(nuevoX,nuevoY);
				}
			}
		});
	}
}
