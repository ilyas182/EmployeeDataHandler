package command.concretecommands;

import command.Command;
import exception.InvalidCommandException;
import receiver.Receiver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A concrete command responsible for adding a new record.
 * This class contains info required to perform an add operation,
 * including the recipient's details and a reference to the receiver that will execute the logic.
 */
public class AddCommand implements Command {

    private Receiver receiver;
    private String firstName;
    private String lastName;
    private String email;
    private boolean allDataProvided = false;

    /**
     * Constructs a partially initialized AddCommand.
     * Note: This command will not be executable until all data is provided.
     *
     * @param receiver The receiver object that will execute the command.
     */
    public AddCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Constructs a partially initialized AddCommand.
     * Note: This command will not be executable until all data is provided.
     *
     * @param receiver The receiver object that will execute the command.
     * @param firstName The first name for the new record.
     */
    public AddCommand(Receiver receiver, String firstName){
        this.receiver = receiver;
        this.firstName = firstName;
    }

    /**
     * Constructs a partially initialized AddCommand.
     * Note: This command will not be executable until all data is provided.
     *
     * @param receiver The receiver object that will execute the command.
     * @param firstName The first name for the new record.
     * @param lastName The last name for the new record.
     */
    public AddCommand(Receiver receiver, String firstName, String lastName) {
        this.receiver = receiver;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Constructs a fully initialized AddCommand ready for execution.
     * This constructor formats the first and last names into title case.
     *
     * @param receiver The receiver object that will execute the command.
     * @param firstName The first name for the new record.
     * @param lastName The last name for the new record.
     * @param email The email address for the new record.
     */
    public AddCommand(Receiver receiver, String firstName, String lastName, String email) {
        // Need ensure that the Case of the name etc is correct as per requirements in the practicum
        if (firstName.isEmpty() && lastName.isEmpty()) {
            this.receiver = receiver;
            this.firstName = firstName; // become ""
            this.lastName = lastName; // become ""
            this.email = email;
        } else if (lastName.isEmpty()) {
            this.receiver = receiver;
            this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
            this.lastName = lastName;
            this.email = email;
        } else if (firstName.isEmpty()) {
            this.receiver = receiver;
            this.firstName = firstName;
            this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
            this.email = email;
        } else {
            this.allDataProvided = true;
            this.receiver = receiver;
            this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
            this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
            this.email = email;
        }
    }

    /**
     * Executes the primary action of the add command.
     * @throws InvalidCommandException if the command cannot be executed due to an invalid state or missing parameters.
     */
    @Override
    public void execute() throws InvalidCommandException {
        // check legal email or not else throw custom exception exception.InvalidCommandException
        if (!this.allDataProvided) {
            throw new InvalidCommandException("All inputs needs to be provided for Add: Thrown at Add command.Command");
        }
        if (!emailLegal(this.email)) {
            throw new InvalidCommandException("Incorrect email format: Thrown at Add command.Command");
        }
        receiver.addCommand(firstName, lastName, email);
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
        return "Add command.Command: " + this.firstName + " " + this.lastName + " " + this.email;
    }
}
