package vistas;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//Clase para ingresar texto en consola
public class Entrada {
	public static String readLine()
	{
		String s = "";
		try {
			InputStreamReader converter = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(converter);
			s = in.readLine();
		} catch (Exception e) {
			System.out.println("Error! Exception: "+e); 
		}
		return s;
	}
	
}
