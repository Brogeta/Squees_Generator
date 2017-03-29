package squees_generator.common.helpers;

import java.util.List;

/**
 * Created by Brandon.O'Donnell on 2/22/2017.
 */
public class StringHelper extends CommonHelper{

    //Checks a String, If it is null or empty it returns True, else it returns False
    public static boolean isNullOrEmpty(String s){
        return s == null || s.length() == 0;
    }

    //Takes a String, returns a string that consists of all of the uppercase characters of the given String
    public static String getAllUpper(String s){
        String  upperCase = new String();

        for(int x = 0; x < s.length(); ++x){
            if(Character.isUpperCase(s.charAt(x)))
                upperCase += s.charAt(x);

        }
        return upperCase;
    }


    //Takes a String, returns a string that consists of all of the lowercase characters of the given String
    public static String getAllLower(String s){
        String  lowerCase = new String();

        for(int x = 0; x < s.length(); ++x){
            if(Character.isLowerCase(s.charAt(x)))
                lowerCase += s.charAt(x);

        }
        return lowerCase;
    }

    //Takes a String, returns a string that consists of all of the number characters of the given String
    public static String getAllDigits(String s){
        String  digits = new String();

        for(int x = 0; x < s.length(); ++x){
            if(Character.isDigit(s.charAt(x)))
                digits += s.charAt(x);

        }
        return digits;
    }

    public static boolean stringListHasString(List<String> stringList, String string) {
        for(String str: stringList) {
            if(str.equals(string))
                return true;
        }
        return false;
    }
}
