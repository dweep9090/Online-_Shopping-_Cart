package Classes;
import java.util.ArrayList;

public class upi extends payment{
    public upi(){
        super();
    }
    //Virtual ID-5
    //Password-6
    
    public void pay(ArrayList<String> details){
        fileReader j = new fileReader();
        System.out.println("upi");
        String x = (j.getLines(5,5,"D:/OOPS/UserInfo/" + details.getFirst() + "bank.txt")).getFirst();
        String y = (j.getLines(6,6,"D:/OOPS/UserInfo/" + details.getFirst() + "bank.txt")).getFirst();
        System.out.println(details.get(1));
        System.out.println(details.get(2));
        if(typeChecker.compare(details.get(4),x) && typeChecker.compare(details.get(5),y)){
            System.out.println("Verification successful!!");
        }
        else{
            System.out.println("Details that you have entered are incorrect.");
            System.out.println("You will have to pay cash");
            cash h = new cash();
            h.pay(details);
        }
    }
}
