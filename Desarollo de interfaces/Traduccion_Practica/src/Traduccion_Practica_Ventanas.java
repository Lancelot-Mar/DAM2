import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Traduccion_Practica_Ventanas {
	
	static String REGEX_TRADUCIR = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Traduccion_Practica_Ventanas window = new Traduccion_Practica_Ventanas();
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
	public Traduccion_Practica_Ventanas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 699, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(218, 252, 251, 58);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(181, 112, 316, 53);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Traducir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String palabraTraducir = textField.getText().trim();
				
				if(palabraTraducir.matches(REGEX_TRADUCIR)) {
					String web = "https://www.spanishdict.com/translate/" + palabraTraducir;
					
					Document document = null;
					Element palabra = null;
					String resultado = null;
					
					try {
						document = Jsoup.connect(web).get();
						palabra = document.select("div#quickdef1-es a.tCur1iYh").get(0);
						resultado= palabra.html().toUpperCase();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					lblNewLabel.setText(resultado);											
				}else {
					String resultado = "error";
					lblNewLabel.setText(resultado);	
					
					String msg = "Error: Palabra Incorecta";
					JOptionPane.showConfirmDialog(null, msg,"ERROR",-1);

				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(271, 192, 146, 39);
		frame.getContentPane().add(btnNewButton);
	}
}
