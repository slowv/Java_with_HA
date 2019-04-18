package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckCharacterSpecial {
    public static boolean checkCharacterSpecial(String value){
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(value);
        boolean b = m.find();
        if (b){
            System.out.println("There is a special character in my string");
            return true;
        }
        return false;
    }
}
