package helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailChecker {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9]+(?:[._-][a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-z]{2,3}$";
    private static final String LATIN_WORD_REGEX = "^[a-zA-Z][a-zA-Z0-9_]*$";

    private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
    private static final Pattern latinWordPattern = Pattern.compile(LATIN_WORD_REGEX);


    /**
     * Call data check logic
     *
     * @param email email string
     * @return boolean true if pass false if fail
     */
    public static String emailLegal(String email) {
        if (email == null || email.trim().isEmpty()) {
            return null;
        }

        boolean isEmail = emailPattern.matcher(email).matches();
        boolean isLatinWord = latinWordPattern.matcher(email).matches();

        // Input match any one to pass
        if (isEmail) {
            return email;
        } else if (isLatinWord) {
            return email.substring(0, 1).toUpperCase() + email.substring(1).toLowerCase();
        } else {
            return null;
        }
    }
}
