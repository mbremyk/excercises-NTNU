import java.util.Random;

class Oppgave3
{
	public static void main(String[] args) 
	{
		Random ran = new Random();
		MyRandom rng = new MyRandom();

		for(int i = 0; i < 100; i++)
		{
			int lower = ran.nextInt(10) - 5;
			int upper = ran.nextInt(10) + lower + 1;
			int num = rng.nextInt(lower, upper);
			System.out.println(num + "\t" + lower + "\t" + upper + "\t" + (((num >= lower && num < upper) || (num < lower && num >= upper)) ? "1" : "0"));
		}

		for(int i = 0; i < 100; i++)
		{
			double lower = ran.nextDouble() * 5 - 5;
			double upper = ran.nextDouble() * 5 + lower + 1d;
			double num = rng.nextDouble(lower, upper);
			System.out.println(num + "\t" + lower + "\t" + upper + "\t" + (((num >= lower && num < upper) || (num < lower && num >= upper)) ? "1" : "0"));
		}

		int lower = Integer.parseInt(System.console().readLine());
		int upper = Integer.parseInt(System.console().readLine());
		double num = rng.nextDouble(lower, upper);
		System.out.println(num);
	}
}

class MyRandom
{
	private Random rng = new Random();

	public MyRandom(){}

	public int nextInt(int min, int max)
	{
		int interval = max - min;
		int out = (int)(rng.nextDouble() * (double)interval);
		out += min;

		return out;
	}

	public double nextDouble(double min, double max)
	{
		double interval = max - min;
		double out = rng.nextDouble() * interval;
		out += min;

		return out;
	}
}