package controladores;

import static org.junit.Assert.*;

import org.junit.Test;

public class TableroTest {
	
	@Test
	public void sumaTest() {
		Tablero tablero = new Tablero(4, 4);
		int n= 128;
		int n2= n*2;
		
		tablero.getGrilla().asignarNumero(0, 0, n);
		tablero.getGrilla().asignarNumero(0, 1, n);
		
		assertTrue(tablero.getGrilla().getCasillero(0, 0).valor() == n);
		assertTrue(tablero.getGrilla().getCasillero(0, 1).valor() == n);

		tablero.moverIzquierda();

		assertTrue(tablero.getGrilla().getCasillero(0, 0).valor() == n2);
	}
	
	@Test
	public void casillerosiguales(){
		Tablero tablero = new Tablero(4, 4);
		int n= 128;
		
		tablero.getGrilla().asignarNumero(0, 0, n);
		tablero.getGrilla().asignarNumero(0, 1, n);
		
		assertTrue(tablero.getGrilla().casillerosIguales(0, 0, 0, 1));
	}

}
