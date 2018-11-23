//Chinonso Nwabueze
//110352750
import java.util.ArrayList;

public abstract class BankCard {
    private String cardHolderName;
    protected long cardNumber;
    protected double cardBalance;
    protected ArrayList<Transaction> transactionsList = new ArrayList<>();

    public BankCard(String cardHolderName, long cardNumber) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cardBalance=0;
    }

    public String cardHolder() {
        return cardHolderName;
    }

    public long number() {
        return cardNumber;
    }

    public double balance() {
        return cardBalance;
    }

    public String toString(){
        return "Name " + cardHolderName+ " Card# "+cardNumber+" Balance "+cardBalance;
    }

    public void transactionPrintOut(){
        System.out.println("Transactions Made: ");
        for(int x=0;x<transactionsList.size();x++){
            System.out.println(transactionsList.get(x));
        }
    }


    public abstract boolean addTransaction(Transaction t);
    public abstract void printStatement();
}
