package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Entity.Student;
import Utility.DbUtil;
import Utility.homePage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem {
	
	public static int validateData(String name,String roll,String grade,String email,JFrame window)
	{
		if(name.isEmpty() || name.trim().isEmpty())
		{
			homePage.showMessage(window, "Name cannot be empty !!");
			return -1;
		}
		
		if(roll == null || !roll.matches("\\d+"))
		{
			homePage.showConfirmBox(window, "Roll no must be valid number !!");
			return -1;
		}
		
		if(grade == null || grade.trim().isEmpty())
		{
			homePage.showConfirmBox(window, "Grade cannot be empty !!");
			return -1;
		}
		
		if(!grade.matches("[A-Fa-f]{1}"))
		{
			homePage.showConfirmBox(window, "Grade must be between A to F !!");
			return -1;
		}
			
		if(email == null || email.isEmpty())
		{
			homePage.showConfirmBox(window, "Email cannot be empty !!");
			return -1;
		}
		
		int rollNo = 0;
		try {
			rollNo = Integer.parseInt(roll);
		}
		catch(NumberFormatException e){
			homePage.showMessage(window, "Enter Valid Number !!");
			return -1;
		}
		return rollNo;
	}
	
	public static void addStudent(String name,String rollNo,String grade,String email,JFrame window)
	{
		String studName = name;
		String studRoll = rollNo;
		String studGrade = grade;
		String studEmail = email;
		
		int validNo = validateData(studName,studRoll,studGrade,studEmail,window);
		if(validNo == -1)
			return;
		
		try(Connection conn = DbUtil.getConnection())
		{
			String sql = " CREATE TABLE IF NOT EXISTS student (name char(50),roll int PRIMARY KEY,grade char(1),email char(50) UNIQUE);";
			try(PreparedStatement pstmt = conn.prepareStatement(sql))
			{
				pstmt.execute();
			}
			
			sql = "Select roll from student where roll = ?";
			try(PreparedStatement pstmt = conn.prepareStatement(sql))
			{
				pstmt.setInt(1, validNo);
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					homePage.showMessage(window, "Student with "+validNo+" already exists ....");
					return;
				}
			}
			
			sql = "INSERT into student values (?,?,?,?);";
			try(PreparedStatement pstmt = conn.prepareStatement(sql))
			{
				pstmt.setString(1,studName);
				pstmt.setInt(2,validNo);
				pstmt.setString(3,studGrade);
				pstmt.setString(4,studEmail);
				
				pstmt.executeUpdate();
				
				homePage.showMessage(window, studName+" Added Successfully .....");
			}
		}
		catch (Exception e) 
		{
			homePage.showMessage(window, e.getMessage());
		}
	}
	
	public static Student searchStudent(String rollNo)
	{
		int roll = Integer.parseInt(rollNo);
		try(Connection conn = DbUtil.getConnection())
		{
			String sql = "Select * from student where roll = ?";
			try(PreparedStatement pstmt = conn.prepareStatement(sql))
			{
				pstmt.setInt(1, roll);
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					Student stud = new Student();
					stud.setName(rs.getString(1));
					stud.setRollNo(rs.getInt(2));
					stud.setGradeObtained(rs.getString(3));
					stud.setEmailAddress(rs.getString(4));
					return stud;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void editStudent(String name,String rollNo,String oldRollNo,String grade,String email,JFrame window)
	{
		String studName = name;
		String studRoll = rollNo;
		String studoldRoll = oldRollNo;
		String studGrade = grade;
		String studEmail = email;
		
		int validNo = validateData(studName,studRoll,studGrade,studEmail,window);
		int oldNo = Integer.parseInt(studoldRoll);
		
		try(Connection conn = DbUtil.getConnection())
		{
			String sql = " Update student set name = ? , roll = ? , grade = ? , email = ? where roll = ? ";
			try(PreparedStatement pstmt = conn.prepareStatement(sql))
			{
				pstmt.setString(1,studName);
				pstmt.setInt(2,validNo);
				pstmt.setString(3,studGrade);
				pstmt.setString(4,studEmail);
				pstmt.setInt(5, oldNo);
				pstmt.executeUpdate();
				
				homePage.showMessage(window, studName+" Edited Successfully .....");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Object[][] getData(JFrame window)
	{
		List<Object[]> data = new ArrayList<Object[]>();
		
		try(Connection conn = DbUtil.getConnection())
		{
			String sql = "Select * From student";
			try(PreparedStatement pstmt = conn.prepareStatement(sql))
			{
				ResultSet rs = pstmt.executeQuery();
				while(rs.next())
				{
					Object[] studentData = new Object[4];
					studentData[0] = rs.getString(1);
					studentData[1] = rs.getInt(2);
					studentData[2] = rs.getString(3);
					studentData[3] = rs.getString(4);
					data.add(studentData);
				}
			}
		}
		catch(Exception e)
		{
			homePage.showMessage(window, e.getMessage());
		}
		
		Object[][] studentsDataSet = new Object[data.size()][4];
		for(int i=0;i<data.size();i++)
		{
			studentsDataSet[i]=data.get(i);
		}
		
		return studentsDataSet;
		
	}
	
	public static void deleteStudent(String rollNo,JFrame window)
	{
		int validNo = Integer.parseInt(rollNo);
		try(Connection conn = DbUtil.getConnection())
		{
			String sql = "Delete From student where roll = ?";
			try(PreparedStatement pstmt = conn.prepareStatement(sql))
			{
				pstmt.setInt(1, validNo);
				if(homePage.showConfirmBox(window, "Are you sure to Delete Student.") == JOptionPane.OK_OPTION)
				{
					pstmt.executeUpdate();
					homePage.showMessage(window, "Student Deleted Successfully .... !");
					return;
				}
				else
					return;
			}
		}
		catch(Exception e)
		{
			homePage.showMessage(window, e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(()->{
			new homePage();
		});
	}

}
