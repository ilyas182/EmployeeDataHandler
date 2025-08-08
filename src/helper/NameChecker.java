package helper;

import java.util.regex.Pattern;

public class NameChecker {
    private static final String nameRegex = "^[A-Za-z]+([ _]?[A-Za-z]+)*$";

    private static final Pattern namePattern = Pattern.compile(nameRegex);

    public static boolean checkName(String name) {
        return namePattern.matcher(name).matches();
    }
}
