import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateCommand implements Command {

    private Receiver receiver;
    private Integer index;
    private String firstName;
    private String lastName;
    private String email;
    private String[] oldValues;

    // Create constructor for firstName update
    public UpdateCommand(Receiver receiver, Integer index, String firstName) {
        this.receiver = receiver;
        this.index = index;
        this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
    }

    // Create constructor for firstName and lastName update
    public UpdateCommand(Receiver receiver, Integer index, String firstName, String lastName) {
        this.receiver = receiver;
        this.index = index;
        this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
    }

    // Create constructor for firstName, lastName, email update
    public UpdateCommand(Receiver receiver, Integer index, String firstName, String lastName, String email) {
        this.receiver = receiver;
        this.index = index;
        this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        this.email = email;
    }

    /**
     * Implement Command Interface execute method
     * This method calls dynamically calls the Receiver's updateCommand methods based on number of parameters passed
     */
    @Override
    public void execute() throws InvalidCommandException {
        this.oldValues = new String[]{receiver.getDataStore().get(index - 1)[0], receiver.getDataStore().get(index - 1)[1], receiver.getDataStore().get(index - 1)[2]};
        if (firstName != null && lastName == null && email == null) {
            receiver.updateCommand(index, firstName);
        } else if (firstName != null && lastName != null && email == null) {
            receiver.updateCommand(index, firstName, lastName);
        } else {
            if (!emailLegal(email)) {
                throw new InvalidCommandException("Incorrect email format: Thrown at Update Command");
            }
            receiver.updateCommand(index, firstName, lastName, email);
        }
    }

    public String[] getOldValues() {
        return this.oldValues;
    }

    public Integer getIndex() {
        return this.index;
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
        if (email == null) {
            return null;
        } else {
            return "Update Command: " + index + " " + firstName + " " + lastName + " " + email;
        }
    }
}
