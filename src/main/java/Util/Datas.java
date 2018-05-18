package Util;

import java.util.Calendar;

public class Datas 
{
	public static Integer anoAtual()
	{
		Calendar cal = Calendar.getInstance();
		int ano = cal.get(Calendar.YEAR);
		return ano;
	}
}
