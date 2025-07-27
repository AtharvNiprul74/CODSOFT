import java.util.Scanner;
public class StudentGrade {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter No of Subjects:");
		int noSubs = sc.nextInt();
		int marks [] = new int[noSubs];
		int totalMarks = 0;
		for(int i=0;i<noSubs;i++)
		{
			System.out.print("Enter "+(i+1)+" subject marks out of 100: ");
			marks[i]=sc.nextInt();
			totalMarks+=marks[i];
		}
		
		System.out.println();
		System.out.println("--------- Student Result -----------");
		
		System.out.println("Total Marks Scored by Student = "+totalMarks);
		
		double averageMarks = (double) totalMarks/noSubs;
		System.out.printf("Average Marks Scored = %.2f ",averageMarks);
		
		String gradeObtained;
		if(averageMarks>=90)
			gradeObtained = "A+";
		else if(averageMarks>=80)
			gradeObtained = "A";
		else if(averageMarks>=70)
			gradeObtained = "B";
		else if(averageMarks>=65)
			gradeObtained = "P";
		else
			gradeObtained = "F";
		
		System.out.println("\nGrade of Student = "+gradeObtained);
	}

}
