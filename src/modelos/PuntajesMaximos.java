package modelos;

import java.util.ArrayList;

public class PuntajesMaximos 
{
	private ArrayList<Puntaje> puntajes;
	private String nombreArchivo = "puntuacionesMaximas.txt";
	public PuntajesMaximos()
	{
		puntajes = leerArchivo();
		if(puntajes.size() == 0)
		{
			crearPuntajesIniciales();
		}
	}
	
	public boolean nuevoPuntajeAlto(Puntaje p2)
	{
		for(Puntaje p1 : puntajes)
		{
			if(p2.getPuntos() > p1.getPuntos())
				return true;
		}
		return false;
	}
	
	private ArrayList<Puntaje> leerArchivo()
	{
		ArrayList<Puntaje> puntajes = new ArrayList<Puntaje>();
		ArrayList<ArrayList<String>> registros = AccesoDatos.leerTodo(nombreArchivo);
		if(registros.isEmpty()) return puntajes;
		for(ArrayList<String> registro : registros)
		{
			puntajes.add(new Puntaje(registro.get(0),Integer.parseInt(registro.get(1))));
		}
		return puntajes;
	}
	
	private void crearPuntajesIniciales()
	{
		puntajes = new ArrayList<Puntaje>();
		ArrayList<ArrayList<String>> cadenas = new ArrayList<ArrayList<String>>();
		for(int i = 10; i > 0; i--)
		{
			Puntaje p = new Puntaje("Capo"+i,i*10);
			puntajes.add(p);
			ArrayList<String> cadena = new ArrayList<String>();
			cadena.add(p.getNombre());
			cadena.add(String.valueOf(p.getPuntos()));
			cadenas.add(cadena);
		}
		AccesoDatos.grabarTodo(nombreArchivo, cadenas);
	}

	public void grabarPuntajeNuevo(Puntaje puntaje) 
	{
		int pos = posicionPuntajeNuevo(puntaje);
		if(pos == -1) return;
		puntajes.add(pos, puntaje);
		puntajes.remove(10);
		
		ArrayList<ArrayList<String>> cadenas = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < puntajes.size(); i++)
		{
			ArrayList<String> cadena = new ArrayList<String>();
			cadena.add(puntajes.get(i).getNombre());
			cadena.add(String.valueOf(puntajes.get(i).getPuntos()));
			cadenas.add(cadena);
		}
		AccesoDatos.grabarTodo(nombreArchivo, cadenas);
	}
	
	private int posicionPuntajeNuevo(Puntaje p)
	{
		for(int i = 0; i < puntajes.size(); i++)
		{
			if(puntajes.get(i).getPuntos() < p.getPuntos())
				return i;
		}
		return -1;
	}
	
	public int tamanio()
	{
		return puntajes.size();
	}
	
	public Puntaje posicion(int pos)
	{
		return puntajes.get(pos);
	}
	
	public ArrayList<Puntaje> obtenerTodos()
	{
		return puntajes;
	}
	
}
