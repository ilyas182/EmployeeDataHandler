package command.concretecommands;

import command.Command;
import exception.InvalidCommandException;
import receiver.Receiver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A concrete command responsible for updating an existing record.
 * This class contains info required to perform an update operation,
 * including the recipient's details and a reference to the receiver that will execute the logic.
 */
public class UpdateCommand implements Command {

    private Receiver receiver;
    private Integer index;
    private String firstName;
    private String lastName;
    private String email;
    private String[] oldValues;

    /**
     * Constructs a partially initialized UpdateCommand.
     * Note: This command will not be executable until all data is provided.
     *
     * @param receiver The receiver object that will execute the command.
     * @param index The index to be updated.
     */
    public UpdateCommand(Receiver receiver, Integer index) {
        this.receiver = receiver;
        this.index = index;
    }

    /**
     * Constructs an UpdateCommand for firstName update.
     *
     * @param receiver The receiver object that will execute the command.
     * @param index The index to be updated.
     * @param firstName The first name to be updated.
     */
    public UpdateCommand(Receiver receiver, Integer index, String firstName) {
        this.receiver = receiver;
        this.index = index;
        this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
    }

    /**
     * Constructs an UpdateCommand for firstName and lastName update.
     *
     * @param receiver The receiver object that will execute the command.
     * @param index The index to be updated.
     * @param firstName The first name to be updated.
     * @param lastName The last name to be updated.
     */
    public UpdateCommand(Receiver receiver, Integer index, String firstName, String lastName) {
        this.receiver = receiver;
        this.index = index;
        this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
    }

    /**
     * Constructs an UpdateCommand for firstName, lastName and email update.
     *
     * @param receiver The receiver object that will execute the command.
     * @param index The index to be updated.
     * @param firstName The first name to be updated.
     * @param lastName The last name to be updated.
     * @param email The email to be updated.
     */
    public UpdateCommand(Receiver receiver, Integer index, String firstName, String lastName, String email) {
        this.receiver = receiver;
        this.index = index;
        this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        this.email = email;
    }

    /**
     * Executes the primary action of the update command.
     * @throws InvalidCommandException if the command cannot be executed due to an invalid state or missing parameters.
     */
    @Override
    public void execute() throws InvalidCommandException {
        if (index > receiver.getDataStore().size() || index < 1) {
            throw new InvalidCommandException("Incorrect Index provided: Thrown at Update command.Command");
        }
        this.oldValues = new String[]{receiver.getDataStore().get(index - 1)[0], receiver.getDataStore().get(index - 1)[1], receiver.getDataStore().get(index - 1)[2]};
        if (firstName == null && lastName == null && email == null) {
            throw new InvalidCommandException("No data provided for update: Thrown at Update command.Command");
          } else {
            if (firstName != null && lastName == null && email == null) {
                receiver.updateCommand(index, firstName);
            } else if (firstName != null && lastName != null && email == null) {
                receiver.updateCommand(index, firstName, lastName);
            } else {
                if (!emailLegal(email)) {
                    throw new InvalidCommandException("Incorrect email format: Thrown at Update command.Command");
                }
                receiver.updateCommand(index, firstName, lastName, email);
            }
        }
    }

    /**
     * getter for old values
     * @return list of old values
     */
    public String[] getOldValues() {
        return this.oldValues;
    }

    /**
     * getter for index
     * @return index
     */
    public Integer getIndex() {
        return this.index;
    }

    /**
     * Checks if email meets application requirements
     * @param email input email string
     * @return true if email is legal
     */
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

    /**
     * Override to string method
     * @return return overridden tostring output.
     */
    @Override
    public String toString() {
        return "Update command.Command: " + index + " " + firstName + " " + lastName + " " + email;
    }


    /**
     * Method to function name for checking if undoable
     * @return String
     */
    public String isUndoable() {
        return "update";
    }
}
