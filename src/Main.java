import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATMOPR atmopr = new ATMOPR();

        System.out.println("Enter your ATM Card Number ");
        long atmCardNum = sc.nextLong();
        System.out.println("Enter your ATM Card Pin Number");
        int atmCardPin = sc.nextInt();

        String sql = "SELECT * FROM accounts WHERE card_number = ? AND pin = ?";
        try (java.sql.Connection con = DBConnection.getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, atmCardNum);
            ps.setInt(2, atmCardPin);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                atmopr.setCardNumber(atmCardNum);
                System.out.println("Login Successfully");
                while (true) {
                    System.out.println("Enter 1 to view Balance");
                    System.out.println("Enter 2 to Deposit Amount");
                    System.out.println("Enter 3 to Withdraw Amount");
                    System.out.println("Enter 4 to Print mini statement");
                    System.out.println("Enter 5 to Exit");
                    int userinput = sc.nextInt();
                    if (userinput == 1) atmopr.viewBalance();
                    else if (userinput == 2) {
                        System.out.println("Enter the Amount deposit :");
                        double amount = sc.nextDouble();
                        atmopr.depositAmount(amount);
                    } else if (userinput == 3) {
                        System.out.println("Enter amount to Withdraw :");
                        double amount = sc.nextDouble();
                        atmopr.withdrawalAmount(amount);
                    } else if (userinput == 4) atmopr.miniStatement();
                    else if (userinput == 5) {
                        System.out.println("Please collect Your Card");
                        System.out.println("Thank you !!!");
                        System.exit(0);
                    } else System.out.println("Please enter valid operation");
                }
            } else {
                System.out.println("Invalid ATM number or Pin");
            }
        } catch (java.sql.SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }
}