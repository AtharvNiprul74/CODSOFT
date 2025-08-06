import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NumberGuessingGame extends JFrame{

	private int orignalNumber;
	private int attemptsRemaining;
	private int maxAttempts;
	private 	int score = 0;
	private int rounds = 0;
	private boolean isRoundStart = false;
	
	private JTextField guessedNumber;
	private JLabel instructionLabel,feedbackLabel,attemptsLabel,scoreLabel;
	private JToggleButton toggleTheme;
	private JButton guessButton,playAgainBtn,easyModeBtn,mediumModeBtn,hardModeBtn;
	private Font font = new Font("Segoe UI",Font.PLAIN,16);
	
	public NumberGuessingGame()
	{
		this.setTitle("Number Guessing Game");
		this.setSize(400,350);
		this.setLayout(new BorderLayout(10,10));
		this.getRootPane().setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		((JComponent) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	
		JPanel topBar = new JPanel();
		topBar.setLayout(new BorderLayout());
		this.add(topBar,BorderLayout.NORTH);
		
		attemptsLabel = new JLabel("Attempts Left: - ", SwingConstants.CENTER);
		attemptsLabel.setFont(font);
		topBar.add(attemptsLabel,BorderLayout.WEST);
		
		scoreLabel = new JLabel("Score : 0 | Rounds : 0 ",SwingConstants.CENTER);
		scoreLabel.setFont(font);
		topBar.add(scoreLabel,BorderLayout.CENTER);
		
		toggleTheme = new JToggleButton("Dark");
		toggleTheme.addActionListener(e -> {
			try {
				if(toggleTheme.isSelected())
				{
					toggleTheme.setText("Light");
					UIManager.setLookAndFeel(new FlatDarkLaf());
				}
				else
				{
					toggleTheme.setText("Dark");
					UIManager.setLookAndFeel(new FlatLightLaf());
				}
				
				SwingUtilities.updateComponentTreeUI(this);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});
		
		topBar.add(toggleTheme,BorderLayout.EAST);
		
		JPanel centerMainPart = new JPanel();
		centerMainPart.setLayout(new GridLayout(0,1,10,10));
		this.add(centerMainPart,BorderLayout.CENTER);
		
		instructionLabel = new JLabel("Guess a number between 1 to 100",SwingConstants.CENTER);
		instructionLabel.setFont(font);
		centerMainPart.add(instructionLabel);
		
		guessedNumber = new JTextField();
		guessedNumber.setFont(font);
		centerMainPart.add(guessedNumber);
		
		guessButton = new JButton("Submit Number");
		guessButton.setFont(font);
		guessButton.setEnabled(false);
		centerMainPart.add(guessButton);
		
		feedbackLabel = new JLabel("Select Difficulty Level :)",SwingConstants.CENTER);
		feedbackLabel.setFont(font);
		centerMainPart.add(feedbackLabel);
		
		JPanel difficultyView = new JPanel();
		difficultyView.setLayout(new GridLayout(1,3,10,10));
		
		easyModeBtn = new JButton("Easy");
		easyModeBtn.setFont(font);
		difficultyView.add(easyModeBtn);
		
		mediumModeBtn = new JButton("Medium");
		mediumModeBtn.setFont(font);
		difficultyView.add(mediumModeBtn);
		
		hardModeBtn = new JButton("Hard");
		hardModeBtn.setFont(font);
		difficultyView.add(hardModeBtn);
		
		centerMainPart.add(difficultyView);
		
		playAgainBtn = new JButton("Play Again");
		playAgainBtn.setFont(font);
		playAgainBtn.setVisible(false);
		centerMainPart.add(playAgainBtn);
		
		guessButton.addActionListener(e -> {
			acceptGuess();
		});
		
		playAgainBtn.addActionListener(e -> {
			resetGame();
		});
		
		easyModeBtn.addActionListener(e -> {
			setDifficulty("easy");
		});
		
		mediumModeBtn.addActionListener(e -> {
			setDifficulty("medium");
		});
		
		hardModeBtn.addActionListener(e -> {
			setDifficulty("hard");
		});
		
		this.setVisible(true);
	}
	
	private void startNewRound()
	{
		orignalNumber = new Random().nextInt(100) + 1;
		rounds ++;
		guessedNumber.setText("");
		feedbackLabel.setText("Enter Your Guess .... :) ");
		attemptsLabel.setText("Attempts Left: "+ attemptsRemaining);
		scoreLabel.setText("Score: "+ score +" | Round: "+rounds);
		guessButton.setEnabled(true);
		playAgainBtn.setVisible(false);
	}
	
	private void acceptGuess()
	{
		String guessedText = guessedNumber.getText();
		int guessedValue;
		try{
			guessedValue = Integer.parseInt(guessedText);
		}
		catch(NumberFormatException e)
		{
			feedbackLabel.setText("Enter Valid Number !!");
			return;
		}
		
		attemptsRemaining --;
		
		if(guessedValue < 1 || guessedValue > 100)
			JOptionPane.showMessageDialog(this, "Attempt Waste :) Enter guess in range of 1 to 100 !!");
		if(guessedValue == orignalNumber)
		{
			feedbackLabel.setText("Correct !! You win this round :)");
			score ++;
			endRound();
		}
		
		else if(guessedValue < orignalNumber)
		{
			feedbackLabel.setText("Too Low Guess !");
		}
		else
		{
			feedbackLabel.setText("Too High Guess !");
		}
		
		attemptsLabel.setText("Attempts Left: " + attemptsRemaining);
		
		if(attemptsRemaining <= 0 && guessedValue != orignalNumber)
		{
			feedbackLabel.setText("Out of attempts :(  Number was: " + orignalNumber);
			endRound();	
		}
		
		easyModeBtn.setEnabled(false);
		mediumModeBtn.setEnabled(false);
		hardModeBtn.setEnabled(false);
	}
	
	private void setDifficulty(String diff)
	{
		if(isRoundStart) return;
		isRoundStart = true;
		switch(diff)
		{
			case "easy":
				maxAttempts = 12;
			break;
				
			case "medium":
				maxAttempts = 7;
			break;
			
			case "hard":
				maxAttempts = 5;
			break;
		}
		
		attemptsRemaining = maxAttempts;
		startNewRound();

		easyModeBtn.setEnabled(false);
		mediumModeBtn.setEnabled(false);
		hardModeBtn.setEnabled(false);
		guessButton.setEnabled(true);
		feedbackLabel.setText("Enter Your Guess ..... ;)");
		attemptsLabel.setText("Attempts Left: "+ maxAttempts);
	}
	
	private void endRound()
	{
		guessButton.setEnabled(false);
		playAgainBtn.setVisible(true);
		isRoundStart = false;
		scoreLabel.setText("Score: "+ score + " | Round: "+ rounds);
	}
	
	private void resetGame()
	{
		isRoundStart = false;
		guessedNumber.setText("");
		feedbackLabel.setText("Select Difficulty Level :) ");
		attemptsLabel.setText("Attempts Left: - ");
		easyModeBtn.setEnabled(true);	
		mediumModeBtn.setEnabled(true);
		hardModeBtn.setEnabled(true);
		guessButton.setEnabled(false);
		playAgainBtn.setVisible(false);
	}
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			
		}
		
		NumberGuessingGame play = new NumberGuessingGame();
	}

}
