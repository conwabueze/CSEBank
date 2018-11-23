//Chinonso Nwabueze
//110352750
public class CreditCard extends BankCard {
    private int exp;
    protected double limit;

    public CreditCard(String cardHolderName, long cardNumber, int exp, double limit) {
        super(cardHolderName, cardNumber);
        this.exp = exp;
        this.limit = limit;
    }

    public CreditCard(String cardHolderName, long cardNumber, int exp) {
        super(cardHolderName, cardNumber);
        this.exp = exp;
        this.limit = 500.00;
    }

    public double limit(){
        return limit;
    }

    public double availableCredit(){
        return limit-cardBalance;
    }

    public int expiration(){
        return exp;
    }

    //Extra credit 3
    public boolean getCashAdvance(double cashRequested){
        double feeAddOn=cashRequested+(cashRequested*0.05);
        if(feeAddOn<=availableCredit()){
            cardBalance+= cashRequested+availableCredit();
            Transaction transaction1 = new Transaction("advance", "CSEBank",cashRequested);
            transactionsList.add(transaction1);
            Transaction transaction2 = new Transaction("fee", "Cash advance fee",cashRequested*0.05);
            transactionsList.add(transaction2);
            return true;
        }
    return false;
    }


    @Override
    public String toString() {
        return super.toString() + " exp " + exp + " limit "+ limit;
    }

    @Override
    public boolean addTransaction(Transaction t) {
        if(t.type().equals("debit")&& t.amount()<=availableCredit()){
            this.cardBalance+=t.amount();
            transactionsList.add(t);
            return true;
        }

        else if(t.type().equals("debit")&& t.amount()>availableCredit())
        return false;

        else if(t.type().equals("credit")) {
            cardBalance += t.amount();
            transactionsList.add(t);
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public void printStatement() {
        System.out.println(toString());
        for(int x=0;x<transactionsList.size();x++){
            System.out.println(transactionsList.get(x));
        }
    }



}
