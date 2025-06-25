package Classes;

import java.io.*;
import java.util.*;


public class fileReader{

    public boolean availableNow(String name){

        boolean found=false;


        ArrayList<String> requestRecord = getLines(6,NoOfLines("userInfo/"+name+".txt"),"userInfo/"+name+".txt");
        if(requestRecord==null) return false;

        for(String temp1: requestRecord){

            String seperate[] = temp1.split(",");
            Company.Request request =new Company.Request(seperate[3],Integer.parseInt(seperate[2]),seperate[1],seperate[0]);

            ArrayList<Product> arr = getProducts("database/"+request.brand+".csv", 2, NoOfLines("database/"+request.brand+".csv"));

            for(Product temp: arr){
                if(request.type.equals(temp.getType()) && temp.getSize().contains( request.size.get(0) )){
                    int indexOfAvail =temp.getSize().indexOf( request.size.get(0) );
                    if(temp.getAvail().get(indexOfAvail)  >= request.required) {
                        found = true;
                        System.out.println(temp.getBrand()+" "+temp.getType() +" is available now, which you had requested last time.");
                    }
                }
            }
        }


        return found;

    }


    public void load(ArrayList<String> categories, Map<String, ArrayList<Product> > NameToList, String filePath){

        ArrayList<Product> arr = getProducts(filePath, 2, NoOfLines(filePath));
 
        for(Product p: arr){
            for(String t : categories){
               if(p.getType().contains(t)){
                 NameToList.get(t).add(p);
               }
            }
        }
 
     }

     public ArrayList<Product> getProducts(String filePath, int startLine, int endLine){
 
        String line = "";
        String splitby = ",";
        String splitby2 = "-";
        int lineNumber=1;


        ArrayList<Product> productList = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            //skip first line
            line = br.readLine();
            lineNumber++;
            
            while((line = br.readLine()) != null){

                if(lineNumber<=endLine && lineNumber>=startLine) {

                    String[] arr = line.split(splitby);
                
                //split 28-30-32 into 28,30,32 and storing into an arraList
                String[] size = arr[4].split(splitby2);
                ArrayList<String> Size = new ArrayList<>();
                for(String b : size){
                    Size.add(b);
                }
                
                //split 10-10-10 into 10,10,10 and storing into an arraList
                String[] avail = arr[3].split(splitby2);
                ArrayList<Integer> Avail = new ArrayList<>();
                for(String i : avail){
                    Avail.add(Integer.parseInt(i));
                }

                //create a product using the data
                Product p = new Product(arr[0],arr[1],Size, Double.parseDouble(arr[2]),Avail);
                productList.add(p);

                }

                lineNumber++;

            }
            
        }catch(IOException e){
            System.out.println(e);
        }

        return productList;
        
    }

    public boolean checkName(String name){

        try(BufferedReader br = new BufferedReader(new FileReader("userInfo/userList.txt"))){

            String line;
            while((line= br.readLine()) !=null){
                if(typeChecker.compare(name ,line )) return true;
            }
        }catch(IOException e){
            System.out.println(e);
        }
        return false;
    }

 

    public int NoOfLines(String filePath){

        int lineNumber=1;

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            
            while(br.readLine() != null){
                lineNumber++;
            }
        }catch(IOException e){
            System.out.println(e);
        }
        return lineNumber;

    }


    public ArrayList<String> getLines(int startLine, int endLine, String filePath){

        int lineNumber=1;
        ArrayList<String> lines = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){

            String line;
            while((line = br.readLine()) != null){
                if(lineNumber >= startLine && lineNumber<=endLine){
                    lines.add(line);
                }
                if(lineNumber== endLine) return lines;
                lineNumber++;
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return lines;

    } 

    
    


    

    
}