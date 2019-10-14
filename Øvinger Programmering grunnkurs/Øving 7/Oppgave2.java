import java.util.Arrays;

class Oppgave2
{
	public static void main(String[] args) 
	{
		int[][] month = new int[30][24];

		for(int i = 0; i < 30; i++)
		{
			for (int j = 0; j < 24; j++) 
			{
				month[i][j] = (int)(5 * Math.sin((j - 6) * Math.PI / 12) + 5 + 5 * Math.sin(i));
				System.out.print(month[i][j] + " ");
			}
			System.out.println();
		}

		Temperatures t = new Temperatures(month);

		System.out.println(t.getMidTempDay(7));

		System.out.println(t.midTempMonth());

		for(int i : t.tempSpread())
		{
			System.out.print(i + "\t");
		}
	}
}

class Temperatures
{
	int days = 30;
	int hours = 24;

	int[][] temps = new int[days][hours];

	public Temperatures(int[][] temps)
	{
		this.temps = temps;
	}

	private int midTempDay(int day)				//Finner middeltemperaturen for en enkelt dag
	{
		int midTemp = 0;
		int[] d = temps[day];
		for(int i : d)
		{
			midTemp += i;
		}
		midTemp /= hours;

		return midTemp;
	}

	public int getMidTempDay(int day)			//Returnerer middeltemperaturen for en enkelt dag
	{
		return midTempDay(day);
	}

	public int[] midTempDays()					//Returnerer et array med middeltemperaturen for alle dagene
	{
		int[] midTemps = new int[temps.length];
		for(int i = 0; i < days; i++)
		{
			midTemps[i] = midTempDay(i);
		}

		return midTemps;
	}

	private int midTempHour(int hour)			//Finner middeltemperaturen for ett tidspunkt på dagen
	{
		int hourMidTemp = 0;
		for(int i = 0; i < days; i++)
		{
			hourMidTemp += temps[i][hour];
		}
		hourMidTemp /= days;

		return hourMidTemp;
	}

	public int[] midTempHours()					//Returnerer middeltemperaturen for alle tidspunkt
	{
		int[] hourMidTemp = new int[hours];

		for(int i = 0; i < hours; i++)
		{
			hourMidTemp[i] = midTempHour(i);
		}

		return hourMidTemp;
	}

	public int midTempMonth()					//Returnerer middeltemperaturen for hele måneden
	{
		int[] tempDay = midTempDays();
		int midTemp = 0;
		for(int i : tempDay)
		{
			midTemp += i;
		}
		midTemp /= days;

		return midTemp;
	}

	public int[] tempSpread()					//Returnerer spredningen til middeltemperaturen, {<-5, -5/0, 0/5, 5/10, 10<}
	{
		int[] spread = new int[5];
		Arrays.fill(spread, 0);

		int[] mids = midTempDays();

		for(int i : mids)
		{
			if(i < -5)
			{
				spread[0]++;
			}
			else if(i < 0)
			{
				spread[1]++;
			}
			else if(i < 5)
			{
				spread[2]++;
			}
			else if(i < 10)
			{
				spread[3]++;
			}
			else
			{
				spread[4]++;
			}
		}

		return spread;
	}
}