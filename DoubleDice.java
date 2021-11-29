/*
   Name:  Benjamin Gunderson
   Class: CIS163AA, Lesson 14 Final Project
   Date:  11/21/2021
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.text.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.*;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

 public class DoubleDice extends JFrame implements ActionListener {
   private JButton rollButton; // creates roll button
   private JButton quitButton; // creates quit button
   private JLabel amountLabel; // shows how much you have in dollars
   private JLabel questionLabel; // Asks how much to bet next
   private JLabel questionLabel2; // Gives option to exit game
   private JLabel rolledLabel; // Tells you what you rolled
   private JLabel messageLabel; // win or lose + amount
   private JFormattedTextField newBetField; // field to enter in new bet
   double amount = 200; // amount the play begins with
   int rolledNum1 = 1; // First die
   int rolledNum2 = 1; // Second die
   Double amtBet = 0.00; // amount you bet
   String newBet; // message box to grab the new bet

   DecimalFormat df = new DecimalFormat("#,###,##0.00"); // reformats numbers to have two decimals  
          
// Constructor: Sets up the GUI.   
   DoubleDice() {
     
       GridBagConstraints layoutConst = null; // GUI layout
       NumberFormat currencyFormat = null;  // Format for money   
       
       // Set frame's title
       JFrame frame = new JFrame();
       frame.setSize(500, 500);
       setTitle("Double Dice Game - CIS163");

       // Create New Bet Input Field
       currencyFormat = NumberFormat.getCurrencyInstance();
       currencyFormat.setMaximumFractionDigits(0);
       newBetField = new JFormattedTextField(df);
       newBetField.setEditable(true);
       newBetField.setValue(50.0);
      
       // Roll Button  
       rollButton = new JButton("Roll");
       rollButton.addActionListener(this);
       rollButton.setBackground(Color.GREEN);

       // Quit Button
       quitButton = new JButton("Quit");
       quitButton.addActionListener(this);
       quitButton.setBackground(Color.RED);
       quitButton.setForeground(Color.WHITE);
      
       // Creates amount label
       amountLabel = new JLabel("You begin with $" + df.format(amount));
      
       // Creates how to roll text
       questionLabel = new JLabel("Click 'roll' to place your bet if you dare.");

       // Creates how to quit text
       questionLabel2 = new JLabel("Heat too much? Click 'Quit' to quit.");


       // Creates what you rolled label
       rolledLabel = new JLabel("What will you roll?");
      
       // Creates label for win/lose amount
       messageLabel = new JLabel("");
      
   // Layout Design    
     
      // Add components using GridBagLayout
      setLayout(new GridBagLayout());
     
      // Amount Label Layout - how much you have in dollars
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 10, 1);
      layoutConst.anchor = GridBagConstraints.LINE_END;
      layoutConst.gridx = 0;
      layoutConst.gridy = 0;
      add(amountLabel, layoutConst);

      // Question Label Layout - how much to bet next
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 10, 1);
      layoutConst.anchor = GridBagConstraints.LINE_END;
      layoutConst.gridx = 0;
      layoutConst.gridy = 1;
      add(questionLabel, layoutConst);
      
      // Question Label Layout - how to quit
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 10, 1);
      layoutConst.anchor = GridBagConstraints.LINE_END;
      layoutConst.gridx = 0;
      layoutConst.gridy = 2;
      add(questionLabel2, layoutConst);
          
      // Rolled Label Layout - tells you what you rolled
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 10, 1);
      layoutConst.anchor = GridBagConstraints.LINE_END;
      layoutConst.gridx = 0;
      layoutConst.gridy = 3;
      add(rolledLabel, layoutConst);
      
      // Message label layout - win or lost amount
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 10, 1);
      layoutConst.anchor = GridBagConstraints.LINE_END;
      layoutConst.gridx =0;
      layoutConst.gridy = 4;
      add(messageLabel, layoutConst);
      
      // Roll Button Layout
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(0, 10, 10, 5);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 0;
      layoutConst.gridy = 5;
      add(rollButton, layoutConst);
      
      // Quit Button Layout
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(0, 10, 10, 30);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 1;
      layoutConst.gridy = 5;
      add(quitButton, layoutConst);
     
   }
   
   //Beginning - converting numbers to words
    private static final String[] units = {
     "",
     " one",
     " two",
     " three",
     " four",
     " five",
     " six",
     " seven",
     " eight",
     " nine"
     };
  
   private static final String[] doubles = {
     " ten",
   };
  
   private static final String[] tens = {
     "",
   };
  
   private static final String[] hundreds = {
      "",
   };

    private static String convertToWord(int number) {
      String word = "";
      int index = 0;
      do {
      // take 3 digits at a time
      int num = number % 1000;
        if (num != 0){
          String str = convertThreeOrLessThanThreeDigitNum(num);
          word = str + hundreds[index] + word;
        }
      index++;
      // move left by 3 digits
      number = number/1000;
      } while (number > 0);
      return word;
    }
  
    private static String convertThreeOrLessThanThreeDigitNum(int number) {
      String word = "";
      int num = number % 100;
      // Logic to take word from appropriate array
      if(num < 10){
        word = word + units[num];
      }
      else {
        word = word + doubles[num%10];
      }
      word = (number/100 > 0)? units[number/100] + " hundred" + word : word;
      return word;
    }
   // End -converting numbers to words


      @Override
      public void actionPerformed(ActionEvent event) {    // Updates the counter and label when the button is pushed.
      
         Random randGen = new Random(); // Begins random number generation
         rolledNum1 = randGen.nextInt(10); // rolledNum1 is assigned a random number up to 10
         rolledNum2 = randGen.nextInt(10); // rolledNum2 is assigned a random number up to 10
         boolean dieNum;
         
         // Stops rolledNum1 or 2 from being value 0
         if (rolledNum1 == 0){
         rolledNum1 = 1;
         }
         if (rolledNum2 == 0){
         rolledNum2 = 1;
         }
         
         //Get source of event (2 buttons in GUI)
         JButton sourceEvent = (JButton) event.getSource();
                    
      //rollButton action
      if (sourceEvent == rollButton){
        
          newBet = JOptionPane.showInputDialog("How much do you want to bet? "); // Pop up message asking how much to bet
          amtBet = Double.parseDouble(newBet); // Assigns input in message box to amtBet variable
          
          if (amtBet < 1) {
          JOptionPane.showMessageDialog(this, "Are you done messing around? Let's bet real money.");
          newBet = JOptionPane.showInputDialog("How much do you want to bet? ");
          amtBet = Double.parseDouble(newBet);
          }
          else {
          amtBet = Double.parseDouble(newBet);
          }       
          
          // boolean true if both die match
             if (rolledNum1 == rolledNum2) {
             dieNum = true;
             }
             else {
             dieNum = false;
             }    
           
         if (amtBet > amount) {
                JOptionPane.showMessageDialog(this, "You cannot bet more than you have.");
          }
          
         else {
             // If die matches, multiply amtBet times 5, display won text
             if (dieNum == true) {
             amtBet = amtBet * 5;
             messageLabel.setText("You won $" + df.format(amtBet) + ". It's your lucky day.");
             amount = amount + amtBet;
             }
             
             else {
             messageLabel.setText("You lost $" + df.format(amtBet) + ". Dare to try again?"); // display lost text and subtract money from amount        
             amount = amount - amtBet;
                // Close if amount is less than zero
                if (amount < 0.0001) {
                JOptionPane.showMessageDialog(this, "You've ran out of money!");
                dispose();
                }
             }
             rolledLabel.setText("You rolled a" + convertToWord(rolledNum1) + " and a" + convertToWord(rolledNum2) + ".");
             
             // Displays remaining amount
             amountLabel.setText("You have $" + df.format(amount));      
          }
        }
        
       
       //Quit button action
       if (event.getSource() == quitButton){
          dispose();
         }   
     }
    
    /* Creates a Frame and makes it visible */
    public static void main(String[] args) {
     
      // Creates Frame and its components
      DoubleDice myFrame = new DoubleDice();

      // Sets default close method - exit.
      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myFrame.setPreferredSize(new Dimension(400,250));
      myFrame.pack();
      myFrame.setVisible(true);
   }   
 }