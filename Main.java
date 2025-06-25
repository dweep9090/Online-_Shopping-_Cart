import java.io.*;
import java.util.*;

import Classes.*;


public class Main{

    public static void main(String[] args){


        ArrayList<Company.Request> requestRecord = new ArrayList<>();

        Map<String, Company> NameToCompany = new HashMap<String, Company>();

        Company USpolo =new Company();
        Company WestSide = new Company();
        Company spykar =new Company();
        NameToCompany.put("Spykar", spykar);
        NameToCompany.put("West Side", WestSide);
        NameToCompany.put("US Polo", USpolo);

        ArrayList<Product> jeans = new ArrayList<>();
        ArrayList<Product> tshirt = new ArrayList<>();
        ArrayList<Product> shirt = new ArrayList<>();
        ArrayList<Product> hoodie = new ArrayList<>();
        ArrayList<Product> jacket = new ArrayList<>();
        ArrayList<Product> denimShorts = new ArrayList<>();
        ArrayList<Product> sweatShirt = new ArrayList<>();

               
        //mapping name to arraylist to acess its element, suppose "jeans" corresponds to jeans then with map we can access jeans arraylist
        Map<String , ArrayList<Product> > NameToList = new HashMap<String , ArrayList<Product> >();
        NameToList.put("Jeans",jeans);
        NameToList.put("Tshirt",tshirt);
        NameToList.put("Shirt",shirt);
        NameToList.put("Hoodie",hoodie);
        NameToList.put("Jacket",jacket);
        NameToList.put("Denim Shorts",denimShorts);
        NameToList.put("Sweatshirt",sweatShirt);

        ArrayList<String> category = new ArrayList<>();
        category.add("Jeans");
        category.add("Tshirt");
        category.add("Shirt");
        category.add("Hoodie");
        category.add("Jacket");
        category.add("Denim Shorts");
        category.add("Sweatshirt");

        fileReader fr = new fileReader();
        fr.load(category, NameToList, "database/Spykar.csv");
        fr.load(category, NameToList, "database/Uspolo.csv");
        fr.load(category, NameToList, "database/Westside.csv");



        //profile

        System.out.println("Enter your username");

        User obj = new User();
        Scanner s = new Scanner(System.in);
        String name = s.next();

        if(!fr.checkName(name)){
            System.out.println("You need to sign-up first");
            obj.signup();
        }
        else{
            System.out.println("Welcome to Online Shopping Cart!!");
            fr.availableNow(name);
        }
        //profile created

        System.out.println();
        System.out.println("What would you like to buy:");
        System.out.println();


        for(String str: NameToList.keySet()){
            System.out.println(str);
        }
        System.out.println();
        //to keep the track of items selected from variety
        String record[]=new String[8];

        System.out.println("If you want to buy all of the item then type 'all' or else 'select' ");
        
        Scanner sc=new Scanner(System.in);
        String choose=sc.nextLine();
        System.out.println();
        
        //input items user want to buy

        int i;
        if( !(typeChecker.compare(choose,"all")) ){

            System.out.println("Enter name of items (in front of ':') you want to buy. Enter 'done' (in front of ':') when you done adding items to the list.");
            System.out.println();

            i = -1;
            do{
                System.out.print(": ");
                record[++i]= sc.nextLine();

                if(typeChecker.compare(record[i],"done")){
                    record[i] = "done";
                    break;
                }
                
                //replace the entered word with exact word in the arrayList category
                for(String temp: category){
                    if( typeChecker.compare(record[i], temp) ) record[i] = temp;
                } 
                
            }while( true );

        }else {
            //if user want to buy all items
            i=0;
            for( String str:NameToList.keySet()){
                record[i]=str;
                i++;
            }
            record[i]="done";
        }
        

        Cart cart=new Cart();
        i=0;
        String key;

        //display the menu and adding items to the cart
        while( !( typeChecker.compare(record[i],"done") ) ){
            
            key=record[i];
            System.out.println();
            System.out.println("->This are the " + key + " we have:");
            System.out.println();
            
            //print menu of products user wants to buy
            String prevBrand="/0";
            String curBrand;
            int index=1;
            for(Product temp : NameToList.get(record[i])){

                curBrand = temp.getBrand();
                if( !(curBrand.equals(prevBrand)) ){
                    System.out.println("#" + curBrand);
                    System.out.println();
                }
                prevBrand=curBrand;
                System.out.println(index +". "+temp.getType());
                System.out.println("Price: "+temp.getPrice());
                System.out.print("Available size: ");
                for(String size: temp.getSize()) System.out.print(size+" ");
                System.out.println();
                System.out.println();
                index++;
            }


            System.out.println("Enter 'done' when you are done adding "+ key +" in cart, else press enter and continue to add.");

            // this while loop fills the cart with desired products
            while( true ){

                try{
                //input necessary details

                System.out.print("Press enter/done: ");
                String check = sc.nextLine();
                if( typeChecker.compare(check,"done") )  break;
                System.out.println();
                
                System.out.print("Enter corresponding index of item you want to buy: ");
                index = sc.nextInt();
                sc.nextLine();
                
                System.out.print("Enter size: ");
                String size= typeChecker.removeSpaceAtEnd(sc.nextLine());
                if(size.length()!=2) {
                    //converts lower case to upper case
                    size = "" + Character.toUpperCase(size.charAt(0));
                }

               
                System.out.print("Enter quantity: ");
                int quantity= sc.nextInt();
                sc.nextLine();

                System.out.println();
                

               
                    //add item to cart
                    Product temp = (NameToList.get(record[i])).get(index-1);

                    //every size of a product has equal number of pieces availble.
                    int available =temp.getAvail().get( temp.getSize().indexOf(size));

                    if(available < quantity) {
                        //if required qauntity is not availble , add request in outofstock list of company and if user want, add available quantity to cart. 
                        System.out.println("Out of stock");
                        System.out.println("A request has been sent to the company, the item will be made availble in reqired quantity in some days.");
    
                        if(available!=0){
                        System.out.println("If you want we add " + available + " items in the cart.  Yes/No");
                        String permission = sc.nextLine();
                        if(typeChecker.compare(permission, "yes")) cart.add(temp,available, size);
                       }
    
                        NameToCompany.get(temp.getBrand()).addRequest(size, quantity-available, temp.getType(), temp.getBrand(),requestRecord);
    
                    } else cart.add(temp, quantity, size);

                }
                catch(InputMismatchException e){
                    System.out.println(e);
                    System.out.println("we were not able to add the product to the cart, we request you to enter details of the product again.");
                    continue;

                }
                catch (NullPointerException e){
                    System.out.println(e);
                    System.out.println("Sorry, we can not add this product in the cart due to some system error.");
                    continue;
                }
                catch(IndexOutOfBoundsException e){
                    System.out.println(e);
                    continue;
                }
                
    
            }
            //cart filling ends here
        
            i++;
        }
        
        //now we will ask user if he wants to remove any item from the cart.
    
        System.out.println("Here's your cart:");
        cart.show();
        System.out.println();
        System.out.println("Do you want to remove any item from the cart? Yes/No");

        
        if(typeChecker.compare( sc.nextLine(), "Yes" )) {

            
            System.out.println();
            System.out.println("Enter the indices of items you want to delete( in this form: 1,2,3)");

            //stores indices in removeElements array
            String removeElements[]= (sc.nextLine()).split(",");
            ArrayList<Integer> index =new ArrayList<>();

            //converts indices from string to int
            for(String temp: removeElements){

                //Integer.parseInt(temp)-1 , -1 as indexing in arraylist starts from 0 and here we are starting from 1
                index.add(Integer.parseInt(temp)-1);
            }

            System.out.println("Enter the corresponding quantities of items you want to delete(in the form 2,3,1)");
            String NoOfRemovedElements[] = (sc.nextLine()).split(",");
            ArrayList<Integer> number =new ArrayList<>();
            for(String temp: NoOfRemovedElements){
                number.add(Integer.parseInt(temp));
            }
            
            try{
                cart.remove(index, number);
            }catch (IndexOutOfBoundsException e){
                System.out.println(e);
            }

        }

        //payment
        ArrayList<String> UserList = new ArrayList<>();
        System.out.println("You need to enter your details one by one in the following order: ");
        UserList.add(name);

        System.out.println("Enter 0 for upi, 1 for credit card and 2 for cash payments");
        int n = sc.nextInt();
        sc.nextLine();

        payment p;
        if(n==0){

            System.out.println("Enter your virtual ID: ");
            String id = sc.nextLine();
            UserList.add(id);
            System.out.println("Enter your 4 digit passcode: ");
            String passcode = sc.nextLine();
            UserList.add(passcode);

            p = new upi();
            p.pay(UserList);
        }
        else if(n==1){

            System.out.println("Enter your 16 digit card number: ");
            String number = sc.nextLine();
            UserList.add(number);
            System.out.println("Enter the expiry date of your card: ");
            String expiry = sc.nextLine();
            UserList.add(expiry);
            System.out.println("Enter CVV of your card: ");
            String cvv = sc.nextLine();
            UserList.add(cvv);

            p = new creditcard();
            p.pay(UserList);
        }
        else if(n==2){
            p = new cash();
            p.pay(UserList);
        }

        //get bill
        cart.getBill(name);

        //add requestRecord to user file
        if(requestRecord.size()!=0){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("userInfo/"+ name+".txt",true))){
                for(Company.Request temp: requestRecord){
                    bw.write(temp.brand+","+temp.type+","+temp.required+","+temp.size.get(0));
                    bw.newLine();
                }
            }catch(IOException e){
                System.out.println("Request can't be loaded");
                System.out.println(e);
            }
        }


    }
}
