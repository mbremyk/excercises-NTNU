import java.util.Scanner;

class Oppgave2
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		MyRandom rng = new MyRandom();

		{	//tester Fraction.add(Fraction frac)
			Fraction frac1 = new Fraction(rng.nextInt(0, 10) + 1, rng.nextInt(0, 10) + 1);
			Fraction frac2 = new Fraction(rng.nextInt(0, 10) + 1, rng.nextInt(0, 10) + 1);

			System.out.println(frac1);
			System.out.println(frac2 + "\n");

			frac1.add(frac2);

			System.out.println(frac1);
			System.out.println(frac2 + "\n");
		}


		{	//tester Fraction.subtract(Fraction frac)
			Fraction frac1 = new Fraction(rng.nextInt(0, 10) + 1, rng.nextInt(0, 10) + 1);
			Fraction frac2 = new Fraction(rng.nextInt(0, 10) + 1, rng.nextInt(0, 10) + 1);

			System.out.println(frac1);
			System.out.println(frac2 + "\n");

			frac1.subtract(frac2);

			System.out.println(frac1);
			System.out.println(frac2 + "\n");
		}

		{	//tester Fraction.multiply(Fraction frac)
			Fraction frac1 = new Fraction(rng.nextInt(0, 10) + 1, rng.nextInt(0, 10) + 1);
			Fraction frac2 = new Fraction(rng.nextInt(0, 10) + 1, rng.nextInt(0, 10) + 1);

			System.out.println(frac1);
			System.out.println(frac2 + "\n");

			frac1.multiply(frac2);

			System.out.println(frac1);
			System.out.println(frac2 + "\n");
		}

		{	//tester Fraction.divide(Fraction frac)
			Fraction frac1 = new Fraction(rng.nextInt(0, 10) + 1, rng.nextInt(0, 10) + 1);
			Fraction frac2 = new Fraction(rng.nextInt(0, 10) + 1, rng.nextInt(0, 10) + 1);

			System.out.println(frac1);
			System.out.println(frac2 + "\n");

			frac1.divide(frac2);

			System.out.println(frac1);
			System.out.println(frac2 + "\n");
		}
	}
}

class Fraction
{
	private int num;
	private int denom;

	private double val()
	{
		this.reduce();
		if(this.denom == 0)throw new ArithmeticException("Denominator is 0");
		return (double)this.num / (double)this.denom;
	}

	public Fraction(int num, int denom)
	{
		this.num = num;
		this.denom = denom;
		if(denom == 0)
		{
			throw new IllegalArgumentException("Division by zero!");
		}

		this.val();
	}

	public Fraction(int num)
	{
		this.num = num;
		this.denom = 1;

		this.val();
	}

	public int getNumerator()
	{
		return this.num;
	}

	public int getDenominator()
	{
		return this.denom;
	}

	public double getValue()
	{
		return this.val();
	}

	public void add(Fraction frac)
	{
		int num1 = this.num; 
		int num2 = frac.getNumerator();

		int denom1 = this.denom;
		int denom2 = frac.getDenominator();

		int sumNum = 0;
		int sumDenom = 0;

		if(denom1 == denom2)
		{
			sumNum = num1 + num2;
			sumDenom = denom1;
		}
		else
		{
			sumNum = num1 * denom2 + num2 * denom1;
			sumDenom = denom1 * denom2;
		}

		this.num = sumNum;
		this.denom = sumDenom;
		this.val();
	}

	public void subtract(Fraction frac)
	{
		int num1 = this.num;
		int num2 = frac.getNumerator();

		int denom1 = this.denom;
		int denom2 = frac.getDenominator();

		int sumNum = 0;
		int sumDenom = 0;

		if(denom1 == denom2)
		{
			sumNum = num1 - num2;
			sumDenom = denom1;
		}
		else
		{
			sumNum = num1 * denom2 - num2 * denom1;
			sumDenom = denom1 * denom2;
		}

		this.num = sumNum;
		this.denom = sumDenom;
		this.val();
	}

	public void multiply(Fraction frac)
	{
		this.num = this.num * frac.getNumerator();
		this.denom = this.denom * frac.getDenominator();
		this.val();
	}

	public void divide(Fraction frac)
	{
		this.num = this.num * frac.getDenominator();
		this.denom = this.denom * frac.getNumerator();
		this.val();
		//this.multiply(new Fraction(frac.getDenominator(), frac.getNumerator()));
	}

	private void reduce()
	{
		double gcd = gcd(this.num, this.denom);
		this.num /= gcd;
		this.denom /= gcd;
	}

	public double gcd(int a, int b)
	{
		return ((b == 0) ? (a) : (gcd(b, a % b)));
	}

	public String toString()
	{
		return this.getNumerator() + " / " + this.getDenominator() + " = " + this.getValue();
	}
}
