import java.util.Scanner;

class Oppgave1
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);

		System.out.print("Print from: ");
		int min = sc.nextInt();

		System.out.print("to: ");
		int max = sc.nextInt();

		if(max < min)
		{
			int k = min;
			min = max;
			max = k;
		}

		System.out.println();

		for (int i = min; i <= max; i++) 
		{
			System.out.println(i + "-gangen:");
			
			for (int j = 1; j <= 10; j++) 
			{
				System.out.println(i + " x " + j + " = " + (i * j));
			}

			System.out.println();
		}
	}
}