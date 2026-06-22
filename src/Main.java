import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ATMOPRINTERFACE atmopr=new ATMOPR();
        int atmNum=1234567;
        int atmPin=1111;
        System.out.println("Enter your ATM Card Number ");
        int atmCardNum=sc.nextInt();
        System.out.println("Enter your ATM Card Pin Number") ;
        int atmCardPin=sc.nextInt();
        if(atmNum==atmCardNum && atmPin ==atmCardPin){
            System.out.println("Login Successfully");
            while(true){
                System.out.println("Enter 1 to view Balance");
                System.out.println("Enter 2 to Deposit Amount");
                System.out.println("Enter 3 to Withdraw Amount");
                System.out.println("Enter 4 to Print mini statement");
                System.out.println("Enter 5 to Exit");
                int userinput=sc.nextInt();
                if(userinput==1){
                    atmopr.viewBalance();
                }
                else if(userinput==2){
                    System.out.println("Enter the Amount deposit :");
                    double amount=sc.nextDouble();
                    atmopr.depositAmount(amount);
                }else if(userinput==3){
                    System.out.println("Enter amount to Withdraw :");
                    double amount=sc.nextDouble();
                    atmopr.withdrawalAmount(amount);
                }else if(userinput==4){
                    atmopr.miniStatement() ;
                }else if(userinput==5){
                    System.out.println("Please collect Your Card");
                    System.out.println("Thank you !!!") ;
                    System.exit(0);
                }else{
                    System.out.println("Please enter valid operation") ;
                }
            }
        }
        else{
            System.out.println("Invalid ATM number or Pin") ;
        }
    }
}
