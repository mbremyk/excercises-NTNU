import java.io.*;
import java.util.Scanner;

class Oppgave3
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);

		String transactionsFile = "transactions.txt";									
		FileInputStream transactionsReaderConnection = new FileInputStream(transactionsFile);
		BufferedReader transactionsReader = new BufferedReader(new InputStreamReader(transactionsReaderConnection));

		int transactionsLines = (int)transactionsReader.lines().count();
		String[][] prevTransactions = new String[transactionsLines][2];

		transactionsReaderConnection.getChannel().position(0);
		transactionsReader = new BufferedReader(new InputStreamReader(transactionsReaderConnection));

		for(int i = 0; i < transactionsLines; i++)
		{
			prevTransactions[i] = transactionsReader.readLine().split(" ");
		}

		transactionsReader.close();

		String balanceFile = "balance.txt";									
		FileReader balanceReaderConnection = new FileReader(balanceFile);
		BufferedReader balanceReader = new BufferedReader(balanceReaderConnection);

		double balance = Double.parseDouble(balanceReader.readLine());
		double prevBalance = balance;

		balanceReader.close();												

		for(String[] s : prevTransactions)
		{
			switch(s[0].toLowerCase())
			{
				case "i":
					balance += Double.parseDouble(s[1]);
					break;
				case "u":
				case "o":
					balance -= Double.parseDouble(s[1]);
					break;
				default:
					throw new IOException();
			}
		}

		if(balance >= 0)
		{
			File balanceF = new File(balanceFile);

			FileWriter balanceWriterConnection = new FileWriter(balanceFile);
			PrintWriter balanceWriter = new PrintWriter(balanceWriterConnection);

			balanceWriter.println(balance);

			balanceWriter.close();
		}
	}
}