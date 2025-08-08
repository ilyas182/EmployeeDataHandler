package command.concretecommands;

import command.Command;
import exception.InvalidCommandException;
import helper.NameChecker;
import receiver.Receiver;


import static helper.EmailChecker.emailLegal;

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
    private boolean wasExecuted = false;

    /**
     * Constructor method for {@code UpdateCommand} class
     * @param receiver the receiver instance
     * @param param the data parameters for the update
     */
    public UpdateCommand(Receiver receiver, String param) {
        this.receiver = receiver;
        String[] splitParams = param.split(" ");
        try {
            if (splitParams.length == 1) {
                this.index = Integer.parseInt(splitParams[0]);
            } else if (splitParams.length == 2) {
                this.index = Integer.parseInt(splitParams[0]);
                this.firstName = splitParams[1];
            } else if (splitParams.length == 3) {
                this.index = Integer.parseInt(splitParams[0]);
                this.firstName = splitParams[1];
                this.lastName = splitParams[2];
            } else if (splitParams.length == 4) {
                this.index = Integer.parseInt(splitParams[0]);
                this.firstName = splitParams[1];
                this.lastName = splitParams[2];
                this.email = splitParams[3];
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Index must be an integer");
        }
        if (firstName != null) {
            this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        }
        if (lastName != null) {
            this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        }
    }


    /**
     * Executes the primary action of the update command.
     * @throws InvalidCommandException if the command cannot be executed due to an invalid state or missing parameters.
     */
    @Override
    public void execute() throws InvalidCommandException {
        if (index == null) {
            throw new InvalidCommandException("Invalid index");
        }
        if (index > receiver.getDataStore().size() || index < 1) {
            throw new InvalidCommandException("Incorrect Index provided: Thrown at Update command.Command");
        }
        this.oldValues = new String[]{receiver.getDataStore().get(index - 1)[0], receiver.getDataStore().get(index - 1)[1], receiver.getDataStore().get(index - 1)[2]};
        if (firstName == null && lastName == null && email == null) {
            throw new InvalidCommandException("No data provided for update: Thrown at Update command.Command");
          } else {
            if (firstName != null && lastName == null && email == null) {
                if (!NameChecker.checkName(this.firstName)) {
                    throw new InvalidCommandException("Incorrect name format: Thrown at Update command.Command");
                }
                System.out.println("update");
                receiver.update(index, firstName);
                wasExecuted = true;
            } else if (firstName != null && lastName != null && email == null) {
                if (!NameChecker.checkName(this.lastName)) {
                    throw new InvalidCommandException("Incorrect name format: Thrown at Update command.Command");
                }
                System.out.println("update");
                receiver.update(index, firstName, lastName);
                wasExecuted = true;
            } else {
                this.email = emailLegal(this.email);
                if (this.email == null) {
                    throw new InvalidCommandException("Incorrect email format: Thrown at Update command.Command");
                }
                System.out.println("update");
                receiver.update(index, firstName, lastName, email);
                wasExecuted = true;
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
    public boolean isUndoable() {
        return true;
    }

    /**
     * This method is to undo this instance to the previous values
     */
    @Override
    public void undo() throws InvalidCommandException {
        if (wasExecuted) {
            receiver.update(index, oldValues[0], oldValues[1], oldValues[2]);
        } else {
            throw new InvalidCommandException("Cannot undo, this command was never executed.");
        }
    }
}
