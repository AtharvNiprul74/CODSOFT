package Entity;

public class Student {
	private String name;
	private 	int rollNo;
	private String gradeObtained;
	private String emailAddress;
		
	public Student() 
	{

	}

	public Student(String name, int rollNo, String gradeObtained, String emailAddress) 
	{
		this.name = name;
		this.rollNo = rollNo;
		this.gradeObtained = gradeObtained;
		this.emailAddress = emailAddress;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getRollNo() 
	{
		return rollNo;
	}

	public void setRollNo(int rollNo) 
	{
		this.rollNo = rollNo;
	}

	public String getGradeObtained() 
	{
		return gradeObtained;
	}

	public void setGradeObtained(String gradeObtained) 
	{
		this.gradeObtained = gradeObtained;
	}

	public String getEmailAddress() 
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) 
	{
		this.emailAddress = emailAddress;
	}

}
