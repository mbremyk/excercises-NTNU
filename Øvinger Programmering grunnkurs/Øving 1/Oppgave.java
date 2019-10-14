import static javax.swing.JOptionPane.*;

class Oppgave
{
	static float cmITommer = 2.54f;
	static float tommerICm = 1 / 2.54f;

	public static void main(String[] args)
	{
		String valg = showInputDialog("(L)engde, (T)imerMinSek eller (S)ekunder?");
		if(valg.equals("L"))tommerCentimeter();	//Konverterer mellom tommer og centimeter
		if(valg.equals("T"))timerMinSek();		//Konverterer fra timer, minutter og sekunder til sekunder
		if(valg.equals("S"))sekTid();			//Konverterer fra sekunder til timer, minutter og sekunder
	}

	private static void tommerCentimeter()
	{
		/*
		*	Konverterer mellom tommer og centimeter
		*	Kan ta enten tommer eller centimeter og konvertere til den andre med konverteringsfaktor 2.54
		*/

		String input = showInputDialog("(T)ommer eller (C)entimeter?");

		if(input.equals("T"))		//Konverterer fra tommer til centimeter. 1" = 2.54cm
		{
			float tommer = Float.parseFloat(showInputDialog("Antall tommer: "));
			float cm = tommer * cmITommer;
			System.out.println(cm);
		}
		else if (input.equals("C"))	//Konverterer fra centimeter til tommer. 2.54cm = 1"
		{
			float cm = Float.parseFloat(showInputDialog("Antall cm: "));
			float tommer = cm * tommerICm;
			System.out.println(tommer);
		}
		else
		{
			tommerCentimeter();
		}
	}

	public static void timerMinSek()
	{
		/*
		*	Konverterer fra timer, minutter og sekunder til sekunder
		*	Tar timer, minutter og sekunder i separate dialogbokser som heltall
		*/
		int timer = Integer.parseInt(showInputDialog("Timer: "));
		int minutter = Integer.parseInt(showInputDialog("Minutter: "));
		int sekunder = Integer.parseInt(showInputDialog("Sekunder: "));

		int ut = timer * 3600 + minutter * 60 + sekunder;

		System.out.println(ut);
	}

	public static void sekTid()
	{
		/*
		*	Konverterer fra sekunder til timer, minutter og sekunder
		*	Tiden vises i format "tt:mm:ss"
		*/
		int sek = Integer.parseInt(showInputDialog("Sekunder: "));

		int timer = sek / 3600;
		int minutter = (sek % 3600) / 60;
		int sekunder = sek % 60;

		System.out.println(timer + ":" + minutter + ":" + sekunder);
	}
}