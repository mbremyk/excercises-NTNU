import java.util.Random;
import java.util.Arrays;

class Oppgave1
{
	public static void main(String[] args) 
	{
		Random rng = new Random();

		float[] count = new float[10];
		Arrays.fill(count, 0f);

		float rounds = 10000f;
		for (int i = 0; i < rounds; i++) 
		{
			count[rng.nextInt(10)]++;
		}

		for(float i : count)
		{
			System.out.print(i + "\t");
			i = (float)Math.round(i / 100) * 100f;
			for (int j = 0; j < i / rounds * 100; j++) 
			{
				System.out.print("*");
			}
			System.out.println();
		}
	}
}