import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCommand implements Command {

    private Receiver receiver;
    private String firstName;
    private String lastName;
    private String email;

    public AddCommand(Receiver receiver, String firstName, String lastName, String email) {
        // Need ensure that the Case of the name etc is correct as per requirements in the practicum

        String firstNameClean = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        String lastNameClean = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();

        this.receiver = receiver;
        this.firstName = firstNameClean;
        this.lastName = lastNameClean;
        this.email = email;
        }

    @Override
    public void execute() throws InvalidCommandException {
        // check legal email or not else throw custom exception InvalidCommandException
        if (!emailLegal(this.email)) {
            throw new InvalidCommandException("Incorrect email format: Thrown at Add Command");
        }
        receiver.addCommand(firstName, lastName, email);
    }

    public boolean emailLegal(String email) {
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

    @Override
    public String toString() {
        if (firstName == null && lastName == null && email == null) {
            return null;
        } else {
            return "Add Command: " + this.firstName + " " + this.lastName + " " + this.email;
        }
    }
}
