import java.util.Scanner;

class Oppgave1
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		NewString s = new NewString(sc.nextLine());
		System.out.println(s.getAbbreviation());

		System.out.println(s.remove(sc.next()));
	}
}

class NewString
{
	private final String string;
	private final String abbreviation;

	public NewString(String string)
	{
		this.string = string;
		this.abbreviation = abbreviate();
	}

	private String abbreviate()
	{
		String[] words = this.string.split(" ");
		String abb = "";

		for(String s : words)
		{
			abb += s.charAt(0);
		}

		return abb;
	}

	public String getAbbreviation()
	{
		return abbreviation;
	}

	public String remove(String c)
	{
		String out = "";
		String in = this.string;
		/*int startIndex = 0;
		while(this.string.indexOf(c, startIndex) >= 0)
		{
			out += this.string.substring(startIndex, this.string.indexOf(c, startIndex));
			startIndex = this.string.indexOf(c, startIndex) + 1;
		}
		out += this.string.substring(startIndex, this.string.length());*/
		out = in.replace(c, "");

		return out;
	}
}