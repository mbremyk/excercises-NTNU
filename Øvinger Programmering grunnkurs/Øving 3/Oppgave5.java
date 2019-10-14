import java.util.Scanner;

class Oppgave5
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);

		int num = sc.nextInt();

		boolean prime = true;

		if (num % 2 == 0 || num == 1) 
		{
			prime = false;
		}
		else
		{
			for (int i = 3; i <= Math.sqrt(num); i += 2) 
			{
				//System.out.println(i);
				if (num % i == 0) 
				{
					prime = false;
					break;
				}
			}
		}

		System.out.println(prime ? "Prime!" : "Not prime!");
	}
}