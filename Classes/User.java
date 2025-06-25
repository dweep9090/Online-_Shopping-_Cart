package Classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class User {

    private String phoneNumber;
    public String userName;
    private String id;
    private String address;
    private String password;

    public String inputName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your first name : ");
        userName = sc.next();
        return userName;
    }

    public String inputNumber() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a 10 digit valid phone number : ");
        phoneNumber = sc.next();

        if (phoneNumber.length() != 10) {
            return inputNumber();
        } else {
            for (int i = 0; i < 10; i++) {
                char c = phoneNumber.charAt(i);
                if ((int) c <= 57 && (int) c >= 48) {
                    continue;
                } else {
                    return inputNumber();
                }
            }
        }

        return phoneNumber;
    }

    public String inputEmail() {
        System.out.println("Enter your email address  : ");
        Scanner sc = new Scanner(System.in);
        id = sc.next();
        return id;
    }

    public String loc() {
        System.out.println("Enter your permanent address");
        System.out.println("It should have the following: ");
        System.out.println("1. House name/Block number");
        System.out.println("2. Street name/Society name");
        System.out.println("3. A nearby landmark");
        System.out.println("4. City");
        System.out.println("5. State");
        System.out.println("6. Country and Pincode");
        System.out.println("in this form(house name, street name, ...)");
        Scanner sc = new Scanner(System.in);
        address = sc.nextLine();
        return address;
    }

    public String pw(User o) {
        Scanner scp = new Scanner(System.in);
        System.out.println("Enter your password : ");
        o.password = scp.nextLine();
        if(o.password.contains("0") || o.password.contains("1") || o.password.contains("2") || o.password.contains("3") || o.password.contains("4") || o.password.contains("5") || o.password.contains("6") || o.password.contains("7") || o.password.contains("8") || o.password.contains("9"))
        {
            return password;
        }
        else{
            System.out.println("Your password must contain a number !!");
            return pw(o);
        }
    }

    public void signup(){
            User obj = new User();
            String number = typeChecker.removeSpaceAtEnd(obj.inputNumber());

            String username = typeChecker.removeSpaceAtEnd(obj.inputName());

            String email = typeChecker.removeSpaceAtEnd(obj.inputEmail());

            String location = typeChecker.removeSpaceAtEnd(obj.loc());

            String p = typeChecker.removeSpaceAtEnd(obj.pw(obj));

            try(BufferedWriter bw = new BufferedWriter(new FileWriter("userInfo/userList.txt",true))){
                bw.write(username);
                bw.newLine();
            }catch(IOException e){
                System.out.println(e);
            }

            // Writing user details to a file
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("userInfo/"+username + ".txt"));
                writer.write("Name: " + username + "\n");
                writer.write("Phone Number: " + number + "\n");
                writer.write("Email Address: " + email + "\n");
                writer.write("Location: " + location + "\n");
                writer.write("Password: " + p + "\n");
                writer.close();
            } 
            catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
    }
}