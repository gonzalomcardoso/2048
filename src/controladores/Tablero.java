package controladores;

import modelos.Puntaje;
import modelos.PuntajesMaximos;

public class Tablero {
	private Grilla grilla;
	private Puntaje puntaje;
	private boolean bandera;
	private PuntajesMaximos puntajesMaximos;
	public Tablero(int filas, int columnas) 
	{
		grilla = new Grilla(filas, columnas);
		puntaje = new Puntaje();
		puntajesMaximos = new PuntajesMaximos();
	}
	
	public Grilla getGrilla()
	{
		return grilla;
	}
	
	private Tablero(Tablero aux) {
		this.grilla = new Grilla(aux.grilla);
		puntaje = new Puntaje();
	}

	public boolean moverIzquierda() {
		bandera = false;
		for (int fila = 0; fila < grilla.cantFilas(); fila++) {
			moverCasillerosIzq		(fila, 0, 1);
			combinarCasillerosIzq	(fila, 0, 1);
			moverCasillerosIzq		(fila, 0, 1);
		}
		return bandera;
	}

	private void moverCasillerosIzq(int fila, int colActual, int colSiguiente) {
		if (colSiguiente >= grilla.cantColumnas()) {
			return;
		} else {
			if (!grilla.casilleroOcupado(fila, colActual)) {
				if (grilla.casilleroOcupado(fila, colSiguiente)) {
					int valor = grilla.getCasillero(fila, colSiguiente).valor();
					grilla.asignarNumero(fila, colActual, valor);
					grilla.asignarNumero(fila, colSiguiente, 0);
					bandera = true;
					moverCasillerosIzq(fila, colActual + 1, colSiguiente + 1);
				} else {
					moverCasillerosIzq(fila, colActual, colSiguiente + 1);
				}
			} else {
				moverCasillerosIzq(fila, colActual + 1, colSiguiente + 1);
			}
		}
	}

	void combinarCasillerosIzq(int fila, int colActual, int colSiguiente) {
		if (colSiguiente >= grilla.cantColumnas()) {
			return;
		} else {
			if (grilla.casilleroOcupado(fila, colActual) && grilla.casilleroOcupado(fila, colSiguiente)) {
				if (grilla.casillerosIguales(fila, colActual, fila, colSiguiente)) {
					int valor = grilla.getCasillero(fila, colActual).valor() * 2;
					puntaje.acumularPuntos(valor);
					grilla.asignarNumero(fila, colActual, valor);
					grilla.removerNumero(fila, colSiguiente);
					bandera = true;
				}
			}
			combinarCasillerosIzq(fila, colActual + 1, colSiguiente + 1);
		}

	}

	public boolean moverDerecha() {
		bandera = false;
		for (int fila = 0; fila < grilla.cantFilas(); fila++) {
			moverCasillerosDer(fila, grilla.cantColumnas() - 1, grilla.cantColumnas() - 2);
			combinarCasillerosDer(fila, grilla.cantColumnas() - 1, grilla.cantColumnas() - 2);
			moverCasillerosDer(fila, grilla.cantColumnas() - 1, grilla.cantColumnas() - 2);
		}
		return bandera;
	}

	private void moverCasillerosDer(int fila, int colActual, int colSiguiente) {
		if (colSiguiente < 0) {
			return;
		} else {
			if (!grilla.casilleroOcupado(fila, colActual)) {
				if (grilla.casilleroOcupado(fila, colSiguiente)) {
					grilla.asignarNumero(fila, colActual, grilla.getCasillero(fila, colSiguiente).valor());
					grilla.asignarNumero(fila, colSiguiente, 0);
					bandera = true;
					moverCasillerosDer(fila, colActual - 1, colSiguiente - 1);
				} else {
					moverCasillerosDer(fila, colActual, colSiguiente - 1);
				}
			} else {
				moverCasillerosDer(fila, colActual - 1, colSiguiente - 1);
			}
		}
	}

	private void combinarCasillerosDer(int fila, int colActual, int colSiguiente) {
		if (colSiguiente < 0) {
			return;
		} else {
			if (grilla.casilleroOcupado(fila, colActual)) {
				if (grilla.casilleroOcupado(fila, colSiguiente)) {
					if (grilla.casillerosIguales(fila, colActual, fila, colSiguiente)) {
						int valor = grilla.getCasillero(fila, colActual).valor() * 2;
						puntaje.acumularPuntos(valor);
						grilla.asignarNumero(fila, colActual, valor);
						grilla.removerNumero(fila, colSiguiente);
						bandera = true;
					}
				}
			}
			combinarCasillerosDer(fila, colActual - 1, colSiguiente - 1);
		}
	}

	public boolean moverArriba() {
		bandera = false;
		for (int columna = 0; columna < grilla.cantColumnas(); columna++) {
			moverCasillerosArr(columna, 0, 1);
			combinarCasillerosArr(columna, 0, 1);
			moverCasillerosArr(columna, 0, 1);
		}
		return bandera;
	}

	private void moverCasillerosArr(int columna, int filaActual, int filaSiguiente) {
		if (filaSiguiente >= grilla.cantFilas()) {
			return;
		} else {
			if (!grilla.casilleroOcupado(filaActual, columna)) {
				if (grilla.casilleroOcupado(filaSiguiente, columna)) {
					grilla.asignarNumero(filaActual, columna, grilla.getCasillero(filaSiguiente, columna).valor());
					grilla.asignarNumero(filaSiguiente, columna, 0);
					bandera = true;
					moverCasillerosArr(columna, filaActual + 1, filaSiguiente + 1);
				} else {
					moverCasillerosArr(columna, filaActual, filaSiguiente + 1);
				}
			} else {
				moverCasillerosArr(columna, filaActual + 1, filaSiguiente + 1);
			}
		}
	}

	private void combinarCasillerosArr(int columna, int filaActual, int filaSiguiente) {
		if (filaSiguiente >= grilla.cantFilas())
			return;
		if (grilla.casilleroOcupado(filaActual, columna)) {
			if (grilla.casilleroOcupado(filaSiguiente, columna)) {
				if (grilla.casillerosIguales(filaActual, columna, filaSiguiente, columna)) {
					int valor = grilla.getCasillero(filaActual, columna).valor() * 2;
					puntaje.acumularPuntos(valor);
					grilla.asignarNumero(filaActual, columna, valor);
					grilla.removerNumero(filaSiguiente, columna);
					bandera = true;
				}
			}
		}
		combinarCasillerosArr(columna, filaActual + 1, filaSiguiente + 1);
	}

	public boolean moverAbajo() {
		bandera = false;
		for (int columna = 0; columna < grilla.cantColumnas(); columna++) {
			moverCasillerosAba(columna, grilla.cantFilas() - 1, grilla.cantFilas() - 2);
			combinarCasillerosAba(columna, grilla.cantFilas() - 1, grilla.cantFilas() - 2);
			moverCasillerosAba(columna, grilla.cantFilas() - 1, grilla.cantFilas() - 2);
		}
		return bandera;
	}

	private void moverCasillerosAba(int columna, int filaActual, int filaSiguiente) {
		if (filaSiguiente < 0) {
			return;
		} else {
			if (!grilla.casilleroOcupado(filaActual, columna)) {
				if (grilla.casilleroOcupado(filaSiguiente, columna)) {
					grilla.asignarNumero(filaActual, columna, grilla.getCasillero(filaSiguiente, columna).valor());
					grilla.asignarNumero(filaSiguiente, columna, 0);
					bandera = true;
					moverCasillerosAba(columna, filaActual - 1, filaSiguiente - 1);
				} else {
					moverCasillerosAba(columna, filaActual, filaSiguiente - 1);
				}
			} else {
				moverCasillerosAba(columna, filaActual - 1, filaSiguiente - 1);
			}
		}
	}

	private void combinarCasillerosAba(int columna, int filaActual, int filaSiguiente) {
		if (filaSiguiente < 0) {
			return;
		} else {
			if (grilla.casilleroOcupado(filaActual, columna)) {
				if (grilla.casilleroOcupado(filaSiguiente, columna)) {
					if (grilla.casillerosIguales(filaActual, columna, filaSiguiente, columna)) {
						int valor = grilla.getCasillero(filaActual, columna).valor() * 2;
						puntaje.acumularPuntos(valor);
						grilla.asignarNumero(filaActual, columna, valor);
						grilla.removerNumero(filaSiguiente, columna);
						bandera = true;
					}
				}
			}
			combinarCasillerosAba(columna, filaActual - 1, filaSiguiente - 1);
		}
	}

	public String mostrar() {
		return grilla.toString();
	}

	public void agregarNumero() {
		if (grilla.cantidadCasilleros() == grilla.casillerosOcupados())
			throw new RuntimeException("No se pueden agregar mas numeros");
		grilla.agregarNumeroAleatorio();
	}

	public Integer puntajeActual() {
		return this.puntaje.getPuntos();
	}
	
	public boolean nuevaPuntuacionMaxima()
	{
		return puntajesMaximos.nuevoPuntajeAlto(puntaje);
	}
	
	public void grabarPuntaje(String nombre)
	{
		if(nombre == null) return;
		puntaje.setNombre(nombre);
		puntajesMaximos.grabarPuntajeNuevo(puntaje);
	}
	
	public boolean hayMovimientosDisponibles() 
	{
		if(grilla.existeCasillero2048()) return false;
		if (grilla.cantidadCasilleros() == grilla.casillerosOcupados()) 
		{
			Tablero t = new Tablero(this);
			if (!t.moverAbajo() && !t.moverArriba() && !t.moverDerecha() && !t.moverIzquierda())
				return false;
			else
				return true;
		} 
		else
			return true;
	}
	
	public boolean victoria()
	{
		return grilla.existeCasillero2048();
	}

}
