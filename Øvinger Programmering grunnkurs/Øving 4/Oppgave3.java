import java.util.Scanner;
import java.util.Random;

class Oppgave3
{
	public static void main(String[] args) 
	{
		Player A = new Player("A");
		Player B = new Player("B");

		Player[] players = {A, B};

		int iter = 0;
		do
		{
			System.out.println("Round " + ++iter + ": ");
			
			for(Player p : players)
			{
				System.out.print(p.name + ": " + p.throwDice() + " - Points: " + p.getPoints() + "	");
			}

			System.out.println();
		} while(!A.isDone() && !B.isDone());


	}
}

class Player
{
	public final String name;
	private Random rng;
	private int points = 0;

	public Player(String name)
	{
		this.name = name;
		this.rng = new Random();
	}

	public int getPoints()
	{
		return points;
	}

	public int throwDice()
	{
		int roll = rng.nextInt(6) + 1;
		if(roll == 1)
		{
			this.points = 0;
		}
		else
		{
			if(this.points < 100)
			{
				this.points += roll;
			}
			else if(this.points > 100)
			{
				this.points -= roll;
			}
		}

		return roll;
	}

	public boolean isDone()
	{
		if(this.points == 100)
		{
			return true;
		}

		return false;
	}
}