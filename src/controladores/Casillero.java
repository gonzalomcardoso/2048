package controladores;

public class Casillero 
{
	private Integer numero;
	
	public Casillero(Integer n)
	{
		invariante(n);
		numero = n;
	}
	
	public Casillero()
	{
		numero = 0;
	}
	
	private void invariante(Integer n)
	{
		if(n == null) lanzarExcepcion("No puede ser null el numero");
		if(!esPotenciaDe2(n)) lanzarExcepcion("El numero no es una potencia de 2 positiva");
	}
	
	private boolean esPotenciaDe2(int n)
	{
		if(n == 0) return true;
		return ((n & (n - 1)) == 0);
	}
	
	private void lanzarExcepcion(String error)
	{
		throw new RuntimeException(error);
	}
	public Integer valor()
	{
		return this.numero;
	}
	
	public String toString()
	{
		if(numero == 0) return " - ";
		else return " " + numero + " ";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Casillero other = (Casillero) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	
	public boolean estaVacio()
	{
		return numero == 0;
	}
	}
