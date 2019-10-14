import java.util.GregorianCalendar;
import java.io.Console;

class Oppgave1
{
	public static void main(String[] args) 
	{
		Console c = System.console();
		Worker Magnus = new Worker(new Person("Magnus", "Brevik", 1998), 48169, 2014, 16000, 10);

		System.out.println(Magnus.getFirstName() + " " + Magnus.getLastName() + ", born " + Magnus.getBirthYear() + "\n");

		System.out.println("Workernumber: " + Magnus.getWorkerNumber() + ", year of commencement: " + Magnus.getYearOfEmployment() + ", monthly salary: " + 
			Magnus.getMonthlySalary() + ", tax percent: " + Magnus.getTaxPercent() + "%, taxing: " + Magnus.getMonthlyTax(1) + "\n");

		System.out.println("Name: " + Magnus.getName() + ", age: " + Magnus.getAge() + ", time worked here: " + Magnus.getSeniority() + ", more than three years?: " + 
			Magnus.checkSeniority(3) + "\n");

		while(true)
		{
			System.out.println("What do you want to do?\n0: Change monthly salary\n1: Change tax percent\n2: Check name\n3: Check age\n4: Check workernumber\n" + 
				"5: Check commencement year\n6: Check monthly salary\n7: Check tax percent\n8: Check tax for specific month\n9: Check seniority");
			try
			{
				switch(Integer.parseInt(c.readLine()))
				{
					case 0:
					Magnus.setMonthlySalary(Double.parseDouble(c.readLine("New monthly salary: ")));
					break;
					case 1:
					Magnus.setTaxPercent(Double.parseDouble(c.readLine("New tax percent: ")));
					break;
					case 2:
					System.out.println("Name: " + Magnus.getName());
					break;
					case 3:
					System.out.println("Age: " + Magnus.getAge());
					break;
					case 4:
					System.out.println("Workernumber: " + Magnus.getWorkerNumber());
					break;
					case 5:
					System.out.println("Commencement year: " + Magnus.getYearOfEmployment());
					break;
					case 6:
					System.out.println("Monthly salary: " + Magnus.getMonthlySalary());
					break;
					case 7:
					System.out.println("Tax percent: " + Magnus.getTaxPercent() + "%");
					break;
					case 8:
					int month = Integer.parseInt(c.readLine());
					System.out.println("Tax for month " + month + ": " + Magnus.getMonthlyTax(month));
					break;
					case 9:
					int years = Integer.parseInt(c.readLine());
					System.out.println((Magnus.checkSeniority(years) ? "S" : "Not s") + "enior of " + years + " years");
					break;
					default:
					break;
				}
			}
			catch(Exception e) { System.out.println(e); }
			System.out.println();

		}
	}
}

class Person
{
	private final String firstName;
	private final String lastName;
	private final int birthYear;

	public Person(String firstName, String lastName, int birthYear)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthYear = birthYear;
	}

	public String getFirstName()
	{
		return this.firstName;
	}

	public String getLastName()
	{
		return this.lastName;
	}

	public int getBirthYear()
	{
		return this.birthYear;
	}
}

class Worker extends Person
{
	private final int workerNumber;
	private final int yearOfEmployment;
	private double monthlySalary;
	private double taxPercent;
	private double monthlyTax;
	private GregorianCalendar calendar = new GregorianCalendar();

	public Worker (Person p, int workerNumber, int yearOfEmployment, double monthlySalary, double taxPercent)
	{
		super(p.getFirstName(), p.getLastName(), p.getBirthYear());
		this.workerNumber = workerNumber;
		this.yearOfEmployment = yearOfEmployment;
		this.monthlySalary = monthlySalary;
		this.taxPercent = taxPercent;
	}

	public Worker (String firstName, String lastName, int birthYear, int workerNumber, int yearOfEmployment, double monthlySalary, double taxPercent)
	{
		super(firstName, lastName, birthYear);
		this.workerNumber = workerNumber;
		this.yearOfEmployment = yearOfEmployment;
		this.monthlySalary = monthlySalary;
		this.taxPercent = taxPercent;
	}

	public int getWorkerNumber()
	{
		return this.workerNumber;
	}

	public int getYearOfEmployment()
	{
		return this.yearOfEmployment;
	}

	public double getMonthlySalary()
	{
		return monthlySalary;
	}

	public double getTaxPercent()
	{
		return taxPercent;
	}

	public double getMonthlyTax(int month)
	{
		double tax = getGrossYearlyIncome() * taxPercent / 100 / 10.5;
		if(month == 6)
		{
			return 0;
		}
		else if (month == 12)
		{
			return tax / 2;
		}
		else if(month > 0 && month <= 12)
		{
			return tax;
		}
		else
		{
			throw(new IllegalArgumentException("Not a month"));
		}
	}

	public double getGrossYearlyIncome()
	{
		return monthlySalary * 12;
	}

	public String getName()
	{
		return this.getLastName() + ", " + this.getFirstName();
	}

	public int getAge()
	{
		return calendar.get(calendar.YEAR) - this.getBirthYear();
	}

	public int getSeniority()
	{
		return calendar.get(calendar.YEAR) - this.yearOfEmployment;
	}

	public Boolean checkSeniority(int years)
	{
		return this.getSeniority() > years;
	}

	public double setMonthlySalary(double monthlySalary)
	{
		this.monthlySalary = monthlySalary;
		return this.monthlySalary;
	}

	public double setTaxPercent(double taxPercent)
	{
		this.taxPercent = taxPercent;
		return this.taxPercent;
	}
}