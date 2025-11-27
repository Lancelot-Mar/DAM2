import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class PracticaMail {

	private JFrame frame;
	private JTextField textoRemitente;
	private JTextField textoAsunto;
	private JTextField textoCuerpo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PracticaMail window = new PracticaMail();
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
	public PracticaMail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 602, 454);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textoRemitente = new JTextField();
		textoRemitente.setBounds(48, 87, 440, 20);
		frame.getContentPane().add(textoRemitente);
		textoRemitente.setColumns(10);
		
		JLabel Remitente = new JLabel("Remitente:");
		Remitente.setBounds(48, 62, 87, 14);
		frame.getContentPane().add(Remitente);
		
		textoAsunto = new JTextField();
		textoAsunto.setColumns(10);
		textoAsunto.setBounds(48, 147, 440, 20);
		frame.getContentPane().add(textoAsunto);
		
		JLabel Asunto = new JLabel("Asunto:");
		Asunto.setBounds(48, 122, 121, 14);
		frame.getContentPane().add(Asunto);
		
		JLabel Cuerpo = new JLabel("Cuerpo:");
		Cuerpo.setBounds(48, 191, 46, 14);
		frame.getContentPane().add(Cuerpo);
		
		textoCuerpo = new JTextField();
		textoCuerpo.setColumns(10);
		textoCuerpo.setBounds(48, 213, 440, 135);
		frame.getContentPane().add(textoCuerpo);
		
		JLabel Titulo = new JLabel("EMAIL");
		Titulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setBounds(10, 11, 104, 38);
		frame.getContentPane().add(Titulo);
		
		JButton Enviar = new JButton("Enviar");
		Enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleEmail.envio(textoRemitente.getText(),textoAsunto.getText(),textoCuerpo.getText());
			}		
		});
		Enviar.setBounds(513, 381, 63, 23);
		frame.getContentPane().add(Enviar);
	}
}
