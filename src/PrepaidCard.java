//Chinonso Nwabueze
//110352750
public class PrepaidCard extends BankCard {

    public PrepaidCard(String cardHolderName, long cardNumber, double balance) {
        super(cardHolderName, cardNumber);
        this.cardBalance = balance;
    }

    public PrepaidCard(String cardHolderName, long cardNumber) {
        super(cardHolderName, cardNumber);
        this.cardBalance = 0;
    }


    @Override
    public boolean addTransaction(Transaction t) {
        if(t.type().equals("debit") && t.amount()<=cardBalance){
            cardBalance-=t.amount();
            transactionsList.add(t);
            return true;
        }

        else if(t.type().equals("debit") && t.amount()>=cardBalance){
            return false;
        }

        else if(t.type().equals("credit")){
            cardBalance-=t.amount();
            transactionsList.add(t);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean addFunds(double amount){
        if(amount>0){
            cardBalance+=amount;
            Transaction add = new Transaction("top-up","User payment",0-amount);
            transactionsList.add(add);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void printStatement() {
        System.out.println(toString());
        for(int x=0;x<transactionsList.size();x++){
            System.out.println(transactionsList.get(x));
        }
    }
}
