import java.util.HashMap;
import java.util.Map;

public class ATMOPR implements ATMOPRINTERFACE{
    ATM atm=new ATM();
    Map<Double, String> miniStatement=new HashMap<Double,String>();

    @Override
    public void viewBalance() {
        System.out.println("Total Amount in your Account : Rs "+atm.getBalnce());
    }

    @Override
    public void depositAmount(double amount) {
        miniStatement.put(amount,"Deposited");
        System.out.println("Deposited Amount : Rs "+amount);
        atm.setBalance(atm.getBalnce()+amount);
    }

    @Override
    public void withdrawalAmount(double amount) {

        if(amount<=atm.getBalnce()){
            if(amount%500==0){
                miniStatement.put(amount," Withdraw");
                System.out.println("Amount withdraw from your Account : Rs "+amount);
                atm.setBalance(atm.getBalnce()-amount);
            }else{
                System.out.println("Pleace enter the amount which is multiples of 500");
            }
        }
        else{
            System.out.println("Insufficient Balance");
        }
    }

    @Override
    public void miniStatement() {
        for(Map.Entry<Double,String>entry:miniStatement.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
