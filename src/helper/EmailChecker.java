package helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailChecker {

    /**
     * Checks if email meets application requirements
     * @param email input email string
     * @return true if email is legal
     */
    public static boolean emailLegal(String email) {
        String strPattern= "^\\w+([.-]?\\w+)*@[A-Za-z0-9]+([.-]?[A-Za-z0-9]+)*\\.[a-z]{2,3}$";
        // creating pattern and matcher object
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(email);
        // flag for nothing found
        boolean found = false;
        // search
        while (matcher.find()) {
            found = true;
        }
        return found;
    }

}
