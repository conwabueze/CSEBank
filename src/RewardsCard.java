//Chinonso Nwabueze
//110352750
public class RewardsCard extends CreditCard {

    protected int rewardPoints;

    public RewardsCard(String cardHolderName, long cardNumber, int expiration, double limit) {
        super(cardHolderName, cardNumber, expiration, limit);
        this.rewardPoints=0;
    }

    public RewardsCard(String cardHolderName, long cardNumber, int expiration) {
        super(cardHolderName, cardNumber, expiration);
        this.limit = 500.00;
        this.rewardPoints=0;
    }

    public int rewardPoints(){
        return rewardPoints;
    }

    public boolean redeemPoints(int points){
        if(points<=rewardPoints && points/100<=cardBalance){
            cardBalance-=(points/100);
            rewardPoints-=points;
            Transaction redemption = new Transaction("redemption","CSEBANK",cardBalance);
            transactionsList.add(redemption);
            return true;
        }
        else if(points<=rewardPoints && points/100>cardBalance){
            rewardPoints-=cardBalance*100;
            cardBalance=0;
            Transaction redemption = new Transaction("redemption","CSEBANK",cardBalance);
            transactionsList.add(redemption);
            return true;
        }
        return false;
    }

    public String toString(){
        return super.toString() + " reward points " + rewardPoints;
    }

    @Override
    public boolean addTransaction(Transaction t) {
        if(t.type().equals("debit")&& t.amount()<=availableCredit()){
            this.cardBalance+=t.amount();
            rewardPoints+=(t.amount()*100);
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
