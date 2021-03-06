//************************************************************
//  ATM.java    
//
//  ATM Class that contains most the methods to be used in the main class
//************************************************************
import java.util.Scanner;
import java.lang.Thread;

public class ATM {
  private int checkingAccountBalance = 5000;
  private int savingAccountBalance = 5000;
  private static final int pinNumber = 1234;
  private int withdrawalAmount;
  private int depositAmount;
  public static String input;
  final int maxAmount = 500;

  Scanner scan = new Scanner(System.in);

 //verifies pin
  public boolean verifyPin(int pin){

    Main.clearScreen();
    return pin == pinNumber;
    
  }

 //get balance
  public void getBalance(){
    
    System.out.println("Welcome to the Polish National Bank!");
    System.out.println("Here is the current balance in each of your accounts. \n \n Checkings: $" + checkingAccountBalance + "\n Savings: $" + savingAccountBalance + "\n");
    System.out.println("Enter [W] to Withdraw | [D] to Deposit | or [F] for FastCash | [E] to exit");
    input = scan.next();
    
  }


 //withdraw
  public void withdraw() throws InterruptedException {  // throws InterruptedException to pause for certain time (Apart of java.lang.Thread)
    boolean isValid = false;
    boolean isCheckings = false;
    boolean isSavings = false;
    String choice = "";
    int currentBalance = 0;
    

    while (!isValid){ // while loop to detect userinput in account selection
      System.out.println("Please choose an account:\nCheckings [C] || Savings [S]");
      input = scan.next();

      if (input.equalsIgnoreCase("C") || input.equalsIgnoreCase("S") || input.equalsIgnoreCase("Checkings") || input.equalsIgnoreCase("Savings")){
        isValid = true;
      }
      
    } 

    if (input.equalsIgnoreCase("c") || input.equalsIgnoreCase("Checkings"))
    {
      choice = "CHECKINGS";
      isCheckings = true;
      currentBalance = checkingAccountBalance;
    }                                                                               // used to determine what the user picked in terms of what account
    else if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("Savings"))
    {
      choice = "SAVINGS";
      isSavings = true;
      currentBalance = savingAccountBalance;
    }

    System.out.println("How many times would you like to withdraw from your " + choice + " account?\n(In $20 increments)");
    withdrawalAmount = scan.nextInt() * 20; // if user puts 5, it would basically be 5 * 20 since the person is asking for 5 $20 dollar bills

    // need a method to verify the depositAmount is not a double
    // need a method to catch any string inputs

    while (withdrawalAmount > currentBalance) // tells user if there is not enough money in account
    {
      System.out.println("Sorry, you're too broke to take that much out. Try a smaller number.");
      withdrawalAmount = scan.nextInt() * 20;
    }

    while (withdrawalAmount > maxAmount) { // User can only withdrawal 500 per session
      System.out.println("Sorry, you can only withdraw $500 per session.");
      System.out.println("You attempted to withdrawal $" + withdrawalAmount);
      System.out.println("Please enter a new amount :");
      withdrawalAmount = scan.nextInt() * 20;
    } 

    if (withdrawalAmount <= currentBalance && isCheckings){
      checkingAccountBalance -= withdrawalAmount;
    }                                                               // determines which account to remove money from
    else if (withdrawalAmount <= currentBalance && isSavings){
      savingAccountBalance -= withdrawalAmount;
    }

    System.out.println("Please wait while we process this change.");

    for (int i = 0; i < (int) (Math.random() * 10) + 1; i++) {
      Thread.sleep(1000); // 1 second                         // depending on the number generated, the for loop goes to that number and prints out a period every second
      System.out.println(".");
    }
       
    System.out.println("Transaction complete!");
    System.out.println("Updated Checking account balance: $" + checkingAccountBalance);
    System.out.println("Updated Savings account balance: $" + savingAccountBalance); // Updates the account balance
    System.out.println("Enter any key to continue.");

    scan.next(); // awaits userinput to continue and clears screen
    Main.clearScreen();

    
  }

  public void fastCash() throws InterruptedException { // throws InterruptedException to pause for certain time (Apart of java.lang.Thread)

    boolean isValid = false;
    boolean isCheckings = false;
    boolean isSavings = false;
    String choice = "";
    int userInput;

    

    while (!isValid){
      System.out.println("Please choose an account:\nCheckings [C] || Savings [S]");
      input = scan.next();

      if (input.equalsIgnoreCase("C") || input.equalsIgnoreCase("S") || input.equalsIgnoreCase("Checkings") || input.equalsIgnoreCase("Savings")){
        isValid = true;
      }
    } 
  

    if (input.equalsIgnoreCase("c") || input.equalsIgnoreCase("Checkings"))
    {
      choice = "CHECKINGS";
      isCheckings = true;
    }
      else if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("Savings"))
    {
      choice = "SAVINGS";
      isSavings = true;
    }
  
    System.out.println("Please input a number for the amount of cash you would like to withdrawal from your " + choice + " account");
    System.out.println("1 = $20");
    System.out.println("2 = $40");
    System.out.println("3 = $60"); // number represents how much cash is going to be withdrawn
    System.out.println("4 = $80");
    System.out.println("5 = $100");
    userInput = scan.nextInt();

    // need a method to verify the depositAmount is not a double
    // need a method to catch any string inputs

    while (userInput > 5) { // helps detect if a user enters a number higher than 5, tells them no
      System.out.println("Sorry, there are no fast cash amounts greater than \"5\". ");
      userInput = scan.nextInt();
    }

    if (isCheckings) { 
      switch (userInput) {
        case 1:
        checkingAccountBalance -= 20;
        break;
        case 2:
        checkingAccountBalance -= 40;
        break;
        case 3:
        checkingAccountBalance -= 60;
        break;
        case 4:
        checkingAccountBalance -= 80;
        break;
        case 5:
        checkingAccountBalance -= 100;
        break;
      }
    } else if (isSavings) {                           // uses a switch statement to tell the possibilities of 1-5 
      switch (userInput) {
        case 1:
        savingAccountBalance -= 20;
        break;
        case 2:
        savingAccountBalance -= 40;
        break;
        case 3:
        savingAccountBalance -= 60;
        break;
        case 4:
        savingAccountBalance -= 80;
        break;
        case 5:
        savingAccountBalance -= 100;
        break;
      }
    }

    System.out.println("Please wait while we process this change.");

    for (int i = 0; i < (int) (Math.random() * 10) + 1; i++) {
      Thread.sleep(1000);
      System.out.println(".");
    }
       
    System.out.println("Transaction complete!");
    System.out.println("Updated Checking account balance: $" + checkingAccountBalance);
    System.out.println("Updated Savings account balance: $" + savingAccountBalance);
    System.out.println("Enter any key to continue.");

    scan.next();
    Main.clearScreen();


  }



 //deposit
  public void deposit()  throws InterruptedException { // throws InterruptedException to pause for certain time (Apart of java.lang.Thread)
    boolean isValid = false;
    boolean isSavings = false;
    boolean isCheckings = false;
    String choice = "";


    while (!isValid){
      System.out.println("Please choose an account:\nCheckings [C] || Savings [S]");
      input = scan.next();

      if (input.equalsIgnoreCase("C") || input.equalsIgnoreCase("S") || input.equalsIgnoreCase("Checkings") || input.equalsIgnoreCase("Savings")){
        isValid = true;
      }
    } 


    if (input.equalsIgnoreCase("c") || input.equalsIgnoreCase("Checkings"))
    {
      choice = "CHECKINGS";
      isCheckings = true;
    }
    else if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("Savings"))
    {
      choice = "SAVINGS";
      isSavings = true;
    }

    System.out.println("Please enter the amount you wish to deposit into your " + choice + " account.");
    depositAmount = scan.nextInt();

    // need a method to verify the depositAmount is not a double
    // need a method to catch any string inputs

    while (depositAmount > maxAmount) {
      System.out.println("You cannot deposit an amount greater than $500 in one session.");
      System.out.println("Please enter a new deposit amount.");
      depositAmount = scan.nextInt();
    }

    if (isCheckings) {
      checkingAccountBalance += depositAmount;
    } else if (isSavings) {
      savingAccountBalance += depositAmount;
    } else {
      System.out.println("no");
    }

    System.out.println("Please wait while we process this change.");

    for (int i = 0; i < (int) (Math.random() * 10) + 1; i++) {
      Thread.sleep(1000);
      System.out.println(".");
    }
       
    System.out.println("Transaction complete!");
    System.out.println("Updated Checking account balance: $" + checkingAccountBalance);
    System.out.println("Updated Savings account balance: $" + savingAccountBalance);
    System.out.println("Enter any key to continue.");

    scan.next();
    Main.clearScreen();

  

  } 
  
}