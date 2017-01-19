package vistas;

import controladores.Tablero;

public class Consola {
	public static void main(String[] args) {
		Tablero tablero = new Tablero(4, 4);
		tablero.agregarNumero();
		tablero.agregarNumero();

		String opcion;
		String opciones;
		
		

		while (tablero.hayMovimientosDisponibles()) {
			opciones = "4 Izquierda 6 Derecha 8 Arriba 2 Abajo\nPuntos " + tablero.puntajeActual();

			System.out.println(tablero.mostrar());
			System.out.println();
			System.out.println(opciones);
			opcion = Entrada.readLine();

			switch (opcion) {
			case "4":
				if (tablero.moverIzquierda()) {
					tablero.agregarNumero();
				}
				break;
			case "6":
				if (tablero.moverDerecha()) {
					tablero.agregarNumero();
				}
				break;
			case "8":
				if (tablero.moverArriba()) {
					tablero.agregarNumero();
				}
				break;
			case "2":
				if (tablero.moverAbajo()) {
					tablero.agregarNumero();
				}
				break;
			default:
				System.out.println("opcion erronea");
			}

		}
		System.out.println(tablero.mostrar());
		System.out.println("Game Over");
		System.out.println("Puntaje: " + tablero.puntajeActual());
		if(tablero.nuevaPuntuacionMaxima())
		{
			System.out.println("Ingrese su nombre");
			tablero.grabarPuntaje(Entrada.readLine());
			System.out.println("Datos Guardados. Good luck!");
		}
		

	}

}
