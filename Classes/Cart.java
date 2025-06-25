package Classes;

import java.io.*;
import java.util.ArrayList;


public class Cart {

    public class CartItem extends Product {

        public int quantity;

        CartItem(Product temp, int quantity, String size) {
            super(temp.getBrand(), temp.getType(), size, temp.getPrice());
            this.quantity = quantity;
        }
    }


    private ArrayList<CartItem> cartList = new ArrayList<>();



    public void add(Product temp, int quantity, String size) {

        CartItem tempc = new CartItem(temp, quantity, size);

        boolean found = false;
        for (CartItem item : cartList) {

            //check if item already exist in the cart then increase its quantity
            if (item.getBrand().equals(tempc.getBrand()) && item.getSize().contains(tempc.getSize().get(0)) && item.getType().equals(tempc.getType())) {
                item.quantity += tempc.quantity;
                temp.setAvail(temp.getAvail(), temp.getSize().indexOf(size), temp.getAvail().get( temp.getSize().indexOf(size) ) -quantity );
                found = true;
                break;
            }
        }

        //else add item to the cart
        if (!found) {
            cartList.add(tempc);
            temp.setAvail(temp.getAvail(), temp.getSize().indexOf(size), temp.getAvail().get( temp.getSize().indexOf(size) ) -quantity );
        }
    }


    public void remove(ArrayList<Integer> index , ArrayList<Integer> quantity) throws IndexOutOfBoundsException{

        // we need to add cartItem ,to be removed,to the list as once we delete an item from cart index number doesn't remain the same
        ArrayList<CartItem> arr =new ArrayList<>();

        
        //add all the carItem in arr to the list 
        for(int temp: index){
            arr.add(cartList.get(temp));   
        }

        //arr.get(i) is product ,removes the cartItem or decrement the quantity
        for(int i=0;i<index.size();i++){
            if (arr.get(i).quantity <= quantity.get(i)) {
                cartList.remove(arr.get(i));
            }else{
                arr.get(i).quantity -= quantity.get(i);
            }
        }
        
        return;
            
        

    }

        public void getBill(String name) {
            try {
                File file = new File("bill/" + name + ".csv");
                file.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(file.getPath(),true));

                bw.write("Brand,Type,Size,Quantity,Price per Item,Total Price\n");

                double totalPrice=0;
                for (CartItem item : cartList) {
                    // Extracting information
                    String brand = item.getBrand();
                    String type = item.getType();
                    String size = item.getSize().get(0); // Assuming only one size for simplicity
                    int quantity = item.quantity;
                    double pricePerItem = item.getPrice();
                     totalPrice += quantity * pricePerItem;

                    // Writing to CSV

                    bw.write(String.format("%s,%s,%s,%d,%.2f,%.2f", brand, type, size, quantity, pricePerItem, quantity*pricePerItem ));
                    bw.newLine();
                }

                bw.write("TotalPrice: "+totalPrice);

                bw.close();
                System.out.println("Bill is in the bill folder.");
                System.out.println("Thank you for shopping!");
            } catch (IOException e) {
                System.out.println("Error occurred while generating bill: " + e.getMessage());
            }
        }


    public void show(){
      
        if(cartList.size()!=0){
            int i=1;
            for(CartItem temp: cartList){
                System.out.println(i+". "+temp.getBrand()+" "+ temp.getType()+" "+ temp.getSize()+" "+temp.quantity+" "+temp.quantity*temp.getPrice());
                i++;
            }
        } else {
            System.out.println("Cart is empty");
        }
          
    }


}
