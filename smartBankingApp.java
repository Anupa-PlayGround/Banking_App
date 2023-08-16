import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

public class smartBankingApp{
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to Smart Banking System";
        final String OPEN_ACCOUNT = "Open New Account";
        final String DEPOSIT_MONEY = "Deposit Money";
        final String WITHDRAW_MONEY = "Withdraw Money";  
        final String TRANSFER_MONEY = "Transfer Money";
        final String CHECK_ACCOUNT_BALANCE = "Check Accout Balance"; 
        final String DELETE_ACCOUNT = "Drop Existing Account";   

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String screen = DASHBOARD;
        String [] accountNumbers = new String [0];
        String [] accountNames =new String [0];
        double [] accountBalances = new double [0];
        
        do{
            final String APP_TITLE = String.format("%s%s%s",COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

            switch(screen){
//==========================================================================================================================================
                case DASHBOARD:
//==========================================================================================================================================               
                    System.out.println("\t[1]. Open New Account: ");
                    System.out.println("\t[2]. Deposit Money: ");  
                    System.out.println("\t[3]. Withdraw Money: ");   
                    System.out.println("\t[4]. Transfer Money: ");  
                    System.out.println("\t[5]. Check Account Balance: ");
                    System.out.println("\t[6]. Drop Existing Account: "); 
                    System.out.println("\t[7]. Exit: \n");     
                    System.out.print("Enter the Option Number to Continue: ");
                    int option = scanner.nextInt();
                    scanner.nextLine();
//------------------------------------------------------------------------------------------------------------------------------------------ 
                    switch(option){
                        case 1: screen = OPEN_ACCOUNT; break;
                        case 2: screen = DEPOSIT_MONEY; break;
                        case 3: screen = WITHDRAW_MONEY; break;
                        case 4: screen = TRANSFER_MONEY; break;
                        case 5: screen = CHECK_ACCOUNT_BALANCE; break;
                        case 6: screen = DELETE_ACCOUNT; break;
                        case 7: System.out.println(CLEAR); System.exit(0); break;
                        default: continue;
                    }  
                    break;

                case OPEN_ACCOUNT:

                boolean valid = true;
                do{
                    String accountNumber = String.format("SDB-%05d", (accountNumbers.length + 1));
                    System.out.printf("\t%s \n",accountNumber);
                    String accountName;
                        // Name Validation
                    do{
                            valid = true;
                            System.out.print("\tEnter the Account Name: ");
                            accountName = scanner.nextLine().strip();
                            if (accountName.isBlank()){
                                System.out.printf(ERROR_MSG, "Customer name can't be empty");
                                valid = false;
                                continue;
                            }
                            for (int i = 0; i < accountName.length(); i++) {
                                if (!(Character.isLetter(accountName.charAt(i)) || Character.isSpaceChar(accountName.charAt(i))) ) {
                                    System.out.printf(ERROR_MSG, "Invalid name");
                                    valid = false;
                                    break;
                                }
                            }
                    }while(!valid);

                    double initialDeposit =0;
                    
                    do{
                        valid = true;
                        System.out.print("\tEnter the Initial Deposit: ");
                        initialDeposit = scanner.nextDouble();
                        scanner.nextLine();

                        if (initialDeposit<500){
                            valid = false;
                            System.out.printf(ERROR_MSG, "Minimum Deposit Should be more than Rs. 500/=");
                            continue;
                        }else {
                            System.out.printf("\tUpdated Account Balance is %.2f",initialDeposit);
                            
                        }

                    }while(!valid);

                    String [] newAccountNumbers = new String [accountNumbers.length+1];
                    String [] newAccountNames = new String [accountNumbers.length+1];                     
                    double [] newAccountBalances = new double [accountNumbers.length+1];
                    for (int i = 0; i < accountNumbers.length; i++) {
                        newAccountNumbers[i] = accountNumbers [i];
                        newAccountNames [i] = accountNames [i];
                        newAccountBalances [i] = accountBalances [i];
                        
                    }

                    newAccountNumbers[newAccountNumbers.length-1] = accountNumber;
                    newAccountNames[newAccountNumbers.length-1] = accountName;
                    newAccountBalances[newAccountNumbers.length-1] = initialDeposit;

                    accountNames = newAccountNames;
                    accountNumbers = newAccountNumbers;
                    accountBalances = newAccountBalances;

                    System.out.println();
                    System.out.printf(SUCCESS_MSG, String.format("%s:%s has been saved successfully", accountNumber, accountName));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;

                }while(valid);
                    
                case DEPOSIT_MONEY:
                case WITHDRAW_MONEY:
                case TRANSFER_MONEY:
                case CHECK_ACCOUNT_BALANCE:
                case DELETE_ACCOUNT:
            }


        }while (true);
        
    }
}
