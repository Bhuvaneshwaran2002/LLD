import java.sql.*;
import java.util.*;

public class ATMOPR implements ATMOPRINTERFACE {
    ATM atm = new ATM();

    public void setCardNumber(long cardNumber) {
        atm.setCardNumber(cardNumber);
    }

    @Override
    public void viewBalance() {
        String sql = "SELECT balance FROM accounts WHERE card_number = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, atm.getCardNumber());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Total Amount in your Account : Rs " + rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    @Override
    public void depositAmount(double amount) {
        String updateSql = "UPDATE accounts SET balance = balance + ? WHERE card_number = ?";
        String insertSql = "INSERT INTO transactions(card_number, amount, type) VALUES(?, ?, 'DEPOSIT')";
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps1 = con.prepareStatement(updateSql);
                 PreparedStatement ps2 = con.prepareStatement(insertSql)) {
                ps1.setDouble(1, amount);
                ps1.setLong(2, atm.getCardNumber());
                ps1.executeUpdate();
                ps2.setLong(1, atm.getCardNumber());
                ps2.setDouble(2, amount);
                ps2.executeUpdate();
                con.commit();
                System.out.println("Deposited Amount : Rs " + amount);
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Deposit failed!");
            }
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    @Override
    public void withdrawalAmount(double amount) {
        if (amount % 500 != 0) {
            System.out.println("Please enter the amount which is multiples of 500");
            return;
        }
        String checkSql  = "SELECT balance FROM accounts WHERE card_number = ?";
        String updateSql = "UPDATE accounts SET balance = balance - ? WHERE card_number = ?";
        String insertSql = "INSERT INTO transactions(card_number, amount, type) VALUES(?, ?, 'WITHDRAW')";
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps0 = con.prepareStatement(checkSql)) {
                ps0.setLong(1, atm.getCardNumber());
                ResultSet rs = ps0.executeQuery();
                if (rs.next() && rs.getDouble("balance") >= amount) {
                    try (PreparedStatement ps1 = con.prepareStatement(updateSql);
                         PreparedStatement ps2 = con.prepareStatement(insertSql)) {
                        ps1.setDouble(1, amount);
                        ps1.setLong(2, atm.getCardNumber());
                        ps1.executeUpdate();
                        ps2.setLong(1, atm.getCardNumber());
                        ps2.setDouble(2, amount);
                        ps2.executeUpdate();
                        con.commit();
                        System.out.println("Amount withdraw from your Account : Rs " + amount);
                    }
                } else {
                    con.rollback();
                    System.out.println("Insufficient Balance");
                }
            }
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    @Override
    public void miniStatement() {
        String sql = "SELECT amount, type, txn_time FROM transactions " +
                "WHERE card_number = ? ORDER BY txn_time DESC LIMIT 5";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, atm.getCardNumber());
            ResultSet rs = ps.executeQuery();
            System.out.println("--- Mini Statement (Last 5 Transactions) ---");
            while (rs.next()) {
                System.out.println(rs.getString("txn_time") +
                        " | " + rs.getString("type") +
                        " | Rs " + rs.getDouble("amount"));
            }
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }
}