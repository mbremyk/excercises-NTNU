import java.io.Console;

class Oppgave1
{
	public static void main(String[] args) 
	{
		Console c = System.console();
		Taskoverview t = new Taskoverview();

		t.newStudent(c.readLine("Name of first student: "));

		System.out.println(t.toString());

		while(true)
		{
			try
			{
				int i = Integer.parseInt(c.readLine("\n0: Get students\n1: Add student\n2: Approve task/s\n3: cancel\n"));
				switch(i)
				{
					case 0: 
						System.out.println(t.toString());
						break;
					case 1:
						t.newStudent(c.readLine("Name of student: "));
						break;
					case 2: 
						t.approveTask(c.readLine("Name of student: "), Integer.parseInt(c.readLine("Number of tasks: ")));
						break;
					case 3:
						return;
					default:
						break;
				}
			}
			catch(Exception e)
			{
				System.out.println("Not a valid choice: " + e);
			}
		}
	}
}

class Student
{
	private final String name;
	private int numTasks = 0;

	public Student(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public int getNumTasks()
	{
		return numTasks;
	}

	public void addNumTasks(int add)
	{
		numTasks += add;
	}

	public String toString()
	{
		return name + " " + numTasks;
	}
}

class Taskoverview
{
	private Student[] students;
	private int studentsCount = 0;

	public Taskoverview(Student[] students)
	{
		this.students = students;
		for(Student s : this.students)
		{
			studentsCount++;
		}
	}

	public Taskoverview()
	{
		this.students = null;
	}

	public int numStudents()
	{
		return studentsCount;
	}

	public int numTaskStudent(int s)
	{
		try
		{
			return this.students[s].getNumTasks();
		}
		catch(Exception e)
		{
			return -1;
		}

	}

	public int numTaskStudent(String stud)
	{
		try 
		{
			for(Student s : this.students)
			{
				if(s.getName().equals(stud))
				{
					return s.getNumTasks();
				}
			}
			throw new NullPointerException("No such student");
		}
		catch(Exception e)
		{
			return -1;
		}
	}

	public void newStudent(String name)
	{
		Boolean exists = false;

		if(!(this.students == null))
		{
			for(Student s : this.students)
			{
				if(s.getName().equals(name))
				{
					exists = true;
				}
			}
		}

		if(!exists)
		{
			studentsCount++;
			Student[] temp = this.students;
			if(temp == null)
			{
				temp = new Student[1];
			}
			this.students = new Student[studentsCount];

			for(int i = 0; i < temp.length; i++)
			{
				this.students[i] = temp[i];
			}

			this.students[studentsCount - 1] = new Student(name);
		}
		else 
		{
			throw new IllegalArgumentException("Student already exists");
		}
	}

	public void approveTask(int stud, int tasks)
	{
		this.students[stud].addNumTasks(tasks);
	}

	public void approveTask(String stud, int tasks)
	{
		for(Student s : this.students)
		{
			if(s.getName().equals(stud))
			{
				s.addNumTasks(tasks);
				break;
			}
		}
	}

	public String toString()
	{
		String temp = "{ ";

		for(Student s : this.students)
		{
			temp += s.toString() + ", "; 
		}

		// for(int i = 0; i < this.students.length; i++)
		// {
		// 	temp += this.students[i].toString() + ", ";
		// }

		temp = temp.substring(0, temp.length() - 2);

		return temp + " }";
	}
}