//Chinonso Nwabueze
//110352750
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// Driver class for the final project
public class TransactionProcessor {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter card data file: ");
        String dataFile = input.nextLine();
        CardList list;

            list=loadCardData(dataFile);

            System.out.println("Enter transaction data file name: ");
            String transactionDataFile=input.nextLine();
            processTransactions(transactionDataFile,list);
            System.out.println();


            for(int x=0;x<list.size();x++){
                list.get(x).printStatement();

                System.out.println();
            }

        }







    private static String getCardType (long number) {
        // Return a String indicating whether 'number' belongs to a
        // CreditCard, RewardsCard, or a PrepaidCard (or null if it's none
        // of the three)

        String result;

        int firstTwo = Integer.parseInt(("" + number).substring(0,2));

        switch(firstTwo) {
            case 84:
            case 85: result = "CreditCard"; break;
            case 86:
            case 87: result = "RewardsCard"; break;
            case 88:
            case 89: result = "PrepaidCard"; break;
            default: result = null; // invalid card number
        }

        return result;
    }

    public static BankCard convertToCard(String data) {
        long cardNumber;
        String cardHolder;
        BankCard card;

        Scanner input = new Scanner(data);
        try {

            //Get card Number
            cardNumber = input.nextLong();



            //get holders name
            cardHolder = input.next();
            //Extra Credit 1
            String[] holderSplit= cardHolder.split("_");
            cardHolder = holderSplit[0]+" "+holderSplit[1];


            if (!getCardType(cardNumber).equals(null)) {
                String cardType = getCardType(cardNumber);

                if (cardType.equals("CreditCard")) {
                    int expirationDate = input.nextInt();




                    if(input.hasNextDouble()) {
                        double cardLimit = input.nextDouble();

                        card = new CreditCard(cardHolder, cardNumber, expirationDate, cardLimit);

                        return card;
                    }else{
                        card = new CreditCard(cardHolder, cardNumber, expirationDate);
                        return card;
                    }
                } else if (cardType.equals("RewardsCard")) {
                    int expirationDate = input.nextInt();



                    if(input.hasNextDouble()) {
                        double cardLimit = input.nextDouble();

                        card = new RewardsCard(cardHolder, cardNumber, expirationDate, cardLimit);


                        return card;
                    }else{
                        card = new RewardsCard(cardHolder, cardNumber, expirationDate);

                    }
                } else if (cardType.equals("PrepaidCard")) {
                    if(input.hasNextDouble()){
                        double balance= input.nextDouble();

                        card = new PrepaidCard(cardHolder, cardNumber, balance);


                        return card;
                    }
                    card = new PrepaidCard(cardHolder, cardNumber);

                    return card;
                }


            }

        }catch (Exception ex){
            System.out.println("Cannot process anything with this String ");
        }
        return null;
    }





    public static CardList loadCardData(String fName){
        CardList list = new CardList();
        try (BufferedReader reader = new BufferedReader(new FileReader(fName))) {
            while (true) {

                String line = reader.readLine();
                list.add(convertToCard(line));

                if (line == null) {
                    break;
                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("stop");
        }
        catch (IOException ex){
            return null;
        }
        catch (NullPointerException ex){

        }
        for(int x=0;x<list.size();x++){

            System.out.println(list.get(x));
            System.out.println();
        }



        return list;
    }

    public static void processTransactions(String filename,CardList c){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (true) {

                String line = reader.readLine();
                String[] cutIt= line.split(" ");
                long cardNumber = Long.parseLong(cutIt[0]);


                //Index of matching Card Number
                int index=0;
                for(int x=0;x<c.size();x++){
                    if(c.get(x).cardNumber==cardNumber)
                        index=x;
                }



                //checking transaction type
                String transactionType = cutIt[1];

                if(transactionType.equals("redeem")){
                    int pointsToRedeem = Integer.parseInt(cutIt[2]);
                    RewardsCard card = (RewardsCard)c.get(index);
                    card.redeemPoints(pointsToRedeem);

                }
                else if(transactionType.equals("top-up")){
                    double moneyToAdd = Double.parseDouble(cutIt[2]);
                    PrepaidCard card=(PrepaidCard)c.get(index);

                    card.addFunds(moneyToAdd);

                }
                //Extra Credit 3
                else if(transactionType.equals("advance")){
                    double cashAdvance = Double.parseDouble(cutIt[2]);
                    CreditCard card = (CreditCard)c.get(index);
                    card.getCashAdvance(cashAdvance);
                }
                else {
                    //Extra Credit 1
                    if(cutIt[3].contains("_")){
                        String merchantName = "";
                        String[] merchantMod = cutIt[3].split("_");
                        for(int x=0;x<merchantMod.length;x++){
                            merchantName+=merchantMod[x]+" ";
                        }
                        Transaction transaction =new Transaction(transactionType,merchantName,Double.parseDouble(cutIt[2]));
                        BankCard card = c.get(index);
                        card.addTransaction(transaction);
                    }
                    else {
                        Transaction transaction = new Transaction(transactionType, cutIt[3], Double.parseDouble(cutIt[2]));
                        BankCard card = c.get(index);
                        card.addTransaction(transaction);
                    }

                }


                if (line == null) {
                    break;
                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File Not Found");
        }
        catch (IOException ex){
            System.out.println("IO problems");
        }
        catch (NullPointerException ex){
            System.out.println("We have reached the end");
        }

    }
}


