package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controladores.Tablero;

public class FormTablero extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color FONDO_COLOR = new Color(0xbbada0);
	private static final String FUENTE_NOMBRE = "Arial";
	private static final int CASILLERO_TAMANIO = 64;
	private static final int CASILLERO_MARGEN = 16;
	private Tablero tablero;
	private FormGrilla grillaVisual;
	public FormTablero()
	{
		setFocusable(true);
		addKeyListener(new KeyAdapter() 
		{
			@Override
		    public void keyPressed(KeyEvent e) 
		    {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
		          reiniciarJuego();
		        }
		        if (!tablero.hayMovimientosDisponibles()) 
		        {
		        	mostrarMensajeFinal();
		        }
		        else
		        {
		        	switch (e.getKeyCode()) 
		        	{
		            	case KeyEvent.VK_LEFT:
		            		izquierda();
		            		break;
		            	case KeyEvent.VK_RIGHT:
		            		derecha();
		            		break;
		            	case KeyEvent.VK_DOWN:
		            		abajo();
		            		break;
		            	case KeyEvent.VK_UP:
		            		arriba();
		            		break;
		            	case KeyEvent.VK_P:
						try {
							verPuntajesMaximos();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
		            		break;
		        	}
		        }
		        repaint();
		    }
		});
		reiniciarJuego();
	}
	
	private void izquierda()
	{
		if(tablero.moverIzquierda())
		{
			tablero.agregarNumero();
			actualizarTablero();
		}
	}
	
	private void derecha()
	{
		if(tablero.moverDerecha())
		{
			tablero.agregarNumero();
			actualizarTablero();
		}
	}
	
	private void arriba()
	{
		if(tablero.moverArriba())
		{
			tablero.agregarNumero();
			actualizarTablero();
		}
	}
	
	private void abajo()
	{
		if(tablero.moverAbajo())
		{
			tablero.agregarNumero();
			actualizarTablero();
		}
	}
	
	private void reiniciarJuego() 
	{
		tablero = new Tablero(4, 4);
		tablero.agregarNumero();
		tablero.agregarNumero();
		grillaVisual = new FormGrilla(tablero.getGrilla());
		actualizarTablero();
		repaint();
	}
	  
	private void mostrarMensajeFinal() 
	{
		if(tablero.nuevaPuntuacionMaxima())
		{
			String nombre= escribirNombre();
			tablero.grabarPuntaje(nombre);
			JOptionPane.showMessageDialog(null,"Ya guarde tus puntos, espero que no te derroquen");
			
		}
		else
		{
			if(tablero.victoria())
				JOptionPane.showMessageDialog(null,"Ganaste Papa!");
			else JOptionPane.showMessageDialog(null,"Game Over, mejor para la proxima lince");
		}
		reiniciarJuego();
	}
	
	private String escribirNombre()
	{
		String nombre= JOptionPane.showInputDialog("Sos el mas capo! +10. Como te llamas?");
		if(nombre == null)
		{
			nombre = escribirNombre();
		}
		return nombre;
	}
	
	private void actualizarTablero()
	{
		grillaVisual.actualizar(tablero.getGrilla());
	}
	
	private void verPuntajesMaximos() throws IOException
	{
		new FormPuntajesMaximos();
	}
	
	@Override
	  public void paint(Graphics g) {
	    super.paint(g);
	    g.setColor(FONDO_COLOR);
	    g.fillRect(0, 0, this.getSize().width, this.getSize().height);
	    for (int fila = 0; fila < 4; fila++) {
	      for (int columna = 0; columna < 4; columna++) {
	        dibujarCasillero(g, grillaVisual.getCasillero(fila, columna), columna, fila);
	      }
	    }
	  }

	  private void dibujarCasillero(Graphics g2, FormCasillero casillero, int x, int y) {
	    Graphics2D g = ((Graphics2D) g2);
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
	    int valor = casillero.valor();
	    int desplazamientoColumna = desplazamientoCoordenadas(x);
	    int desplazamientoFila = desplazamientoCoordenadas(y);
	    g.setColor(casillero.getBackground());
	    g.fillRoundRect(desplazamientoColumna, desplazamientoFila, CASILLERO_TAMANIO, CASILLERO_TAMANIO, 14, 14);
	    g.setColor(casillero.getForeground());
	    final int tamanio = valor < 100 ? 36 : valor < 1000 ? 32 : 24;
	    final Font fuente = new Font(FUENTE_NOMBRE, Font.BOLD, tamanio);
	    g.setFont(fuente);

	    String s = String.valueOf(valor);
	    final FontMetrics fm = getFontMetrics(fuente);

	    final int w = fm.stringWidth(s);
	    final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

	    if (valor != 0)
	      g.drawString(s, desplazamientoColumna + (CASILLERO_TAMANIO - w) / 2, desplazamientoFila + CASILLERO_TAMANIO - (CASILLERO_TAMANIO - h) / 2 - 2);

	    g.setFont(new Font(FUENTE_NOMBRE, Font.PLAIN, 18));
	    g.drawString("Puntaje: " + tablero.puntajeActual(), 200, 365);
	    g.drawString("Ver Puntajes (P)", 10, 365);

	  }

	  private static int desplazamientoCoordenadas(int arg) {
	    return arg * (CASILLERO_MARGEN + CASILLERO_TAMANIO) + CASILLERO_MARGEN;
	  }
}
