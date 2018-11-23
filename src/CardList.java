//Chinonso Nwabueze
//110352750
import java.util.ArrayList;

public class CardList {
    private ArrayList<BankCard> bankCardList;

    public CardList(){
        bankCardList = new ArrayList<>();
    }

    public void add(BankCard b){
        bankCardList.add(b);
    }

    public void add(int index,BankCard b){
        if(index>=0||index<=bankCardList.size()-1){
            bankCardList.add(index,b);
        }
    }

    public int size(){
        return bankCardList.size();
    }

    public BankCard get(int index){
        if(index>=0||index<=bankCardList.size()-1){
            return bankCardList.get(index);
        }
        return null;
    }

    public int indexOf(long cardNumber){
        for(int x=0;x<bankCardList.size();x++){
           if(bankCardList.get(x).cardNumber==cardNumber){
               return x;
           }
        }
        return -1;
    }


}
