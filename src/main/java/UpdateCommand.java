import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateCommand implements Command {

    private Receiver receiver;
    private Integer index;
    private String firstName;
    private String lastName;
    private String email;
    private String[] oldValues;
    private boolean updateEmail = false;

    // Create constructor for firstName update
    public UpdateCommand(Receiver receiver, Integer index, String firstName) {
        this.receiver = receiver;
        this.index = index;
        this.firstName = firstName;
    }

    // Create constructor for firstName and lastName update
    public UpdateCommand(Receiver receiver, Integer index, String firstName, String lastName) {
        this.receiver = receiver;
        this.index = index;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Create constructor for firstName, lastName, email update
    public UpdateCommand(Receiver receiver, Integer index, String firstName, String lastName, String email) {
        updateEmail = true;
        if (emailLegal(email)) {
            this.receiver = receiver;
            this.index = index;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        } else {
            this.email = null;
            System.out.println("Please recheck email validity.");
        }
    }

    /**
     * Implement Command Interface execute method
     * This method calls dynamically calls the Receiver's updateCommand methods based on number of parameters passed
     */
    @Override
    public void execute() {
        this.oldValues = new String[]{receiver.getDataStore().get(index - 1)[0], receiver.getDataStore().get(index - 1)[1], receiver.getDataStore().get(index - 1)[2]};
        if (firstName != null && lastName == null && email == null) {
            receiver.updateCommand(index, firstName);
        } else if (firstName != null && lastName != null && email == null) {
            receiver.updateCommand(index, firstName, lastName);
        } else {
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
        if (updateEmail && email == null) {
            return null;
        } else {
            return "Update Command: " + index + " " + firstName + " " + lastName + " " + email;
        }
    }
}
