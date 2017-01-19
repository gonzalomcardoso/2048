package vistas;

import controladores.Grilla;

public class FormGrilla 
{
	private FormCasillero[][] grillaVisual;
	public FormGrilla(Grilla grilla)
	{
		grillaVisual = new FormCasillero[grilla.cantFilas()][grilla.cantColumnas()];
		for(int i = 0; i < grilla.cantFilas(); i++)
		{
			for(int j = 0; j < grilla.cantColumnas(); j++)
			{
				grillaVisual[i][j] = new FormCasillero();
			}
		}
	}
	
	public void actualizar(Grilla grilla)
	{
		for(int i = 0; i < grilla.cantFilas(); i++)
		{
			for(int j = 0; j < grilla.cantColumnas(); j++)
			{
				grillaVisual[i][j] = new FormCasillero(grilla.getCasillero(i, j).valor());
			}
		}
	}
	
	public FormCasillero getCasillero(int fila, int columna)
	{
		return grillaVisual[fila][columna];
	}
	
}
