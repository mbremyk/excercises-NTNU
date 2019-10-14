import java.util.Scanner;

class Oppgave2
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);

		Currency dollar = new Currency("dollar", 8.43f);
		Currency euro = new Currency("euro", 9.84f);
		Currency sek = new Currency("svenskejævler", 0.93f);

		Currency[] currencies = {dollar, euro, sek};

		while(true)
		{
			int iter = 0;
			System.out.println("Currency:");									//Skriver ut meny
			for (Currency currency : currencies)
			{
				System.out.println(iter++ + ": " + currency.getName());
			}

			System.out.println(iter++ + ": cancel");

			int choice = sc.nextInt();											//Leser menyvalg

			if(choice == iter - 1)												//Cancel
			{
				break;
			}
			else if(choice >= iter)												//Ugyldig valg
			{
				System.out.println("Not a valid choice!\n");
				continue;
			}

			System.out.println("\nConvert \n0: To NOK\n1: From NOK");			//Skriver ut menyvalg
			int toFrom = sc.nextInt();											//Leser menyvalg

			if(toFrom > 1)														//Ugyldig valg
			{
				System.out.println("Not a valid choice!\n");
				continue;
			}

			System.out.print("\nHow much?	");
			float cash = sc.nextFloat();										//Leser verdi som skal konverteres

			if(toFrom == 0)
			{
				System.out.println(cash + " " + currencies[choice].getName() + " is " + currencies[choice].convertToNok(cash) + "NOK");
			}	
			else if(toFrom == 1)
			{
				System.out.println(cash + "NOK is " + currencies[choice].convertFromNok(cash) + " " + currencies[choice].getName());
			}		
		}
	}
}

class Currency
{
	private final String name;
	private float value;

	public Currency(String name, float value)
	{
		this.name = name;
		this.value = value;
	}

	public String getName()
	{
		return this.name;
	}

	public float getValue()
	{
		return this.value;
	}

	public void setValue(float value)
	{
		this.value = value;
	}

	public float convertToNok(float cash)
	{
		return cash * this.value;
	}

	public float convertFromNok(float cash)
	{
		return cash / this.value; 
	}
}