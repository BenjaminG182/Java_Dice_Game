/*
   Name:  Benjamin Gunderson
   Class: CIS163AA, Lesson 12 Lab #1
   Date:  11/12/2021
*/

import java.util.Random;
import java.util.*;

public class Die {

   int rolledNum1;
   int rolledNum2;
   private double amtBet;
   private String amount;
   
   private String amountBet;
   private String amountLeft;
  
   
   Die die1 = new Die();
   Die die2 = new Die(); 
   
   // uses the random object to simulate a die roll between 1 and 10
   public void roll() {
   Random randGen = new Random();
         rolledNum1 = randGen.nextInt(10);
         rolledNum2 = randGen.nextInt(10);
   }
   
   // returns boolean true if both die have equal values, false otherwise

   public boolean trueOrFalse() {
       boolean dieNum;
       
       if (rolledNum1 == rolledNum2) {
          dieNum = true; 
          } 
       else {
          dieNum = false;
          }   
       return dieNum;
    }
    
    //Method to check if amount is below zero
    public boolean isNegative() {
       return amount.equals("negative");
    }
 } 

         