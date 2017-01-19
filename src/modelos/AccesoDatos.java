package modelos;

import java.io.*;
import java.util.*;

public class AccesoDatos {
	
	public static void grabar(String nombreArchivo, ArrayList<String> cadenas)
	{
		try 
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(nombreArchivo));
            for(int i=0; i < cadenas.size(); i++)
            {
            	out.write(cadenas.get(i));
            	if(i < cadenas.size()-1) out.write(":");
            }
			out.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private static boolean existeArchivo(String nombreArchivo)
	{
		File f = new File( nombreArchivo );
		return f.exists();
	}
	
	public static ArrayList<String> leer(String nombreArchivo)
	{
		
        ArrayList<String> cadenas = new ArrayList<String>();
        if(!existeArchivo(nombreArchivo)) return cadenas;
        File f = new File( nombreArchivo );
		BufferedReader entrada;
		try 
		{
			entrada = new BufferedReader( new FileReader( f ) );
			String[] linea;
			while(entrada.ready())
			{
				linea = entrada.readLine().split(":");
				cadenas.addAll(Arrays.asList(linea));
				break;
			}
			entrada.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return cadenas;
	}
	
	public static ArrayList<ArrayList<String>> leerTodo(String nombreArchivo)
	{
		ArrayList<ArrayList<String>> cadenas = new ArrayList<ArrayList<String>>();
		if(!existeArchivo(nombreArchivo)) return cadenas;
        File f = new File( nombreArchivo );
		BufferedReader entrada;
		try 
		{
			entrada = new BufferedReader( new FileReader( f ) );
			String[] linea;
			while(entrada.ready())
			{
				linea = entrada.readLine().split(":");
				cadenas.add(new ArrayList<String>(Arrays.asList(linea)));
			}
			entrada.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return cadenas;
	}
	
	public static void grabarTodo(String nombreArchivo, ArrayList<ArrayList<String>> cadenas)
	{
		try 
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(nombreArchivo));
            for(int i = 0; i < cadenas.size(); i++)
            {
            	for(int j = 0; j < cadenas.get(i).size(); j++)
            	{
            		out.write(cadenas.get(i).get(j));
                	if(j < cadenas.get(i).size()-1) out.write(":");
            	}
            	out.newLine();
            }
			out.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
