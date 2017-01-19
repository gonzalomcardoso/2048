package vistas;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class FormPrincipal 
{
	public static void main(String[] args) {
	    JFrame juego = new JFrame();
	    juego.setTitle("Juego 2048 Vargas Lucero Cardozo");
	    juego.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    juego.setSize(340, 400);
	    juego.setResizable(false);

	    juego.add(new FormTablero());

	    juego.setLocationRelativeTo(null);
	    juego.setVisible(true);
	  }
}
