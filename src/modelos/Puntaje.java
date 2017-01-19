package modelos;

public class Puntaje {
	private Integer puntos;
	private String nombre;
	public Puntaje() {
		puntos = 0;
		nombre = "jugador";
	}
	
	public Puntaje(String nombre, Integer puntos)
	{
		this.puntos = puntos;
		this.nombre = nombre;
	}
	
	public Puntaje(Integer puntos) {
		this.puntos = puntos;
	}

	public void acumularPuntos(int puntosGanados) {
		this.puntos += puntosGanados;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public Integer getPuntos()
	{
		return puntos;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

}
