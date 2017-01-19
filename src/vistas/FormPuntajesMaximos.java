package vistas;

import java.awt.Font;
import javax.swing.JDialog;

import modelos.Puntaje;
import modelos.PuntajesMaximos;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FormPuntajesMaximos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FormPuntajesMaximos() throws java.io.IOException
	{
		this.setTitle("Puntajes Maximos");
		this.setSize(340, 400);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    getContentPane().setLayout(null);
	    
	    JLabel lblPuntajesMaximos = new JLabel("Puntajes Maximos");
	    lblPuntajesMaximos.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	    lblPuntajesMaximos.setHorizontalAlignment(SwingConstants.CENTER);
	    lblPuntajesMaximos.setBounds(54, 11, 212, 48);
	    getContentPane().add(lblPuntajesMaximos);
	    this.setVisible(true);
	    
	    PuntajesMaximos puntajes = new PuntajesMaximos();
	    
	    int y = 40;
	    for(Puntaje p : puntajes.obtenerTodos())
	    {
	    	JLabel nuevoLbl = new JLabel();
	    	nuevoLbl.setText("Jugador :" + p.getNombre() + "  Puntos: " + p.getPuntos());
		    nuevoLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		    nuevoLbl.setBounds(33, y+=20, 340, 22);
		    getContentPane().add(nuevoLbl);
	    }
	    
	}

}