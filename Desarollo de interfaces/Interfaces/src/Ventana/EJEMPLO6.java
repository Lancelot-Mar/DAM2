package Ventana;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EJEMPLO6 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EJEMPLO6 window = new EJEMPLO6();
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
	public EJEMPLO6() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 814, 589);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(EJEMPLO6.class.getResource("/SaltwaterCrocodile('Maximo').jpg")));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.getContentPane().add(BuscarImagen());
	}
	
	
	private Component BuscarImagen() {
		BufferedImage fondo = null;
		
		try{
			fondo = ImageIO.read(new File ("src/rfb6f608.jpg"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		final Image foto = fondo;
		
		JPanel PanelConFondo = new JPanel() {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void paintComponent(Graphics G) {
				super.paintComponent(G);
				G.drawImage(foto,0,0,814,589,null);
			}
		};
		return PanelConFondo;
	}

}
