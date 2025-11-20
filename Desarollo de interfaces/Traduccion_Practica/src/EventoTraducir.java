import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EventoTraducir implements ActionListener {

	JTextField entrada;
	JLabel etiqueta;
	
	public EventoTraducir(JTextField entrada, JLabel etiqueta) {

		this.entrada = entrada;
		this.etiqueta = etiqueta;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(entrada.getText().isBlank()!=true) {
			
			etiqueta.setText(Operaciones.traducir(entrada.getText()));
			
		}else {
			String msg = "Error: Palabra Incorecta";
			JOptionPane.showConfirmDialog(null, msg,"ERROR",-1);
		}
		
	}

}
