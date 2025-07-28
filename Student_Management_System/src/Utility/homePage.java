package Utility;
import main.StudentManagementSystem;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;

import Entity.Student;

public class homePage extends JFrame{
	
	JPanel leftPanel,mainPanel;
	JButton btnAdd,btnEdit,btnSearch,btnView,btnDelete,btnExit;
	JPanel addStudent,editStudent,searchStudent,viewAllStudent,deleteStudent;
	Font font = new Font("Segoe UI",Font.BOLD,14);
	Font lableFont = new Font("Segoe UI",Font.PLAIN,13);
	
	public static void showMessage(JFrame root,String message)
	{
		JOptionPane.showMessageDialog(root, message);
	}
	
	public static int showConfirmBox(JFrame root,String message)
	{
		int choice = JOptionPane.showConfirmDialog(root, message,"Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		return choice;
	}
	
	public homePage()
	{
		this.setSize(700,400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Student Management System");		
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setBackground(Color.WHITE);
		
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel greet = new JLabel("Welcome to Student Management System ",JLabel.CENTER);
		greet.setFont(new Font("SansSarif",Font.BOLD,20));
		greet.setForeground(new Color(52,73,94));
		this.add(greet,BorderLayout.NORTH);
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
		leftPanel.setPreferredSize(new Dimension(200,600));
		leftPanel.add(Box.createRigidArea(new Dimension(0,30)));
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));

		btnAdd = new JButton("Add Student");
		btnEdit = new JButton("Edit Student");
		btnSearch = new JButton("Search Student");
		btnView = new JButton("View All Students");
		btnDelete = new JButton("Delete Student");
		btnExit = new JButton("Exit");
		
		JButton Buttons[] = {btnAdd,btnEdit,btnSearch,btnView,btnDelete,btnExit}; 
		for(JButton btn : Buttons)
		{
			btn.setAlignmentX(CENTER_ALIGNMENT);
			btn.setMaximumSize(new Dimension(180,40));
			btn.setForeground(Color.WHITE);
			btn.setBackground(new Color(44,62,80));
			btn.setFont(new Font("SansSerif",Font.BOLD,15));
			
			btn.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(MouseEvent e)
				{
					btn.setBackground(new Color(41,128,185));
				}
				
				public void mouseExited(MouseEvent e)
				{
					btn.setBackground(new Color(44,62,80));
				}
			});
			
			leftPanel.add(btn);
			leftPanel.add(Box.createRigidArea(new Dimension(0,15)));
		}
		
		
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(600,500));
		mainPanel.setLayout(new CardLayout());
		CardLayout card =  (CardLayout) mainPanel.getLayout(); 
		
		// Add Student Card
		
		addStudent = new JPanel();
		addStudent.add(new JLabel("Add Student View"));
		addStudent.setLayout(null);
		addStudent.setBackground(Color.WHITE);
		
		JLabel addname = new JLabel("Name");
		addname.setBounds(50,35,100,25);
		addname.setFont(lableFont);
		
		JTextField addnameField = new JTextField();
		addnameField.setBounds(160,30,150,25);
		addnameField.setFont(lableFont);
		
		JLabel addroll = new JLabel("Roll No");
		addroll.setBounds(50,75,100,25);
		addroll.setFont(lableFont);
		
		JTextField addrollField = new JTextField();
		addrollField.setBounds(160,70,150,25);
		addrollField.setFont(lableFont);
		
		JLabel addgrade = new JLabel("Grade Obtained");
		addgrade.setBounds(50,120,100,25);
		addgrade.setFont(lableFont);
		
		JTextField addgradeField = new JTextField();
		addgradeField.setBounds(160,120,150,25);
		addgradeField.setFont(lableFont);
		
	    JLabel addemail = new JLabel("Student Email");
		addemail.setBounds(50,170,100,25);
		addemail.setFont(lableFont);
		
		JTextField addemailField = new JTextField();
		addemailField.setBounds(160,170,150,25);
		addemailField.setFont(lableFont);
		
		JButton saveStudent = new JButton("Save Student");
		saveStudent.setBounds(160, 220, 150, 30);
		saveStudent.setFont(font);
		
		addStudent.add(addname);
		addStudent.add(addnameField);
		addStudent.add(addroll);
		addStudent.add(addrollField);
		addStudent.add(addgrade);
		addStudent.add(addgradeField);
		addStudent.add(addemail);
		addStudent.add(addemailField);
		addStudent.add(saveStudent);
		
		saveStudent.addActionListener(e -> {
			StudentManagementSystem.addStudent(addnameField.getText(),addrollField.getText(),addgradeField.getText(),addemailField.getText(),this);
			addnameField.setText("");
			addrollField.setText("");
			addgradeField.setText("");
			addemailField.setText("");
		});
		
		// Edit Student Card
		
		editStudent = new JPanel();
		editStudent.add(new JLabel("Edit Student View"));
		editStudent.setLayout(null);
		editStudent.setBackground(Color.WHITE);
		

		JLabel editname = new JLabel("Name Of Student");
		editname.setBounds(50,35,100,25);
		editname.setFont(lableFont);
		
		JTextField editnameField = new JTextField();
		editnameField.setBounds(180,30,150,25);
		editnameField.setFont(lableFont);
		editnameField.setEditable(false);
		
		JLabel editroll = new JLabel("Roll No Of Student");
		editroll.setBounds(50,75,120,25);
		editroll.setFont(lableFont);
		
		JTextField editrollField = new JTextField();
		editrollField.setBounds(180,70,150,25);
		editrollField.setFont(lableFont);
		
		JLabel editgrade = new JLabel("Grade Obtained");
		editgrade.setBounds(50,120,120,25);
		editgrade.setFont(lableFont);
		
		JTextField editgradeField = new JTextField();
		editgradeField.setBounds(180,120,150,25);
		editgradeField.setFont(lableFont);
		editgradeField.setEditable(false);
		
		JLabel editemail = new JLabel("Student Email ID");
		editemail.setBounds(50,170,100,25);
		editemail.setFont(lableFont);
		
		JTextField editemailField = new JTextField();
		editemailField.setBounds(180,170,150,25);
		editemailField.setFont(lableFont);
		editemailField.setEditable(false);
		
		JTextField oldRollNo = new JTextField();
		oldRollNo.setVisible(false);
		
		JButton searchEdit = new JButton("Search Student");
		searchEdit.setBounds(180, 220, 150, 30);
		searchEdit.setFont(font);
		
		JButton edit = new JButton("Edit Student");
		edit.setBounds(180, 260, 150, 30);
		edit.setFont(font);
		edit.setEnabled(false);
		
		searchEdit.addActionListener(e -> 
		{
			Student stud = StudentManagementSystem.searchStudent(editrollField.getText());
			if(stud == null)
			{
				showMessage(this, "Student with "+editrollField.getText()+" Roll No not found ..... !!");
				return;
			}
			editnameField.setText(stud.getName());
			editrollField.setText(String.valueOf(stud.getRollNo()));
			oldRollNo.setText(String.valueOf(stud.getRollNo()));
			editgradeField.setText(stud.getGradeObtained());
			editemailField.setText(stud.getEmailAddress());
			editnameField.setEditable(true);
			editrollField.setEditable(true);
			editgradeField.setEditable(true);
			editemailField.setEditable(true);
			edit.setEnabled(true);
			searchEdit.setEnabled(false);
		});			
		
		edit.addActionListener(e -> {
			StudentManagementSystem.editStudent(editnameField.getText(),editrollField.getText(),oldRollNo.getText(),editgradeField.getText(),editemailField.getText(),this);
			editnameField.setText("");
			editrollField.setText("");
			oldRollNo.setText("");
			editgradeField.setText("");
			editemailField.setText("");
			editnameField.setEditable(false);
			editgradeField.setEditable(false);
			editemailField.setEditable(false);
			searchEdit.setEnabled(true);
			edit.setEnabled(false);
		});
		
		editStudent.add(editname);
		editStudent.add(editnameField);
		editStudent.add(editroll);
		editStudent.add(editrollField);
		editStudent.add(editgrade);
		editStudent.add(editgradeField);
		editStudent.add(editemail);
		editStudent.add(editemailField);
		editStudent.add(searchEdit);
		editStudent.add(edit);
			
		//Search Student card
		
		searchStudent = new JPanel();
		searchStudent.add(new JLabel("Search Student"));
		searchStudent.setLayout(null);
		searchStudent.setBackground(Color.WHITE);
		

		JLabel searchname = new JLabel("Name Of Student");
		searchname.setBounds(50,35,100,25);
		searchname.setFont(lableFont);
		
		JTextField searchnameField = new JTextField();
		searchnameField.setBounds(180,30,150,25);
		searchnameField.setFont(lableFont);
		searchnameField.setEditable(false);
		
		JLabel searchroll = new JLabel("Roll No Of Student");
		searchroll.setBounds(50,75,120,25);
		searchroll.setFont(lableFont);
		
		JTextField searchrollField = new JTextField();
		searchrollField.setFont(lableFont);
		searchrollField.setBounds(180,70,150,25);
		
		JLabel searchgrade = new JLabel("Grade Obtained");
		searchgrade.setBounds(50,120,120,25);
		searchgrade.setFont(lableFont);
		
		JTextField searchgradeField = new JTextField();
		searchgradeField.setBounds(180,120,150,25);
		searchgradeField.setFont(lableFont);
		searchgradeField.setEditable(false);
		
		JLabel searchemail = new JLabel("Student Email ID");
		searchemail.setBounds(50,170,100,25);
		searchemail.setFont(lableFont);
		
		JTextField searchemailField = new JTextField();
		searchemailField.setBounds(180,170,150,25);
		searchemailField.setFont(lableFont);
		searchemailField.setEditable(false);
		
		JButton search = new JButton("Search Student");
		search.setBounds(180, 220, 150, 30);
		search.setFont(font);
		search.addActionListener(e -> {
			Student stud = StudentManagementSystem.searchStudent(searchrollField.getText());
			if(stud == null)
			{
				showMessage(this, "Student with "+searchrollField.getText()+" Roll No not found ..... !!");
				return;
			}
			searchnameField.setText(stud.getName());
			searchrollField.setText(String.valueOf(stud.getRollNo()));
			searchgradeField.setText(stud.getGradeObtained());
			searchemailField.setText(stud.getEmailAddress());
		});
		
		searchStudent.add(searchname);
		searchStudent.add(searchnameField);
		searchStudent.add(searchroll);
		searchStudent.add(searchrollField);
		searchStudent.add(searchgrade);
		searchStudent.add(searchgradeField);
		searchStudent.add(searchemail);
		searchStudent.add(searchemailField);
		searchStudent.add(search);
		
		
		// View All Student card
		
		viewAllStudent = new JPanel();
		viewAllStudent.setLayout(new BorderLayout(10,10));
		viewAllStudent.setBackground(Color.WHITE);
		JLabel label = new JLabel("Student's List",SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI",Font.PLAIN,20));
		label.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		label.setBounds(50,20,120,25);
		viewAllStudent.add(label,BorderLayout.NORTH);
		
		String[] columns = {"Name","Roll","Grade","Email"};
		
		DefaultTableModel model = new DefaultTableModel(columns,0) {
			public boolean isCellEditable(int row,int col)
			{
				return false;
			}
		};
		
		JTable table = new JTable(model);
		table.setRowHeight(25);
		table.setFont(font);
		table.getTableHeader().setFont(font);
		table.setShowGrid(true);
		
		JScrollPane pane = new JScrollPane(table);
		pane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		pane.setPreferredSize(new Dimension(580,350));
		
		viewAllStudent.add(pane,BorderLayout.CENTER);
		btnView.addActionListener(e -> {
			Object[][] data = StudentManagementSystem.getData(this);
			model.setRowCount(0);
			for(Object[] student : data)
			{
				model.addRow(student);
			}
		});
		
	
		// Delete Student card
		
		deleteStudent = new JPanel();
		deleteStudent.setLayout(null);
		deleteStudent.setBackground(Color.WHITE);
		
		JLabel deletename = new JLabel("Name");
		deletename.setBounds(50,35,100,25);
		deletename.setFont(lableFont);
		
		JTextField deletenameField = new JTextField();
		deletenameField.setBounds(160,30,150,25);
		deletenameField.setFont(lableFont);
		deletenameField.setEditable(false);
		
		JLabel deleteroll = new JLabel("Roll No");
		deleteroll.setBounds(50,75,100,25);
		deleteroll.setFont(lableFont);
		
		JTextField deleterollField = new JTextField();
		deleterollField.setBounds(160,70,150,25);
		deleterollField.setFont(lableFont);
		
		JLabel deletegrade = new JLabel("Grade Obtained");
		deletegrade.setBounds(50,120,100,25);
		deletegrade.setFont(lableFont);
		
		JTextField deletegradeField = new JTextField();
		deletegradeField.setBounds(160,120,150,25);
		deletegradeField.setFont(lableFont);
		deletegradeField.setEditable(false);
		
	    JLabel deleteemail = new JLabel("Student Email");
		deleteemail.setBounds(50,170,100,25);
		deleteemail.setFont(lableFont);
		
		JTextField deleteemailField = new JTextField();
		deleteemailField.setBounds(160,170,150,25);
		deleteemailField.setFont(lableFont);
		deleteemailField.setEditable(false);
		
		JButton searchData = new JButton("Search Student");
		searchData.setBounds(160,220,150,30);
		searchData.setFont(font);
		
		JButton deleteStudentData = new JButton("Delete Student");
		deleteStudentData.setBounds(160, 260, 150, 30);
		deleteStudentData.setEnabled(false);
		deleteStudentData.setFont(font);
		
		searchData.addActionListener(e -> {
			Student stud = StudentManagementSystem.searchStudent(deleterollField.getText());
			if(stud == null)
			{
				showMessage(this, "Student with "+deleterollField.getText()+" Roll No not found ..... !!");
				return;
			}
			deletenameField.setText(stud.getName());
			deleterollField.setText(String.valueOf(stud.getRollNo()));
			deletegradeField.setText(stud.getGradeObtained());
			deleteemailField.setText(stud.getEmailAddress());
			deleteStudentData.setEnabled(true);
			searchData.setEnabled(false);
		});
	
		deleteStudentData.addActionListener(e -> {
			StudentManagementSystem.deleteStudent(deleterollField.getText(),this);
			deletenameField.setText("");
			deleterollField.setText("");
			deletegradeField.setText("");
			deleteemailField.setText("");
			searchData.setEnabled(true);
			deleteStudentData.setEnabled(false);
		});
		
		deleteStudent.add(deletename);
		deleteStudent.add(deletenameField);
		deleteStudent.add(deleteroll);
		deleteStudent.add(deleterollField);
		deleteStudent.add(deletegrade);
		deleteStudent.add(deletegradeField);
		deleteStudent.add(deleteemail);
		deleteStudent.add(deleteemailField);
		deleteStudent.add(searchData);
		deleteStudent.add(deleteStudentData);
		
		// main card panel
		mainPanel.add(addStudent,"Add");
		mainPanel.add(editStudent,"Edit");
		mainPanel.add(searchStudent,"Search");
		mainPanel.add(viewAllStudent,"View");
		mainPanel.add(deleteStudent,"Delete");
		
		btnAdd.addActionListener(e -> {
				card.show(mainPanel,"Add");
				addnameField.setText("");
				addrollField.setText("");
				addgradeField.setText("");
				addemailField.setText("");
			});
		
		btnEdit.addActionListener(e -> {
				card.show(mainPanel, "Edit");
				editnameField.setText("");
				editrollField.setText("");
				oldRollNo.setText("");
				editgradeField.setText("");
				editemailField.setText("");
			});
		
		btnSearch.addActionListener(e -> {
				card.show(mainPanel,"Search");
				searchrollField.setText("");
			});
		
		btnView.addActionListener(e -> card.show(mainPanel,"View"));
		
		btnDelete.addActionListener(e -> {
				card.show(mainPanel, "Delete");
				deletenameField.setText("");
				deleterollField.setText("");
				deletegradeField.setText("");
				deleteemailField.setText("");
			});
		
		btnExit.addActionListener(e -> {
			int choice = showConfirmBox(this, "Do You Sure Exit ?");
			if(choice == JOptionPane.YES_OPTION)
				System.exit(0);
		});
		
		this.add(leftPanel,BorderLayout.WEST);
		this.add(mainPanel,BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
}
