
import java.util.Scanner;

class Main {
  
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    boolean isCool = false;
    boolean isValid = false;
    int invalidAttempts = 0;
    ATM test = new ATM();

    clearScreen();
    Flag.polishFlag();
    System.out.println("Welcome to the Mars branch of the Polish National Bank!");
    
    do {
      System.out.println("Enter Your Pin to Complete a Transaction: ");
      if (scan.hasNextInt()) {

        if (test.verifyPin(scan.nextInt()) == true) {

          isValid = true;
          
          while (!isCool){

            test.getBalance();

            if (ATM.input.equalsIgnoreCase("w") || ATM.input.equalsIgnoreCase("Withdraw")) {
              try {
                test.withdraw();
              } catch (InterruptedException e) {
                System.out.println("lol no");
              }
            } else if (ATM.input.equalsIgnoreCase("d") || ATM.input.equalsIgnoreCase("Deposit")) {
              try {
                test.deposit();
              } catch (InterruptedException e) {
                System.out.println("lol no");
              }
            } else if (ATM.input.equalsIgnoreCase("e") || ATM.input.equalsIgnoreCase("Exit")) {
              System.out.println("Thank you for using the Polish National Bank!");
              Flag.polishFlag();
              System.exit(1);
            } else if (ATM.input.equalsIgnoreCase("f") || ATM.input.equalsIgnoreCase("FastCash")) {
              try {
                test.fastCash();
              } catch (InterruptedException e) {
                System.out.println("lol no");
              }
            } else if (ATM.input.equalsIgnoreCase("r")) {
              Flag.robbery();
              isCool = true;
              System.exit(1);
            }
          }
        } else {
          System.out.println("Incorrect pin.");
          invalidAttempts++;

          switch (invalidAttempts) {
            case 1:
            System.out.println("WARNING: You have 2 attempts left.");
            break;
            case 2:
            System.out.println("WARNING: You have 1 attempt left.");
            System.out.println("Failure to enter the correct pin will result in session termination.");
            break;
            case 3:
            System.out.println("Session terminated.");
            System.exit(1);
            break;
          }
        }  
      } else {
        System.out.println("Please enter a 4 digit integer only.");
        isValid = false;
        scan.next();
      }
    } while (isValid != true);
  

    scan.close();
  }

  public static void clearScreen() {    
  System.out.print("\033[H\033[2J");  
  System.out.flush();  
  } 
}