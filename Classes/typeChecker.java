package Classes;


public class typeChecker{

    public static String removeSpaceAtEnd(String check){

        String filter[] = check.split(" ");
        
        if(filter.length == 1) return filter[0];

        check="";
        for(int i=0;i< filter.length;i++){
            if(i != filter.length-1) check += filter[i]+" ";
            else check += filter[i];
        }

        return check;
    }

    public static boolean compare(String check, String original){

        check=removeSpaceAtEnd(check);

        if(check.equalsIgnoreCase(original)) return true;
        else return false;

    }

}

