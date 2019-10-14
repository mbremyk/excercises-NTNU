import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;

class Oppgave3
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		TextAnalysis t = new TextAnalysis(sc.nextLine());
		//TextAnalysis t = new TextAnalysis("Hei på deg, smurf i vei, her er vi igjen!ÅåÅå");
		int timesChar = t.getTimesChar((String)sc.next());
		System.out.print(t.getDiffChars() + "\t" + t.getChars() + "\t" + t.getProsSign() + "%\t" + (timesChar >= 0 ? timesChar : "Not a letter") + "\t");
		for(char c : t.largestValues())
		{
			System.out.print(c + " ");
		}
		System.out.println(t);
	}
}

class TextAnalysis
{
	private final String text;
	private int[] numChar = new int[27];
	private int chars = 0;

	public TextAnalysis(String text)
	{
		this.text = text.toLowerCase();

		Arrays.fill(numChar, 0);


		textCount();
		chars = charCount();
	}

	public int getChars()
	{
		return this.chars;
	}

	public int getDiffChars()
	{
		int diffChars = 0;
		for(int i = 0; i < (numChar.length - 1); i++)
		{
			if(numChar[i] > 0)
			{
				diffChars++;
			}
		}
		return diffChars;
	}

	public float getProsSign()
	{
		return (float)numChar[numChar.length - 1] / (float)(this.chars + numChar[numChar.length - 1]) * 100;
	}

	private void textCount()
	{
		for (int i = 0; i < this.text.length(); i++) 
		{
			char c = this.text.charAt(i);
			int j = c;

			if (j < 97 || j > 122) 
			{
				numChar[numChar.length - 1]++;
			}
			else
			{
				numChar[((int)j) - 97]++;
			}
		}
	}

	private int charCount()
	{
		int chars = 0;
		for (int i = 0; i < numChar.length - 1; i++) 
		{
			chars += numChar[i];
		}

		return chars;
	}

	public int getTimesChar(String c)
	{
		int i = c.toLowerCase().charAt(0);
		i -= 97;

		try
		{
			return numChar[i];
		}
		catch(Exception e)
		{
			return -1;
		}
	}

	public char[] largestValues()
	{
		int largestValue = 0;
		char[] mostChars = new char[29];
		int iter = 0;

		for(int i = 0; i < numChar.length - 1; i++)
		{
			if(numChar[i] > largestValue)
			{
				largestValue = numChar[i];
				mostChars = new char[29];
				mostChars[0] = (char)(i + 97);
				iter = 1;
			}
			else if(numChar[i] == largestValue)
			{
				mostChars[iter++] = (char)(i + 97);
			}
		}

		return mostChars;
	}

	public String toString()
	{
		return this.text;
	}
}