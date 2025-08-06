package command;

import exception.InvalidCommandException;

/**
 * The Command interface usually declares just a single method for executing the command.
 * Concrete Commands implements the various kinds of commands. A concrete command isn’t supposed to perform the work
 * on its own, but rather to pass the call to one of the business logic objects. Parameters required to execute a
 * method on a receiving object can be declared as fields in the concrete command.
 */
public interface Command {
    /**
     * Executes the primary action of the command.
     * @throws InvalidCommandException if the command cannot be executed due to an invalid state or missing parameters.
     */
    void execute() throws InvalidCommandException;

    /**
     * Method to function name for checking if undoable
     * @return String
     */
    boolean isUndoable();

    /**
     * Undo method
     */
    void undo() throws InvalidCommandException;
}
