import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

public class Panel extends JPanel{

	private JLabel etiqueta;
	private JButton boton;
	private JTextField entrada;
	public Panel() {
		
		setLayout(null);
		etiqueta = new JLabel("");
		etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
		etiqueta.setBounds(195, 170, 184, 29);
		add(etiqueta);
		
		boton = new JButton("Traducir");
		boton.setBounds(62, 139, 89, 23);
		add(boton);
		
		entrada = new JTextField();
		entrada.setFont(new Font("Tahoma", Font.PLAIN, 11));
		entrada.setHorizontalAlignment(SwingConstants.CENTER);
		entrada.setBounds(195, 93, 184, 29);
		add(entrada);
		entrada.setColumns(10);
		
		boton.addActionListener(new EventoTraducir(entrada, etiqueta));

		
	}
}
