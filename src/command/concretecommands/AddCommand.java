package command.concretecommands;

import command.Command;
import exception.InvalidCommandException;
import receiver.Receiver;

import java.util.Arrays;

import static helper.EmailChecker.emailLegal;

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
    private boolean wasExecuted = false;

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
     * Constructs a fully initialized AddCommand ready for execution.
     * This constructor formats the first and last names into title case.
     *
     * @param receiver The receiver object that will execute the command.
     * @param param The first, last name, email for the new record
     */
    public AddCommand(Receiver receiver, String param) {
        String[] splitParam = param.split(" ");
        try {
            this.receiver = receiver;
            this.firstName = splitParam[0];
            this.lastName = splitParam[1];
            this.email = splitParam[2];
            this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
            this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
            this.allDataProvided = true;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
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
        this.email = emailLegal(this.email);

        if (this.email == null) {
            throw new InvalidCommandException("Incorrect email format: Thrown at Add command.Command");
        }
        System.out.println("add");
        receiver.add(firstName, lastName, email);
        wasExecuted = true;
    }

    /**
     * Override to string method
     * @return return overridden tostring output.
     */
    @Override
    public String toString() {
        return "Add command.Command: " + this.firstName + " " + this.lastName + " " + this.email;
    }

    /**
     * Method to function name for checking if undoable
     * @return String
     */
    public boolean isUndoable() {
        return true;
    }

    /**
     * This method calls the deleteCommand to delete the added values using AddCommand
     */
    @Override
    public void undo() throws InvalidCommandException {
        if (wasExecuted) {
            receiver.delete(receiver.getDataStore().size());
        } else {
            throw new InvalidCommandException("Cannot undo, this command was never executed.");
        }
    }
}
