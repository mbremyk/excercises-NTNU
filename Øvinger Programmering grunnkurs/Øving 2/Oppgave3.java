import java.util.Scanner;

class Oppgave3
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Kva for år vil du ha? "); //Hvilket år vil du ha?
		int year = sc.nextInt();

		if(((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) && (year != 0))
		{
			System.out.println("Skuddår!");
		}
		else System.out.println("Kjedeleg år!");
	}
}