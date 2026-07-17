public class ATM {
    private long cardNumber;    // ADD THIS
    private double balance;
    private double withdrawalAmount;
    private double depositAmount;

    public long getCardNumber() { return cardNumber; }      // ADD THIS
    public void setCardNumber(long cardNumber) { this.cardNumber = cardNumber; }  // ADD THIS

    public double getBalnce(){
        return balance;
    }
    public void setBalance(double balance){
        this.balance=balance;
    }
    public double getWithdrawalAmount(){
        return withdrawalAmount;
    }
    public void setWithdrawalAmount(double withdrawalAmount){
        this.withdrawalAmount=withdrawalAmount;
    }
    public double getDepositAmount(){
        return depositAmount;
    }
    public void setDepositAmount(double depositAmount){
        this.depositAmount=depositAmount;
    }
}