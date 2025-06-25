package Classes;

import java.util.ArrayList;



public class Company{


    public static class Request{
        public ArrayList<String> size=new ArrayList<>();
        public int required;
        public String type;
        public String brand;
        
        public Request(String size, int required, String type, String brand){
            this.required=required;
            (this.size).add(size);
            this.type=type;
            this.brand =brand;
        }
        
    }

    private ArrayList<Request> OutOfStock = new ArrayList<Request>();
    

    public ArrayList<Product> getList(){ 
        ArrayList<Product> arr =new ArrayList<Product>();
        return arr;
    }

    public void addRequest(String size,  int required, String type, String brand, ArrayList<Request> arr){
         
        boolean b= true;
        for( Request req:this.OutOfStock){
            if(req.type.equals(type) && req.required==required){
                req.size.add(size);
                b=false;
            }  
        }

        Request temp = new Request(size, required, type, brand);
        arr.add(temp);
        if(b) this.OutOfStock.add(temp);
    }

    public void removeRequest(Company.Request req){
        OutOfStock.remove(req);
    }

    public void show(){
        
        if(OutOfStock.isEmpty()) {
            System.out.println("No request left");
            return;
        }

        for(Request temp: OutOfStock){
            System.out.println(temp.brand + " "+ temp.type + " "+ temp.size+ " "+temp.required);
        }
    }


    

    
}


