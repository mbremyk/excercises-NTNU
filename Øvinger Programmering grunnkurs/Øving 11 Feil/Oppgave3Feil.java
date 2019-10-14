import java.io.*;
import java.util.Scanner;

class Oppgave3Feil
{
	public static void main(String[] args) throws IOException
	{
		Scanner s = new Scanner(System.in);

		String balanceFile = "balance.txt";												//Leser saldo før transaksjoner
		FileReader balanceReaderConnection = new FileReader(balanceFile);
		BufferedReader balanceReader = new BufferedReader(balanceReaderConnection);

		int balance = Integer.parseInt(balanceReader.readLine());
		int prevBalance = balance;

		balanceReader.close();															//Slutt på lesing

		String transactionsFile = "transactions.txt";									//Leser inn forrige sesjon med transaksjoner i tilfelle sluttsaldo er negativ
		FileInputStream transactionsReaderConnection = new FileInputStream(transactionsFile);
		BufferedReader transactionsReader = new BufferedReader(new InputStreamReader(transactionsReaderConnection));

		String[] prevTransactions = new String[(int)transactionsReader.lines().count()];

		transactionsReaderConnection.getChannel().position(0);
		transactionsReader = new BufferedReader(new InputStreamReader(transactionsReaderConnection));

		for(int i = 0; i < prevTransactions.length; i++)
		{
			prevTransactions[i] = transactionsReader.readLine();
			System.out.println(prevTransactions[i]);
		}

		transactionsReader.close();														//Slutt på lesing
		
		File file = new File(transactionsFile);											//Lager en ny transactions.txt fil

		FileWriter transactionsWriterConnection = new FileWriter(transactionsFile);		//Skriver for transaksjoner
		PrintWriter transactionsWriter = new PrintWriter(new BufferedWriter(transactionsWriterConnection));

		int transactions = 0;
		loop:
		while(true)
		{
			System.out.print("In or Out: ");
			String io = s.next();

			try
			{
				int t;
				switch(io.toLowerCase())
				{
					case "i":
					case "in":
					case "inn":
						t = Integer.parseInt(s.next());									//Transaksjon +
						balance += t;
						transactionsWriter.println("I " + t);
						transactions++;
						break;
					case "o":
					case "out":
					case "u":
					case "ut":
						t = Integer.parseInt(s.next());									//Transaksjon -
						balance -= t;
						transactionsWriter.println("U " + t);
						transactions++;
						break;
					case "c":
					case "cancel":
					case "close":
					case "a":
					case "avbryt":
						t = 0;
						break loop;
					default:
						t = 0;
						throw new IllegalArgumentException("Not a valid command");
				}
			}
			catch(IllegalArgumentException e)
			{
				System.out.println(e);
			}
			catch (Exception e)
			{
				System.out.println(e);
				break;
			}
		}

		transactionsWriter.close();

		if(balance < 0 || transactions == 0)
		{
			file = new File(transactionsFile);
			transactionsWriterConnection = new FileWriter(transactionsFile);		//Skriver for transaksjoner
			transactionsWriter = new PrintWriter(new BufferedWriter(transactionsWriterConnection));

			for(String str : prevTransactions)
			{
				transactionsWriter.println(str);
			}

			transactionsWriter.close();
		}

		File balFile = new File(balanceFile);

		FileWriter balanceWriterConnection = new FileWriter(balanceFile);
		PrintWriter balanceWriter = new PrintWriter(balanceWriterConnection);

		if(balance >= 0)
		{
			balanceWriter.println(balance);
		}
		else
		{
			balanceWriter.println(prevBalance);
		}
		balanceWriter.close();
	}
}