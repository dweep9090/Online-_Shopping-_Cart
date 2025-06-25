package Classes;
import java.util.ArrayList;

public class creditcard extends payment{

    public creditcard(){
        super();
    }
    //16 digit card number-1
    //expiry date-2
    //cvv-3
    public void pay(ArrayList<String> details){
        fileReader j = new fileReader();
        System.out.println("Credit card");
        String a = (j.getLines(1,1,"D:/OOPS/UserInfo/" + details.getFirst() + "bank.txt")).getFirst();
        String b = (j.getLines(2,2,"D:/OOPS/UserInfo/" + details.getFirst() + "bank.txt")).getFirst();
        String c = (j.getLines(3, 3, "D:/OOPS/UserInfo/" + details.getFirst() + "bank.txt")).getFirst();
        if(typeChecker.compare(details.get(1),a) && typeChecker.compare(details.get(2),b) && typeChecker.compare(details.get(3),c))
        {
            System.out.println("Verification successful!!");
        }
        else{
            System.out.println("Details that you have entered are incorrect");
            System.out.println("You will have to pay cash");
            cash p = new cash();
            p.pay(details);
        }
    }
}
