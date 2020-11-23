//************************************************************
// ATMProject.java    Author:  Gabriel Limberg, Adam Sarabia, Wyatt Ficek
//
// A somewhat simple ATM simulator that detects user input
//************************************************************
import java.util.Scanner;

class Main {
  
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    boolean isCool = false;
    boolean isValid = false;  // variables for the Main method
    int invalidAttempts = 0;

    ATM test = new ATM(); // creates the ATM object

    clearScreen(); // calls upon the clearScreen() method near the bottom of the main class
    Flag.polishFlag(); // ASCII art in Flag.java
    System.out.println("Welcome to the Mars branch of the Polish National Bank!");
    
    do { // verifies userinput from the scanner if it contains integers only
      System.out.println("Enter Your Pin to Complete a Transaction: ");
      if (scan.hasNextInt()) { // if scanner contains integers, then it goes to here.

        if (test.verifyPin(scan.nextInt()) == true) { // if user inputs correct pin, then this equates to true
          
          while (!isCool){ // while loop encompasses main code, helps determine if user wants to leave or stay in a session

            test.getBalance(); // calls upon the getBalance method in ATM class using the object instantiation test

            if (ATM.input.equalsIgnoreCase("w") || ATM.input.equalsIgnoreCase("Withdraw")) { // detects user input from the ATM class
              try {
                test.withdraw(); // calls on the withdraw method in the ATM class using the object instantiation test
              } catch (InterruptedException e) { // catches the error throw by the withdraw method
                System.out.println("lol no");
              }
            } else if (ATM.input.equalsIgnoreCase("d") || ATM.input.equalsIgnoreCase("Deposit")) { // detects user input from the ATM class
              try {
                test.deposit();
              } catch (InterruptedException e) { // catches the error throw by the deposit method
                System.out.println("lol no");
              }
            } else if (ATM.input.equalsIgnoreCase("e") || ATM.input.equalsIgnoreCase("Exit")) { // detects user input from the ATM class, end program
              System.out.println("Thank you for using the Polish National Bank!");
              Flag.polishFlag();
              isCool = true;
              isValid = true; // loop control variables
            } else if (ATM.input.equalsIgnoreCase("f") || ATM.input.equalsIgnoreCase("FastCash")) { // detects user input from the ATM class
              try {
                test.fastCash();
              } catch (InterruptedException e) { // catches the error throw by the fastCash method
                System.out.println("lol no");
              }
            } else if (ATM.input.equalsIgnoreCase("r")) { // easter egg by Adam
              Flag.robbery();
              test.robbery();
              // loop control variables
            }
          }
        } else { // if user inputs wrong code (only integers) then program states incorrect pin, only 3 attempts
          System.out.println("Incorrect pin.");
          invalidAttempts++;

          switch (invalidAttempts) { // switch statement for invalid attempts
            case 1:
            System.out.println("WARNING: You have 2 attempts left.");
            break;
            case 2:
            System.out.println("WARNING: You have 1 attempt left.");
            System.out.println("Failure to enter the correct pin will result in session termination.");
            break;
            case 3:
            System.out.println("Session terminated.");
            isValid = true; // ends the program
            break;
          }
        }  
      } else { // detects if there is anything other than integers (doubles and string characters)
        System.out.println("Please enter a 4 digit integer only.");
        isValid = false;
        scan.next();
      }
    } while (isValid != true); // end of do while loop, if this equates to true then the program ends
  

    scan.close(); // closes scanner
  }

  public static void clearScreen() { // clear console of any text   
  System.out.print("\033[H\033[2J");  
  System.out.flush();  
  } 
}