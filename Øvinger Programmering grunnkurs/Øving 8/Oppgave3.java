import java.util.Scanner;
import java.util.Vector;

class Oppgave3
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		Stronk s = new Stronk(sc.nextLine());

		System.out.println("Antall ord: \t\t\t" + s.countWords());
		System.out.println("Gjennomsnittlig ordlengde: \t" + s.averageWordLength());
		System.out.println("Gjennomsnittlig periodelengde: \t" + s.averagePeriodLength());
		System.out.println("Teksten: \t\t\t" + s.getString());
		System.out.println("TEKSTEN: \t\t\t" + s.getUpperCase());

		s.swap(sc.next(), sc.next());

		System.out.println("Antall ord: \t\t\t" + s.countWords());
		System.out.println("Gjennomsnittlig ordlengde: \t" + s.averageWordLength());
		System.out.println("Gjennomsnittlig periodelengde: \t" + s.averagePeriodLength());
		System.out.println("Teksten: \t\t\t" + s.getString());
		System.out.println("TEKSTEN: \t\t\t" + s.getUpperCase());
	}
}

class Stronk
{
	private String string;
	private String[] splitString;

	public Stronk(String string)
	{
		this.string = string;
		this.splitString = this.string.split(" ");
	}

	public int countWords()
	{
		return splitString.length;
	}

	public double averageWordLength()
	{
		String[] str = splitString;
		double length = 0;

		for(String s : str)
		{
			if(s.charAt(s.length() - 1) < 48 || s.charAt(s.length() - 1) > 57 && s.charAt(s.length() - 1) < 65 || s.charAt(s.length() - 1) > 90 && s.charAt(s.length() - 1) < 97 || s.charAt(s.length() - 1) > 122)
			{
				s = s.substring(0, s.length() - 1);
			}

			length += s.length();
		}

		length /= (double)str.length;

		return length;
	}

	public double averagePeriodLength()
	{
		String[] str = splitString;
		double avgLength = 0;
		double per = 0;
		Vector<Vector<String>> period = new Vector<Vector<String>>();
		Vector<String> p = new Vector<String>();

		for(String i : str)
		{
			if(i.charAt(i.length() - 1) == '.' || i.charAt(i.length() - 1) == ':' || i.charAt(i.length() - 1) == '!' || i.charAt(i.length() - 1) == '?')
			{
				i = i.substring(0, i.length() - 1);
				p.add(i);
				period.add(p);
				p = new Vector<String>();
			}
			else
			{
				p.add(i);
			}
		}

		if(p.iterator().hasNext())
		{
			period.add(p);
		}

		for(Vector<String> st : period)
		{
			per++;
			for(String s : st)
			{
				avgLength++;
			}
		}

		return avgLength / per;
	}

	public void swap(String out, String in)
	{
		String[] str = splitString;
		String s = "";

		for(int i = 0; i < str.length; i++)
		{
			if(str[i].equals(out))
			{
				str[i] = in;
			}
		}

		s = str[0];
		for(int i = 1; i < str.length; i++)
		{
			s += " " + str[i];
		}

		this.string = s;
		this.splitString = this.string.split(" ");
	}

	public String getString()
	{
		return this.string;
	}

	public String getUpperCase()
	{
		return this.string.toUpperCase();
	}
}