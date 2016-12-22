import java.util.ArrayList;

/**
 * Created by a575666 on 12/21/16.
 */
public class MainClass {
    public static void main(String[] args) {

        Wallet w = new Wallet();
        w.addCash(Currency.BILL10);
        w.addCash(Currency.BILL1);
        w.addCash(Currency.QUARTER);
        w.addCard(new PaymentCard("Discover","3293029302393"));
        w.addCard(new PaymentCard("Amex","332323232323233"));
        w.addCard(new PaymentCard("Mastercard","3323232323275575"));
        w.addCard(new IDCard("Driver's License"));



//        w.listCards();

        System.out.println(w);

    }
}

class Wallet{
    private float cash;
    private int cashInCents=0;
    private int cardSlots = 3;
    ArrayList<Card> Cards = new ArrayList<Card>(3);

    boolean addCard(Card card){
        if (Cards.size()>=cardSlots)
        {
            System.out.println("Card slots full. "+card+" cannot be added");
            return false;
        }

        Cards.add(card);
        return true;
    }

    boolean addCash(Currency c){
        cashInCents += c.value;
        return true;
    }

    public double getCash() {
        return cashInCents*0.01;
    }
    public void listCards() {
        for(Card card:Cards)
            System.out.println(card);
    }

    @Override
    public String toString() {
        return "Cash : "+getCash()+"\nCards: "+Cards.toString();
    }
}
interface Card{

}

class PaymentCard implements Card{
    String cardNumber;
    String issuer;

    public PaymentCard(String issuer, String cardNumber) {
        this.issuer = issuer;
        this.cardNumber = cardNumber;
    }

    public String toString() {
        return issuer+" ending in "+cardNumber.substring(cardNumber.length()-4);
    }

    public String getIssuer() {
        return issuer;
    }
}

class IDCard implements Card{

    String type;

    public IDCard(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }

}

enum Currency {
    PENNY(1), NICKLE(5), DIME(10), QUARTER(25), BILL1(1*100), BILL10(10*100), BILL20(20*100), BILL50(50*100), BILL100(100*100);

    int value;

    Currency(int value){
        this.value = value;
    }
}