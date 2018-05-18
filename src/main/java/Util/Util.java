package Util;

import java.util.ArrayList;
import java.util.List;

import Entidade.Casal;
import Entidade.OrientadorEspiritual;
import Entidade.TipoEquipe;

public abstract class Util 
{
	public static String[][] parseToMatrix(List<Casal> casais)
	{
		String[][] casaisOut = new String[casais.size()][4];
		for(int i=0;i<casais.size();i++)
		{
			casaisOut[i][0] = casais.get(i).getApelidoDoCasal();
			casaisOut[i][1] = casais.get(i).getNomeDela();
			casaisOut[i][2] = casais.get(i).getNomeDele();
			casaisOut[i][3] = casais.get(i).getTelefoneDela()+";"+casais.get(i).getTelefoneDele();
		}
		return casaisOut;
	}
	public static String[][] parseToMat(List<Casal> casais)
	{
		if(casais==null)
		{
			//System.out.println("RETORNO");
			return new String[0][0];
		}
		String[][] casaisOut = new String[casais.size()][4];
		for(int i=0;i<casais.size();i++)
		{
			casaisOut[i][0] = casais.get(i).getApelidoDoCasal();
			casaisOut[i][1] = casais.get(i).getNomeDela();
			casaisOut[i][2] = casais.get(i).getNomeDele();
			casaisOut[i][3] = null;
		}
		return casaisOut;
	}
	public static String[][] parseToMatx(List<Casal> casais)
	{
		if(casais==null)
		{
			//System.out.println("RETORNO");
			return new String[0][0];
		}
		String[][] casaisOut = new String[casais.size()][3];
		for(int i=0;i<casais.size();i++)
		{
			casaisOut[i][0] = casais.get(i).getApelidoDoCasal();
			casaisOut[i][1] = casais.get(i).getNomeDela();
			casaisOut[i][2] = casais.get(i).getNomeDele();
		}
		return casaisOut;
	}
	public static String[][] parseToM(List<OrientadorEspiritual> orientadorEspirituals)
	{
		String[][] orienEspiritualOut = new String[orientadorEspirituals.size()][3];
		for(int i=0;i<orientadorEspirituals.size();i++)
		{
			orienEspiritualOut[i][0] = orientadorEspirituals.get(i).getNome();
			orienEspiritualOut[i][1] = orientadorEspirituals.get(i).getTelefone();
			orienEspiritualOut[i][2] = null;
		}
		return orienEspiritualOut;
	}
	public static String[] parseToMa(List<TipoEquipe> tipoEquipes)
	{
		String[] tipoEquipe = new String[tipoEquipes.size()];
		for(int i=0;i<tipoEquipes.size();i++)
		{
			tipoEquipe[i] = tipoEquipes.get(i).getNome();
		}
		return tipoEquipe;
	}
}
