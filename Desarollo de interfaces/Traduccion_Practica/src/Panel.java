import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Panel extends JPanel{

	private JLabel etiqueta;
	private JButton boton;

	
	private JTextField textField;
	public Panel() {
		setLayout(null);
		etiqueta = new JLabel("");
		etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
		etiqueta.setBounds(195, 170, 184, 29);
		add(etiqueta);
		
		boton = new JButton("Traducir");
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String REGEX_TRADUCIR = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
				
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
					
					etiqueta.setText(resultado);											
				}else {
					String resultado = "error";
					etiqueta.setText(resultado);	
					
					String msg = "Error: Palabra Incorecta";
					JOptionPane.showConfirmDialog(null, msg,"ERROR",-1);

				}
				
			}
		});
		boton.setBounds(62, 139, 89, 23);
		add(boton);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(195, 93, 184, 29);
		add(textField);
		textField.setColumns(10);
		
	}
}
